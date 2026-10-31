package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.CierreCajaDTO;
import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.CierreCaja;
import com.grupo06.epicentrogourmet.entity.Pedido;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.repository.ICierreCajaRepository;
import com.grupo06.epicentrogourmet.repository.IPedidoRepository;
import com.grupo06.epicentrogourmet.repository.UnidadVentaRepository;
import com.grupo06.epicentrogourmet.service.interfaces.ICierreCajaService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.List;

@Service
public class CierreCajaServiceImpl implements ICierreCajaService {

    private final ICierreCajaRepository cierreCajaRepository;
    private final ModelMapper modelMapper;
    private final UnidadVentaRepository unidadVentaRepository;
    private final IPedidoRepository pedidoRepository;

    public CierreCajaServiceImpl(
            ICierreCajaRepository cierreCajaRepository,
            ModelMapper modelMapper,
            UnidadVentaRepository unidadVentaRepository,
            IPedidoRepository pedidoRepository) {

        this.cierreCajaRepository = cierreCajaRepository;
        this.modelMapper = modelMapper;
        this.unidadVentaRepository = unidadVentaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CierreCajaDTO> listarCierres() {

        return cierreCajaRepository.findAll().stream()
                .map(cierre -> modelMapper.map(cierre, CierreCajaDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public void guardarCierre(CierreCajaDTO cierreCajaDTO) {

        // Validamos los datos necesarios para registrar el cierre.
        if (cierreCajaDTO.getFecha() == null) {
            throw new IllegalArgumentException(
                    "La fecha del cierre es obligatoria."
            );
        }

        if (cierreCajaDTO.getIdCajero() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un cajero."
            );
        }

        if (cierreCajaDTO.getIdUnidadVenta() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar una unidad de venta."
            );
        }

        // Buscamos la unidad de venta.
        UnidadVenta unidad = unidadVentaRepository
                .findById(cierreCajaDTO.getIdUnidadVenta())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "La Unidad de Venta ingresada no existe."
                        )
                );

        // Buscamos los pedidos de esa unidad correspondientes a la fecha.
        List<Pedido> pedidos = pedidoRepository
                .findByUnidadVenta_IdUnidadVentaAndFecha(
                        unidad.getIdUnidadVenta(),
                        cierreCajaDTO.getFecha()
                );

        // Calculamos la recaudación sumando el total de cada pedido.
        BigDecimal montoTotal = pedidos.stream()
                .map(Pedido::calcularTotalVenta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Creamos el cierre de caja.
        CierreCaja cierre = new CierreCaja();

        cierre.setFecha(cierreCajaDTO.getFecha());

        // El monto se calcula automáticamente, no viene del formulario.
        cierre.setMontoTotal(montoTotal);

        // Asignamos el cajero.
        Cajero cajero = new Cajero();
        cajero.setIdEmpleado(cierreCajaDTO.getIdCajero());
        cierre.setCajero(cajero);

        cierre.setUnidadVenta(unidad);

        // Guardamos el cierre en la base de datos.
        cierreCajaRepository.save(cierre);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] generarPdfCierre(Long id) {

        CierreCaja cierre = cierreCajaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe el cierre de caja solicitado."
                        )
                );

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        Document documento = new Document(PageSize.A4);

        try {

            PdfWriter.getInstance(documento, salida);
            documento.open();

            Font titulo = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font subtitulo = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font texto = new Font(Font.HELVETICA, 11, Font.NORMAL);

            documento.add(new Paragraph("EPICENTRO GOURMET", titulo));
            documento.add(new Paragraph("Comprobante de cierre de caja", subtitulo));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(
                    "Número de cierre: #" + cierre.getIdCierreCaja(),
                    texto
            ));

            documento.add(new Paragraph(
                    "Fecha: " + cierre.getFecha().format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    ),
                    texto
            ));

            NumberFormat formatoMoneda =
                    NumberFormat.getCurrencyInstance(new Locale("es", "AR"));

            documento.add(new Paragraph(
                    "Monto total: " + formatoMoneda.format(cierre.getMontoTotal()),
                    texto
            ));

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("DATOS DEL CAJERO", subtitulo));

            Cajero cajero = cierre.getCajero();

            documento.add(new Paragraph(
                    "Nombre completo: " + cajero.getNombre()
                            + " " + cajero.getApellido(),
                    texto
            ));

            documento.add(new Paragraph(
                    "DNI: " + cajero.getDni(),
                    texto
            ));

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("UNIDAD DE VENTA", subtitulo));

            documento.add(new Paragraph(
                    "ID de unidad de venta: "
                            + cierre.getUnidadVenta().getIdUnidadVenta(),
                    texto
            ));

            documento.close();

            return salida.toByteArray();

        } catch (DocumentException e) {

            if (documento.isOpen()) {
                documento.close();
            }

            throw new IllegalStateException(
                    "No se pudo generar el PDF del cierre de caja.",
                    e
            );
        }
    }

    @Override
    public List<CierreCajaDTO> listarCierresPorFecha(
            LocalDate fechaDesde,
            LocalDate fechaHasta) {

        return listarCierres().stream()
                .filter(cierre -> cierre.getFecha() != null)
                .filter(cierre ->
                        !cierre.getFecha().isBefore(fechaDesde)
                        && !cierre.getFecha().isAfter(fechaHasta)
                )
                .toList();
    }
}
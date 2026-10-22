package com.grupo06.epicentrogourmet.service.implementation;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.service.EmpleadoService;
import com.grupo06.epicentrogourmet.service.IPdfService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;

@Service
public class PdfService implements IPdfService {

    Logger logger = LoggerFactory.getLogger(PdfService.class);

    private final EmpleadoService empleadoService;

    public PdfService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] generarListadoEmpleados() {

        List<Empleado> listadoEmpleados = empleadoService.listarEmpleados();

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, salida);
            document.open();

            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fuenteEncabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

            Paragraph titulo = new Paragraph("Epicentro Gourmet - Listado de empleados", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(15);
            document.add(titulo);

            PdfPTable tablaEmpleados = new PdfPTable(6);
            tablaEmpleados.setWidthPercentage(100);
            tablaEmpleados.setWidths(new float[]{3f, 2.5f, 1.8f, 4.2f, 2f, 1.5f});
            tablaEmpleados.setHeaderRows(1);

            for (String encabezado : new String[]{"Nombre", "Puesto","DNI", "Email", "Ingreso","Estado"}) {
                PdfPCell celda = new PdfPCell(new Phrase(encabezado, fuenteEncabezado));
                celda.setBackgroundColor(Color.LIGHT_GRAY);
                celda.setPadding(6);
                tablaEmpleados.addCell(celda);
            }

            listadoEmpleados.forEach(empleado -> {
                tablaEmpleados.addCell(empleado.getNombre()+" "+empleado.getApellido());
                tablaEmpleados.addCell(obtenerPuesto(empleado));
                tablaEmpleados.addCell(String.valueOf(empleado.getDni()));
                tablaEmpleados.addCell(empleado.getEmail());
                tablaEmpleados.addCell(
                        empleado.getFechaIngreso() != null
                                ? empleado.getFechaIngreso().toString()
                                : "-");
                tablaEmpleados.addCell(empleado.isActivo()? "Activo" : "Baja");
            });

            document.add(tablaEmpleados);

        } catch (DocumentException e) {
            logger.error("Error al generar el PDF de empleados: {}", e.getMessage());
            throw new IllegalStateException("No se pudo generar el PDF de empleados.", e);
        } finally {
            document.close();
        }

        return salida.toByteArray();
    }

    private String obtenerPuesto(Empleado empleado) {

        Object real = Hibernate.unproxy(empleado);

        if (real instanceof Cocinero cocinero) {
            return "Cocinero - " + cocinero.getRolCocina();
        }

        if (real instanceof Cajero cajero) {
            return "Cajero - " + cajero.getTurno();
        }

        return "-";
    }
}
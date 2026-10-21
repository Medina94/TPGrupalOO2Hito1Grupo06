package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.PlatoDTO;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Plato;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.PlatoRepository;
import com.grupo06.epicentrogourmet.repository.UnidadVentaRepository;
import com.grupo06.epicentrogourmet.service.IPlatoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PlatoService implements IPlatoService {
    private final PlatoRepository platoRepository;
    private final ModelMapper modelMapper;
    private final UsuarioActualService usuarioActualService;
    private final UnidadVentaRepository unidadVentaRepository;

    public PlatoService(
            PlatoRepository platoRepository,
            ModelMapper modelMapper,
            UsuarioActualService usuarioActualService,
            UnidadVentaRepository unidadVentaRepository) {
        this.platoRepository = platoRepository;
        this.modelMapper = modelMapper;
        this.usuarioActualService = usuarioActualService;
        this.unidadVentaRepository = unidadVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlatoDTO> listarPlatos() {
        UnidadVenta unidadVenta = buscarUnidadUnicaDelUsuario();
        return platoRepository
                .findAllByUnidadVenta_IdUnidadVenta(
                        unidadVenta.getIdUnidadVenta()
                )
                .stream()
                .map(plato -> modelMapper.map(plato, PlatoDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public void agregarPlato(PlatoDTO platoDTO) {
        UnidadVenta unidadVenta = buscarUnidadUnicaDelUsuario();
        Plato plato = modelMapper.map(platoDTO, Plato.class);
        plato.setUnidadVenta(unidadVenta);
        platoRepository.save(plato);
    }

    @Override
    @Transactional
    public void actualizarPlato(Long idPlato, PlatoDTO platoDTO) {
        Plato plato = buscarPlatoDeUnidad(idPlato);
        plato.setNombre(platoDTO.getNombre());
        plato.setPrecioVenta(platoDTO.getPrecioVenta());
        plato.setCostoProduccion(platoDTO.getCostoProduccion());
        platoRepository.save(plato);
    }

    @Override
    @Transactional
    public void eliminarPlato(Long idPlato) {
        platoRepository.delete(buscarPlatoDeUnidad(idPlato));
    }

    private Plato buscarPlatoDeUnidad(Long idPlato) {
        UnidadVenta unidadVenta = buscarUnidadUnicaDelUsuario();
        Plato plato = platoRepository.findById(idPlato)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el plato seleccionado."
                ));
        if (!Objects.equals(
                plato.getUnidadVenta().getIdUnidadVenta(),
                unidadVenta.getIdUnidadVenta())) {
            throw new IllegalArgumentException(
                    "El plato seleccionado no pertenece a tu unidad de venta."
            );
        }
        return plato;
    }

    private UnidadVenta buscarUnidadUnicaDelUsuario() {
        Usuario usuario = usuarioActualService.obtenerUsuarioActual();
        Empleado empleado = usuario.getEmpleado();
        if (empleado == null) {
            throw new IllegalArgumentException(
                    "El usuario no está vinculado a un empleado."
            );
        }

        List<UnidadVenta> unidades =
                unidadVentaRepository.findByResponsable_IdEmpleado(
                        empleado.getIdEmpleado()
                );

        if (unidades.size() != 1) {
            throw new IllegalArgumentException(
                    "El empleado debe tener exactamente una unidad de venta "
                    + "asignada para registrar platos."
            );
        }

        return unidades.get(0);
    }
}

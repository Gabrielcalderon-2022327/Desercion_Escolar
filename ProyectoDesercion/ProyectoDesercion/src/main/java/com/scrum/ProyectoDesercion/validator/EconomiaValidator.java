package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Economia;
import com.scrum.ProyectoDesercion.repository.EconomiaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EconomiaValidator {
    private final EconomiaRepository economiaRepository;

    public EconomiaValidator(EconomiaRepository economiaRepository) {
        this.economiaRepository = economiaRepository;
    }

    public void validar(Economia economia) {
        Double ingresos = economia.getIngresosEconomia();
        LocalDate fecha = economia.getFechaEconomia();
        Integer idEstudiante = economia.getIdEstudiante();

        if (ingresos == null || ingresos <= 0) {
            throw new IllegalArgumentException("Los ingresos deben ser mayores a 0");
        }

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (idEstudiante == null || idEstudiante <= 0) {
            throw new IllegalArgumentException("El id del estudiante debe ser mayor a 0");
        }
        List<Economia> economias = economiaRepository.findAll();
        for (Economia eco : economias) {
            if (eco.getIdEstudiante().equals(idEstudiante)
                    && eco.getFechaEconomia().equals(fecha)) {
                throw new IllegalArgumentException(
                        "Ya existe un registro económico para este estudiante en esa fecha"
                );
            }
        }
    }
}
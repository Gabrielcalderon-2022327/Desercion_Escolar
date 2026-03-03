package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.repository.MateriasfRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MateriasfServiceImplements implements MateriasfService{
    private final MateriasfRepository materiasfRepository;

    public MateriasfServiceImplements(MateriasfRepository materiasfRepository) { this.materiasfRepository = materiasfRepository; }

    @Override
    public List<MateriasF> getAllMateriasF() {
        return materiasfRepository.findAll();
    }

    @Override
    public MateriasF getMateriasFById(Integer id) {
        return materiasfRepository.findById(id).orElse(null);
    }

    @Override
    public MateriasF saveMateriasF(MateriasF materiasf) throws RuntimeException {
        return materiasfRepository.save(materiasf);
    }

    @Override
    public MateriasF updateMateriasF(Integer id, MateriasF materiasf) {
        MateriasF materiasF1 = materiasfRepository.findById(id).orElse(null);
        if (materiasF1 != null) {
            materiasF1.setNombreMateriaF(materiasf.getNombreMateriaF());
            materiasF1.setDescripcionMateriaF(materiasf.getDescripcionMateriaF());
            materiasF1.setFechaAlertaMateriaF(materiasf.getFechaAlertaMateriaF());
            materiasF1.setIdMaestro(materiasf.getIdMaestro());
            materiasF1.setIdEstudiante(materiasf.getIdEstudiante());
        } else {
            throw new RuntimeException("Materia no encontrado");
        }
        return materiasfRepository.save(materiasF1);
    }

    public void deleteMateriasF(Integer id) {
        MateriasF materiasf = materiasfRepository.findById(id).orElse(null);
        if (materiasf == null) {
            throw new ResourceNotFoundException("Materia no encontrada");
        }
        materiasfRepository.delete(materiasf);
    }
}


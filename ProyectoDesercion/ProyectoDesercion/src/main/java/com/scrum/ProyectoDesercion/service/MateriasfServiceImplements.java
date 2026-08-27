package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.MateriasfRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriasfServiceImplements implements MateriasfService{
    private final MateriasfRepository materiasfRepository;

    public MateriasfServiceImplements(MateriasfRepository materiasfRepository) { this.materiasfRepository = materiasfRepository; }

    @Override
    public List<MateriasF> getAllMateriasF() {
        return materiasfRepository.findAll();
    }

    @Override
    public Optional<MateriasF> getMateriasFById(Integer id) {
        MateriasF searchedmateria = materiasfRepository.findById(id).orElse(null);
        if (searchedmateria == null){
            throw new ResourceNotFoundException("Materia no encontrado");
        }
        return materiasfRepository.findById(id);
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


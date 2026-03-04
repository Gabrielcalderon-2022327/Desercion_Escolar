package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Grado;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.GradoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradoServiceImplements implements GradoService{
    private final GradoRepository gradoRepository;

    public GradoServiceImplements(GradoRepository gradoRepository) {
        this.gradoRepository = gradoRepository;
    }

    @Override
    public List<Grado> getAllGrado() {
        return gradoRepository.findAll();
    }

    @Override
    public Grado getGradoById(Integer id) {

        Grado grado = gradoRepository.findById(id).orElse(null);

        if(grado == null){
            throw new ResourceNotFoundException("El grado no se ha encontrado");
        }
        return grado;
    }

    @Override
    public Grado saveGrado(Grado grado) throws RuntimeException {
        return gradoRepository.save(grado);
    }

    @Override
    public Grado updateGrado(Integer id, Grado grado) {

        Grado updateGrado = gradoRepository.findById(id).orElse(null);
        if(updateGrado != null){
            updateGrado.setNombre_grado(grado.getNombre_grado());
            updateGrado.setFk_id_maestro(grado.getFk_id_maestro());
        }else{
            throw new ResourceNotFoundException("Grado no ha sido encontrado");
        }
        return gradoRepository.save(updateGrado);
    }

    @Override
    public void deleteGrado(Integer id) {
        Grado grado = gradoRepository.findById(id).orElse(null);

        if(grado == null){
            throw new ResourceNotFoundException("Grado no se ha encontrado");
        }
        gradoRepository.deleteById(id);

    }
}

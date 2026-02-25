package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Encargado;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.EncargadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncargadoServiceImplements implements EncargadoService{

    private final EncargadoRepository encargadoRepository;

    public EncargadoServiceImplements(EncargadoRepository encargadoRepository) {
        this.encargadoRepository = encargadoRepository;
    }

    @Override
    public List<Encargado> getAllEncargado() {
        return encargadoRepository.findAll();
    }

    @Override
    public Encargado getEncargadoById(Integer id) {
        Encargado encargado = encargadoRepository.findById(id).orElse(null);

        if(encargado == null){
            throw new ResourceNotFoundException("El Encargado no se ha encontrado");
        }
        return encargado;
    }

    @Override
    public Encargado saveEncargado(Encargado encargado) throws RuntimeException {
        return encargadoRepository.save(encargado);
    }

    @Override
    public Encargado updateEncargado(Integer id, Encargado encargado) {

        Encargado updateEncargado = encargadoRepository.findById(id).orElse(null);
        if(updateEncargado != null){
            updateEncargado.setNombre_encargado(encargado.getNombre_encargado());
            updateEncargado.setApellido_encargado(encargado.getApellido_encargado());
            updateEncargado.setFecha_nacimiento_encargado(encargado.getFecha_nacimiento_encargado());
            updateEncargado.setDireccion_encargado(encargado.getDireccion_encargado());
            updateEncargado.setTelefono_encargado(encargado.getTelefono_encargado());
        }else{
            throw new ResourceNotFoundException("Encargado no ha sido encontrado");
        }

        return encargadoRepository.save(updateEncargado);
    }

    @Override
    public void deleteEncargado(Integer id) {
        Encargado encargado = encargadoRepository.findById(id).orElse(null);

        if(encargado == null){
            throw new ResourceNotFoundException("Encargado no se ha encontrado");
        }
        encargadoRepository.deleteById(id);
    }
}

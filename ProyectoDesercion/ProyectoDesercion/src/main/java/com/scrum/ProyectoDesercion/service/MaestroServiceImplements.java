package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Economia;
import com.scrum.ProyectoDesercion.entity.Maestro;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.MaestroRepository;

import java.util.List;

public class MaestroServiceImplements implements MaestroService{
    private final MaestroRepository maestroRepository;

    public MaestroServiceImplements(MaestroRepository maestroRepository){
        this.maestroRepository = maestroRepository;
    }


    @Override
    public List<Maestro> getAllMaestros() {
        return maestroRepository.findAll();
    }

    @Override
    public Maestro getMaestroById(Integer id) {
        Maestro maestro = maestroRepository.findById(id).orElse(null);
        if (maestro == null) {
            throw new ResourceNotFoundException("Maestro no encontrado");
        }
        return maestro;
    }

    @Override
    public Maestro saveMaestro(Maestro maestro) throws RuntimeException {
        return maestroRepository.save(maestro);
    }

    @Override
    public Maestro updateMaestro(Integer id, Maestro maestro) {
        Maestro updateMaestro = maestroRepository.findById(maestro.getIdMaestro()).orElse(null);
        if (updateMaestro != null) {
            updateMaestro.setNombreMaestro(maestro.getNombreMaestro());
            updateMaestro.setEspecialidadMaestro(maestro.getEspecialidadMaestro());
            updateMaestro.setTelefonoMaestro(maestro.getTelefonoMaestro());
            updateMaestro.setIdUsuario(maestro.getIdUsuario());
        } else{
            throw new ResourceNotFoundException("Maestro no encontrado");
        }
        return maestroRepository.save(updateMaestro);
    }

    @Override
    public void deleteMaestro(Integer id) {
        Maestro maestro = maestroRepository.findById(id).orElse(null);
        if (maestro == null) {
            throw new ResourceNotFoundException("Maestro no encontrado");
        }
        maestroRepository.delete(maestro);
    }
}

package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.MateriasF;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface MateriasfService {
    List<MateriasF> getAllMateriasF();
    Optional<MateriasF> getMateriasFById(Integer id);
    MateriasF saveMateriasF(MateriasF materiasF) throws RuntimeException;
    MateriasF updateMateriasF(Integer id, MateriasF materiasF);
    void deleteMateriasF(Integer id);
}

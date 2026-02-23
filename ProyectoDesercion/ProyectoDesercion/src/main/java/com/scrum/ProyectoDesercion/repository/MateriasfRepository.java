package com.scrum.ProyectoDesercion.repository;
import com.scrum.ProyectoDesercion.entity.MateriasF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriasfRepository extends JpaRepository<MateriasF,Integer> {

}

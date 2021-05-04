package com.example.dsc1.repositories;

import com.example.dsc1.dtos.DisciplinaNotaDTO;
import com.example.dsc1.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findAllByOrderByNotaDesc();
    List<Disciplina> findAllByOrderByLikesDesc();
}

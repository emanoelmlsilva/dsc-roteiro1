package com.example.dsc1.repositories;

import com.example.dsc1.models.Comentario;
import com.example.dsc1.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}

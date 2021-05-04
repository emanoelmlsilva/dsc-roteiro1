package com.example.dsc1.dtos;

import com.example.dsc1.models.Disciplina;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class DisciplinaNotaDTO implements Serializable {

    private static final long serialVersionUID = -214863204542986002L;
    private Long id;
    private String nome;
    private Double nota;

    public DisciplinaNotaDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.nota = disciplina.getNota();
    }
}

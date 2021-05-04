package com.example.dsc1.dtos;

import com.example.dsc1.models.Comentario;
import com.example.dsc1.models.Disciplina;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DisciplinaComentariosDTO implements Serializable {
    private static final long serialVersionUID = -214863204542986002L;
    private Long id;
    private String nome;
    private List<Comentario> comentarioList;

    public DisciplinaComentariosDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.comentarioList = disciplina.getComentarioList();
    }
}

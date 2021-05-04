package com.example.dsc1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Disciplina implements Serializable {
    private static final long serialVersionUID = -214863204542986002L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nome;
    Double nota;
    int likes;


    @JsonIgnore
    @OneToMany(mappedBy = "disciplina")
    List<Comentario> comentarioList;


}
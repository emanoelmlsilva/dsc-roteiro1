package com.example.dsc1.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String comentario;

    @ManyToOne
    @JoinColumn(name="disciplina_comentario")
    Disciplina disciplina;

}

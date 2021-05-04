package com.example.dsc1.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.dsc1.dtos.DisciplinaComentariosDTO;
import com.example.dsc1.dtos.DisciplinaDTO;
import com.example.dsc1.dtos.DisciplinaLikesDTO;
import com.example.dsc1.dtos.DisciplinaNotaDTO;
import com.example.dsc1.models.Comentario;
import com.example.dsc1.models.Disciplina;
import com.example.dsc1.services.DisciplinaService;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll() {
        return new ResponseEntity<List<DisciplinaDTO>>(disciplinaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable long id) {
        try {
            return new ResponseEntity<Disciplina>(disciplinaService.findById(id), HttpStatus.OK);
        } catch (ArrayIndexOutOfBoundsException error) {
            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id}/likes")
    public ResponseEntity<Disciplina> updateLikes(@PathVariable long id){
        try {
            return new ResponseEntity<Disciplina>(disciplinaService.updateLikes(id), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/nota")
    public ResponseEntity<DisciplinaNotaDTO> updateDisciplineNota(@PathVariable("id") long id, @Valid @RequestBody ObjectNode json) {
        try {
            Double nota = json.get("nota").asDouble();
            return new ResponseEntity<DisciplinaNotaDTO>(disciplinaService.updataNota(id, nota), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<DisciplinaNotaDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<DisciplinaComentariosDTO> updateDisciplinaComentario(@PathVariable long id, @Valid @RequestBody Comentario comentario){
        try {
            return new ResponseEntity<DisciplinaComentariosDTO>(disciplinaService.insertComentario(id, comentario), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<DisciplinaComentariosDTO>( HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ranking/notas")
    public ResponseEntity<List<DisciplinaNotaDTO>> findAllNotas() {
        return new ResponseEntity<List<DisciplinaNotaDTO>>(disciplinaService.orderNota(), HttpStatus.OK);
    }

    @GetMapping("/ranking/likes")
    public ResponseEntity<List<DisciplinaLikesDTO>> findAllLikes() {
        return new ResponseEntity<List<DisciplinaLikesDTO>>(disciplinaService.orderLikes(), HttpStatus.OK);
    }

//    @GetMapping("/ranking")
//    public ResponseEntity<List<Disciplina>> findAllRanking() {
//
//        List<Disciplina> novaLista = disciplinaService.findAll();
//
//        Collections.sort(novaLista);
//        Collections.reverse(novaLista);
//        return new ResponseEntity<List<Disciplina>>(novaLista, HttpStatus.OK);
//
//    }
//

//    @PutMapping("/{id}/nome")
//    public ResponseEntity<Disciplina> update(@PathVariable int id, @Valid @RequestBody Disciplina disciplina) {
//
//        try {
//            return new ResponseEntity<Disciplina>(disciplinaService.update(disciplina), HttpStatus.OK);
//        } catch (ArrayIndexOutOfBoundsException error) {
//            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
//
//        }
//
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Disciplina> delete(@PathVariable int id) {
//        try {
//            return new ResponseEntity<Disciplina>(disciplinaService.delete(id), HttpStatus.OK);
//        } catch (ArrayIndexOutOfBoundsException error) {
//            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
//        }
//    }

}
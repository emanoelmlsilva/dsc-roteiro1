package com.example.dsc1.controllers;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.example.dsc1.models.Disciplina;
import com.example.dsc1.services.DisciplinaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable int id) {
        try {
            return new ResponseEntity<Disciplina>(disciplinaService.findById(id), HttpStatus.OK);
        } catch (ArrayIndexOutOfBoundsException error) {
            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/ranking")
    public ResponseEntity<List<Disciplina>> findAllRanking() {

        List<Disciplina> novaLista = disciplinaService.findAll();

        Collections.sort(novaLista);
        Collections.reverse(novaLista);
        return new ResponseEntity<List<Disciplina>>(novaLista, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Disciplina> insert(@Valid @RequestBody Disciplina disciplina) {
        return new ResponseEntity<Disciplina>(disciplinaService.insert(disciplina), HttpStatus.OK);
    }

    @PutMapping("/{id}/nome")
    public ResponseEntity<Disciplina> update(@PathVariable int id, @Valid @RequestBody Disciplina disciplina) {

        try {
            return new ResponseEntity<Disciplina>(disciplinaService.update(disciplina), HttpStatus.OK);
        } catch (ArrayIndexOutOfBoundsException error) {
            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Disciplina> delete(@PathVariable int id) {
        try {
            return new ResponseEntity<Disciplina>(disciplinaService.delete(id), HttpStatus.OK);
        } catch (ArrayIndexOutOfBoundsException error) {
            return new ResponseEntity<Disciplina>(new Disciplina(), HttpStatus.NOT_FOUND);
        }
    }

}
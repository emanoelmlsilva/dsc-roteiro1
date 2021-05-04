package com.example.dsc1.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dsc1.dtos.DisciplinaComentariosDTO;
import com.example.dsc1.dtos.DisciplinaDTO;
import com.example.dsc1.dtos.DisciplinaLikesDTO;
import com.example.dsc1.dtos.DisciplinaNotaDTO;
import com.example.dsc1.models.Comentario;
import com.example.dsc1.models.Disciplina;

import com.example.dsc1.repositories.ComentarioRepository;
import com.example.dsc1.repositories.DisciplinaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @PostConstruct
    public void initDisicplina(){
        if(disciplinaRepository.findAll().size() > 0){
            System.out.println("Disciplinas ja estão cadastrada");
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
            InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
            try {
                List<Disciplina> disciplinas = objectMapper.readValue(inputStream, typeReference);
                disciplinaRepository.saveAll(disciplinas);
                System.out.println("Disciplinas salvas no BD");
            }catch (IOException erro){
                System.out.println("Não foi possivel salvar as disciplinas"+erro.getMessage());
            }
        }

    }

    public List<DisciplinaDTO> findAll() {
        return disciplinaRepository.findAll().stream().map(DisciplinaDTO::new).collect(Collectors.toList());
    }

    public Disciplina insert(Disciplina disciplina) {
       disciplinaRepository.save(disciplina);
        return disciplina;
    }

    public Disciplina findById(Long id) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        if(!disciplinaOptional.isPresent()) throw new ArrayIndexOutOfBoundsException("Disciplina não encontrada");
        return disciplinaOptional.get();
    }

    public Disciplina updateLikes(Long id) throws Exception {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        if(!disciplinaOptional.isPresent()){
            throw new Exception("Disciplina não encontrada");
        }

        Disciplina disciplinaUpdate = disciplinaOptional.get();
        disciplinaUpdate.setLikes(disciplinaUpdate.getLikes() + 1);
        disciplinaRepository.save(disciplinaUpdate);
        return disciplinaUpdate;

    }

    public DisciplinaNotaDTO updataNota(Long id, Double nota) throws Exception {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        if(!disciplinaOptional.isPresent()){
            throw new Exception("Disciplina não encontrada");
        }
        Disciplina disciplinaUpdate = disciplinaOptional.get();
        if(disciplinaUpdate.getNota() == null){
            disciplinaUpdate.setNota(nota);
        }else{
            disciplinaUpdate.setNota((disciplinaUpdate.getNota() + nota) / 2);
        }
        disciplinaRepository.save(disciplinaUpdate);
        return new DisciplinaNotaDTO(disciplinaUpdate);
    }

    public DisciplinaComentariosDTO insertComentario(Long idDisciplina, Comentario comentario) throws Exception{
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(idDisciplina);
        if(!optionalDisciplina.isPresent()){
            throw new Exception("Disciplina não encontrada");
        }
        comentario.setDisciplina(optionalDisciplina.get());
        comentarioRepository.save(comentario);
        return new DisciplinaComentariosDTO(optionalDisciplina.get());
    }

    public List<DisciplinaNotaDTO> orderNota(){
        return disciplinaRepository.findAllByOrderByNotaDesc().stream().map(DisciplinaNotaDTO::new).collect(Collectors.toList());
    }

    public List<DisciplinaLikesDTO> orderLikes(){
        return disciplinaRepository.findAllByOrderByLikesDesc().stream().map(DisciplinaLikesDTO::new).collect(Collectors.toList());
    }
}
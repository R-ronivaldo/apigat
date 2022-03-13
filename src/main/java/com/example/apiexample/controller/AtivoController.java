package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

import com.example.apiexample.model.Ativo;
import com.example.apiexample.respository.AtivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtivoController {
    private final AtivoRepository ativoRepository;

    @Autowired
    public AtivoController(AtivoRepository ativoRepository){
        this.ativoRepository = ativoRepository;
    }

    @GetMapping("/ativos")
    public List<Ativo> getAllAtivos(){
        return ativoRepository.findAll();
    }

    @GetMapping("/ativos/{id}")
    public Ativo getAtivo(@PathVariable("id") Long id){
        Optional<Ativo> ativoFind = this.ativoRepository.findById(id);

        if (ativoFind.isPresent()){
            return ativoFind.get();
        }

        return null;
    }

    @PostMapping("/ativos")
    public Ativo addAtivo(@RequestBody Ativo ativo){
        return this.ativoRepository.save(ativo);
    }
}

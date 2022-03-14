package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

import com.example.apiexample.model.Ativo;
import com.example.apiexample.respository.AtivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtivoController {
    private final AtivoRepository ativoRepository;

    @Autowired
    public AtivoController(AtivoRepository ativoRepository){
        this.ativoRepository = ativoRepository;
    }

    @GetMapping("/ativo")
    public List<Ativo> getAllAtivos(){
        return ativoRepository.findAll();
    }

    @GetMapping("/ativo/{id}")
    public Ativo getAtivo(@PathVariable("id") Long id){
        Optional<Ativo> ativoFind = this.ativoRepository.findById(id);

        if (ativoFind.isPresent()){
            return ativoFind.get();
        }

        return null;
    }

    @GetMapping("/ativos/{name}")
	public ResponseEntity<List<Ativo>> getAtivosByEmail(@RequestParam String email) {
		return new ResponseEntity<List<Ativo>>(ativoRepository.findByEmail(email), HttpStatus.OK);
	}   

    @PostMapping("/ativo")
    public Ativo addAtivo(@RequestBody Ativo ativo){
        return this.ativoRepository.save(ativo);
    }
}

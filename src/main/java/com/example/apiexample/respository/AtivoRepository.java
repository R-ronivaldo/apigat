package com.example.apiexample.respository;

import java.util.List;

import com.example.apiexample.model.Ativo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    public List<Ativo> findByEmail(String email);

	public List<Ativo> findByUser_id( Long user_id);
    
}
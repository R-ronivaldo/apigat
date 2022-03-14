package com.example.apiexample.respository;

import com.example.apiexample.model.Ativo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}
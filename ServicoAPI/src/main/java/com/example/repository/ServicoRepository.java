package com.example.repository;

import com.example.model.Servico;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServicoRepository extends MongoRepository<Servico, String> {
    List<Servico> findByAnimalId(String animalId);
}

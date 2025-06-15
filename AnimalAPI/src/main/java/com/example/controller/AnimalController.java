package com.example.controller;

import com.example.dto.AnimalRequest;
import com.example.repository.AnimalRepository;
import com.example.security.JwtUtil;
import com.example.model.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalRepository repository;
    private final JwtUtil jwtUtil;

    public AnimalController(AnimalRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    private boolean isAuthorized(String token) {
        return token != null && token.startsWith("Bearer ") && jwtUtil.validateToken(token.substring(7));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestHeader("Authorization") String authHeader,
                                       @RequestBody AnimalRequest dto) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        Animal animal = Animal.builder()
                .nome(dto.getNome())
                .especie(dto.getEspecie())
                .raca(dto.getRaca())
                .idade(dto.getIdade())
                .pessoaId(dto.getPessoaId())
                .build();

        return ResponseEntity.ok(repository.save(animal));
    }

    @GetMapping
    public ResponseEntity<?> listar(@RequestHeader("Authorization") String authHeader) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        List<Animal> animal = repository.findAll();
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@RequestHeader("Authorization") String authHeader,
                                         @PathVariable String id) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
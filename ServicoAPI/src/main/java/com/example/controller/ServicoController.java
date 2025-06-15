package com.example.controller;

import com.example.dto.ServicoRequest;
import com.example.repository.ServicoRepository;
import com.example.model.Servico;
import com.example.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoRepository repository;
    private final JwtUtil jwtUtil;

    public ServicoController(ServicoRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    private boolean isAuthorized(String token) {
        return token != null && token.startsWith("Bearer ") && jwtUtil.validateToken(token.substring(7));
    }

    @PostMapping
    public ResponseEntity<?> registrarServico(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody ServicoRequest dto) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        Servico servico = Servico.builder()
                .tipo(dto.getTipo())
                .data(LocalDate.parse(dto.getData()))
                .valor(dto.getValor())
                .status(dto.getStatus())
                .animalId(dto.getAnimalId())
                .build();

        return ResponseEntity.ok(repository.save(servico));
    }

    @GetMapping
    public ResponseEntity<?> listarTudo(@RequestHeader("Authorization") String authHeader) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<?> listarAnimal(@RequestHeader("Authorization") String authHeader,
                                             @PathVariable String animalId) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(repository.findByAnimalId(animalId));
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

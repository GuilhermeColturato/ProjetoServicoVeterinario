package com.example.controller;

import com.example.dto.PessoaRequest;
import com.example.repository.PessoaRepository;
import com.example.model.Pessoa;
import com.example.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository repository;
    private final JwtUtil jwtUtil;

    public PessoaController(PessoaRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    private boolean isAuthorized(String token) {
        return token != null && token.startsWith("Bearer ") && jwtUtil.validateToken(token.substring(7));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestHeader("Authorization") String authHeader,
                                       @RequestBody PessoaRequest dto) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        Pessoa pessoa = Pessoa.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();

        return ResponseEntity.ok(repository.save(pessoa));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar(@RequestHeader("Authorization") String authHeader) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        List<Pessoa> pessoa = repository.findAll();
        return ResponseEntity.ok(pessoa);
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

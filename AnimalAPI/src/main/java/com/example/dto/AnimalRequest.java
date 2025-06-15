package com.example.dto;

import lombok.Data;

@Data
public class AnimalRequest {
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String pessoaId;
}


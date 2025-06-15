package com.example.dto;

import lombok.Data;

@Data
public class ServicoRequest {
    private String tipo;
    private String data; // formato ISO 8601
    private double valor;
    private String status;
    private String animalId;
}

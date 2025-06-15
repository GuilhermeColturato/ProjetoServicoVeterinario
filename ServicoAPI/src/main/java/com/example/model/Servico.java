package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document(collection = "servicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servico {
    @Id
    private String id;
    private String tipo;
    private LocalDate data;
    private double valor;
    private String status;
    private String animalId;
}

package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "animais")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {
    @Id
    private String animalId;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String pessoaId;
}

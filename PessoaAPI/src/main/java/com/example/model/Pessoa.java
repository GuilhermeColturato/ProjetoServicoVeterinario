package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pessoas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pessoa {
    @Id
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
}

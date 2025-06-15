package com.example.dto;

import lombok.Data;

@Data
public class PessoaRequest {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
}

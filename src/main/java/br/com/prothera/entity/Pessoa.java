package br.com.prothera.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Pessoa {

    private String nome;

    private LocalDate dataNascimento;

}

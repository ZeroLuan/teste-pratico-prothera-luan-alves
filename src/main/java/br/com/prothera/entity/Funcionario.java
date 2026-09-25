package br.com.prothera.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Funcionario extends Pessoa {

    private BigDecimal salario;

    private String funcao;

}

package br.com.prothera;

import br.com.prothera.entity.Funcionario;
import br.com.prothera.service.FuncionarioService;
import br.com.prothera.util.FormatadorUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioTest {

    @Test
    void deveAplicarAumentoDe10PorCento() {
        Funcionario funcionario = new Funcionario("Maria", LocalDate.of(2000, 10, 18),
                new BigDecimal("2000.00"), "Operador");

        FuncionarioService service = new FuncionarioService();
        service.aplicarAumento(funcionario, BigDecimal.valueOf(10));

        assertTrue(new BigDecimal("2200.00").compareTo(funcionario.getSalario()) == 0,
                "Salario apos aumento de 10% deveria ser 2200.00");
    }

    @Test
    void deveFormatarSalarioNoPadraoBrasileiro() {
        BigDecimal salario = new BigDecimal("19119.88");

        assertEquals("19.119,88", FormatadorUtil.formatarMoeda(salario));
    }

    @Test
    void deveFormatarDataNoPadraoBrasileiro() {
        LocalDate data = LocalDate.of(1961, 5, 2);

        assertEquals("02/05/1961", FormatadorUtil.formatarData(data));
    }
}

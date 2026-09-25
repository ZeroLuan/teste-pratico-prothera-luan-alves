package br.com.prothera;

import br.com.prothera.entity.Funcionario;
import br.com.prothera.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServiceTest {

    private FuncionarioService service;
    private List<Funcionario> funcionarios;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService();
        funcionarios = new ArrayList<>();
        
        // Dados controlados para o teste
        funcionarios.add(new Funcionario("Zeca", LocalDate.of(1990, 5, 10), new BigDecimal("3000.00"), "Gerente"));
        funcionarios.add(new Funcionario("Ana", LocalDate.of(1980, 2, 20), new BigDecimal("5000.00"), "Diretor"));
        funcionarios.add(new Funcionario("Carlos", LocalDate.of(2000, 8, 15), new BigDecimal("2000.00"), "Gerente"));
    }

    @Test
    void deveAgruparPorFuncao() {
        var mapa = service.agruparPorFuncao(funcionarios);
        
        assertEquals(2, mapa.keySet().size()); // Gerente e Diretor
        assertEquals(2, mapa.get("Gerente").size());
        assertEquals(1, mapa.get("Diretor").size());
    }

    @Test
    void deveBuscarFuncionarioMaisVelho() {
        Funcionario maisVelho = service.buscarFuncionarioMaisVelho(funcionarios);
        
        assertNotNull(maisVelho);
        assertEquals("Ana", maisVelho.getNome()); // Nasceu em 1980
    }

    @Test
    void deveCalcularTotalSalarios() {
        BigDecimal total = service.calcularTotalSalarios(funcionarios);
        
        assertTrue(new BigDecimal("10000.00").compareTo(total) == 0); // 3000 + 5000 + 2000
    }

    @Test
    void deveOrdenarPorNome() {
        List<Funcionario> ordenados = service.ordenarPorNome(funcionarios);
        
        assertEquals("Ana", ordenados.get(0).getNome());
        assertEquals("Carlos", ordenados.get(1).getNome());
        assertEquals("Zeca", ordenados.get(2).getNome());
    }

    @Test
    void deveCalcularQuantidadeDeSalariosMinimos() {
        Funcionario f = funcionarios.get(0); // Zeca ganha 3000
        BigDecimal salarioMinimo = new BigDecimal("1000.00");
        
        BigDecimal qtd = service.calcularSalariosMinimos(f, salarioMinimo);
        
        assertTrue(new BigDecimal("3.00").compareTo(qtd) == 0);
    }
}

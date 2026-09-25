package br.com.prothera.service;

import br.com.prothera.entity.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioService {

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> mapa = new HashMap<>();

        for (Funcionario f : funcionarios) {
            String funcao = f.getFuncao();

            if (!mapa.containsKey(funcao)) {
                mapa.put(funcao, new ArrayList<>());
            }
            mapa.get(funcao).add(f);
        }

        return mapa;
    }

    public Funcionario buscarFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = null;

        for (Funcionario f : funcionarios) {
            if (maisVelho == null || f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = f;
            }
        }

        return maisVelho;
    }

    public BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = BigDecimal.ZERO;

        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario());
        }

        return total;
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        List<Funcionario> ordenados = new ArrayList<>(funcionarios);
        ordenados.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));
        return ordenados;
    }

    public BigDecimal calcularSalariosMinimos(Funcionario funcionario, BigDecimal salarioMinimo) {
        return funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }

    /**
     * Aplica um percentual de aumento sobre o salário de um funcionário.
     */
    public void aplicarAumento(Funcionario funcionario, BigDecimal percentual) {
        BigDecimal fator = percentual.divide(BigDecimal.valueOf(100));
        BigDecimal novoSalario = funcionario.getSalario().add(funcionario.getSalario().multiply(fator));
        funcionario.setSalario(novoSalario);
    }
}

package br.com.prothera;

import br.com.prothera.entity.Funcionario;
import br.com.prothera.factory.FuncionarioFactory;
import br.com.prothera.service.FuncionarioService;
import br.com.prothera.util.FormatadorUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

public class Principal {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final BigDecimal PERCENTUAL_AUMENTO = BigDecimal.valueOf(10);

    public static void main(String[] args) {

        FuncionarioService service = new FuncionarioService();

        // 3.1 – Inserir todos os funcionarios
        List<Funcionario> funcionarios = FuncionarioFactory.criarFuncionarios();

        // 3.2 – Remover o funcionario "Joao"
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNome().equals("João")) {
                funcionarios.remove(i);
                break;
            }
        }

        // 3.3 – Imprimir todos os funcionarios com todas as informacoes
        imprimirSeparador("LISTA DE FUNCIONARIOS");
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }

        // 3.4 – Aplicar 10% de aumento de salario
        for (Funcionario f : funcionarios) {
            service.aplicarAumento(f, PERCENTUAL_AUMENTO);
        }

        imprimirSeparador("APOS AUMENTO DE 10%");
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }

        // 3.5 – Agrupar os funcionarios por funcao em um Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = service.agruparPorFuncao(funcionarios);

        // 3.6 – Imprimir os funcionarios agrupados por funcao
        imprimirSeparador("FUNCIONARIOS AGRUPADOS POR FUNCAO");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println(">> " + entry.getKey() + ":");
            for (Funcionario f : entry.getValue()) {
                System.out.println("   " + formatarFuncionario(f));
            }
            System.out.println();
        }

        // 3.8 – Imprimir os funcionarios que fazem aniversario no mes 10 e 12
        imprimirSeparador("ANIVERSARIANTES - MES 10 E 12");
        for (Funcionario f : funcionarios) {
            int mesNascimento = f.getDataNascimento().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                imprimirFuncionario(f);
            }
        }

        // 3.9 – Imprimir o funcionario com a maior idade (nome e idade)
        imprimirSeparador("FUNCIONARIO COM MAIOR IDADE");
        Funcionario maisVelho = service.buscarFuncionarioMaisVelho(funcionarios);
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");
        }

        // 3.10 – Imprimir a lista de funcionarios por ordem alfabetica
        imprimirSeparador("FUNCIONARIOS EM ORDEM ALFABETICA");
        List<Funcionario> ordenados = service.ordenarPorNome(funcionarios);
        for (Funcionario f : ordenados) {
            imprimirFuncionario(f);
        }

        // 3.11 – Imprimir o total dos salarios dos funcionarios
        imprimirSeparador("TOTAL DOS SALARIOS");
        BigDecimal totalSalarios = service.calcularTotalSalarios(funcionarios);
        System.out.println("Total: " + FormatadorUtil.formatarMoeda(totalSalarios));

        // 3.12 – Imprimir quantos salarios minimos ganha cada funcionario
        imprimirSeparador("SALARIOS MINIMOS POR FUNCIONARIO");
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalariosMinimos = service.calcularSalariosMinimos(f, SALARIO_MINIMO);
            System.out.println(f.getNome() + ": " + qtdSalariosMinimos + " salarios minimos");
        }
    }


    private static String formatarFuncionario(Funcionario f) {
        return "Nome: " + f.getNome()
                + " | Data Nascimento: " + FormatadorUtil.formatarData(f.getDataNascimento())
                + " | Salario: " + FormatadorUtil.formatarMoeda(f.getSalario())
                + " | Funcao: " + f.getFuncao();
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.println(formatarFuncionario(f));
    }

    private static void imprimirSeparador(String titulo) {
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(60));
    }
}
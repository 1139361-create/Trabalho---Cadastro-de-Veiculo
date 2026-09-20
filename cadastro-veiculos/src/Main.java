import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Interface de console do sistema de Cadastro de Veículos.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CadastroVeiculos cadastro = new CadastroVeiculos();

    public static void main(String[] args) {
        try {
            int opcao;
            do {
                exibirMenu();
                opcao = lerInteiro("Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> cadastrarVeiculo();
                    case 2 -> listarVeiculos();
                    case 3 -> consultarVeiculo();
                    case 0 -> System.out.println("\nEncerrando o sistema. Até logo!");
                    default -> System.out.println("\nOpção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        } catch (NoSuchElementException e) {
            System.out.println("\n\nNão foi possível ler a digitação (entrada encerrada).");
            System.out.println("Execute o programa pelo TERMINAL do VS Code (aba \"Terminal\"),");
            System.out.println("e não pelo \"Debug Console\" nem pelo painel \"Output\".");
        } finally {
            scanner.close();
        }
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("======= Cadastro de Veículos OO =======");
        System.out.println("1 - Cadastrar Veículo");
        System.out.println("2 - Listar Veículos");
        System.out.println("3 - Consultar Veículo");
        System.out.println("0 - Sair");
        System.out.println();
    }

    // ---------------------------------------------------------------
    // 1 - Cadastrar
    // ---------------------------------------------------------------
    private static void cadastrarVeiculo() {
        System.out.println("\n--- Cadastrar Veículo ---");
        System.out.println("(deixe um campo em branco para cancelar)\n");

        String marca = lerTexto("Marca: ");
        if (marca.isBlank()) {
            System.out.println("Cadastro cancelado.");
            return;
        }

        String modelo = lerTexto("Modelo: ");
        if (modelo.isBlank()) {
            System.out.println("Cadastro cancelado.");
            return;
        }

        Integer ano = lerAnoValido();
        if (ano == null) {
            System.out.println("Cadastro cancelado.");
            return;
        }

        String placa = lerPlacaDisponivel();
        if (placa == null) {
            System.out.println("Cadastro cancelado.");
            return;
        }

        try {
            cadastro.cadastrar(marca, modelo, ano, placa);
            System.out.println("\nVeículo cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    /** Pede o ano até que seja válido. Retorna null se o usuário cancelar. */
    private static Integer lerAnoValido() {
        while (true) {
            String entrada = lerTexto("Ano (" + CadastroVeiculos.ANO_MINIMO + " a "
                    + CadastroVeiculos.getAnoMaximo() + "): ");
            if (entrada.isBlank()) {
                return null;
            }
            try {
                int ano = Integer.parseInt(entrada.trim());
                if (cadastro.anoValido(ano)) {
                    return ano;
                }
                System.out.println("Ano inválido. Deve estar entre " + CadastroVeiculos.ANO_MINIMO
                        + " e " + CadastroVeiculos.getAnoMaximo() + ".");
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números para o ano.");
            }
        }
    }

    /** Pede a placa até que não exista duplicidade. Retorna null se o usuário cancelar. */
    private static String lerPlacaDisponivel() {
        while (true) {
            String placa = lerTexto("Placa: ");
            if (placa.isBlank()) {
                return null;
            }
            if (cadastro.placaExiste(placa)) {
                System.out.println("Já existe um veículo com essa placa. Informe outra.");
            } else {
                return placa;
            }
        }
    }

    // ---------------------------------------------------------------
    // 2 - Listar
    // ---------------------------------------------------------------
    private static void listarVeiculos() {
        System.out.println("\n--- Veículos Cadastrados ---");
        List<Veiculo> veiculos = cadastro.listar();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        int larguraMarca = "Marca".length();
        int larguraModelo = "Modelo".length();
        for (Veiculo v : veiculos) {
            larguraMarca = Math.max(larguraMarca, v.getMarca().length());
            larguraModelo = Math.max(larguraModelo, v.getModelo().length());
        }

        String formato = "%-3s  %-" + larguraMarca + "s  %-" + larguraModelo + "s  %-4s  %-8s%n";
        System.out.printf(formato, "#", "Marca", "Modelo", "Ano", "Placa");
        System.out.println("-".repeat(3 + 2 + larguraMarca + 2 + larguraModelo + 2 + 4 + 2 + 8));

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            System.out.printf(formato, i + 1, v.getMarca(), v.getModelo(), v.getAno(), v.getPlaca());
        }
        System.out.println("\nTotal: " + veiculos.size() + " veículo(s).");
    }

    // ---------------------------------------------------------------
    // 3 - Consultar
    // ---------------------------------------------------------------
    private static void consultarVeiculo() {
        System.out.println("\n--- Consultar Veículo ---");
        String placa = lerTexto("Informe a placa: ");

        Optional<Veiculo> resultado = cadastro.consultarPorPlaca(placa);

        if (resultado.isPresent()) {
            System.out.println("\nVeículo encontrado:");
            System.out.println(resultado.get());
        } else {
            System.out.println("\nNenhum veículo encontrado com a placa informada.");
        }
    }

    // ---------------------------------------------------------------
    // Utilitários de leitura
    // ---------------------------------------------------------------
    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
}

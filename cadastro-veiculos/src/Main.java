import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Veiculo> veiculos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            mostrarMenu();
            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            if (opcao == 1) {
                cadastrar();
            } else if (opcao == 2) {
                listar();
            } else if (opcao == 3) {
                consultar();
            } else if (opcao == 0) {
                System.out.println("Saindo do sistema...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("======= Cadastro de Veículos OO =======");
        System.out.println("1 - Cadastrar Veículo");
        System.out.println("2 - Listar Veículos");
        System.out.println("3 - Consultar Veículo");
        System.out.println("0 - Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");
    }

    static Veiculo buscarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    static void cadastrar() {
        System.out.print("Marca: ");
        String marca = sc.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine().trim();

        int anoMaximo = LocalDate.now().getYear() + 1;
        int ano = 0;
        boolean anoOk = false;
        while (!anoOk) {
            System.out.print("Ano: ");
            try {
                ano = Integer.parseInt(sc.nextLine().trim());
                if (ano < 1900 || ano > anoMaximo) {
                    System.out.println("Ano inválido! Digite um ano entre 1900 e " + anoMaximo + ".");
                } else {
                    anoOk = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido para o ano.");
            }
        }

        String placa = "";
        boolean placaOk = false;
        while (!placaOk) {
            System.out.print("Placa: ");
            placa = sc.nextLine().trim().toUpperCase();
            if (placa.isEmpty()) {
                System.out.println("A placa não pode ficar vazia.");
            } else if (buscarPorPlaca(placa) != null) {
                System.out.println("Já existe um veículo com essa placa!");
            } else {
                placaOk = true;
            }
        }

        Veiculo v = new Veiculo(marca, modelo, ano, placa);
        veiculos.add(v);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    static void listar() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        System.out.println("--- Veículos cadastrados ---");
        for (Veiculo v : veiculos) {
            v.exibir();
            System.out.println("----------------------------");
        }
    }

    static void consultar() {
        System.out.print("Digite a placa: ");
        String placa = sc.nextLine().trim();

        Veiculo v = buscarPorPlaca(placa);
        if (v == null) {
            System.out.println("Nenhum veículo encontrado com essa placa.");
        } else {
            v.exibir();
        }
    }
}
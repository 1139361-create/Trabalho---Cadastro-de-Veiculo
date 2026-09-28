import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class CadastroVeiculos {

    public static final int ANO_MINIMO = 1900;

    private final List<Veiculo> veiculos = new ArrayList<>();

    public static int getAnoMaximo() {
        return LocalDate.now().getYear() + 1;
    }

    public static String normalizarPlaca(String placa) {
        return placa == null ? "" : placa.trim().replace("-", "").toUpperCase();
    }

    public boolean anoValido(int ano) {
        return ano >= ANO_MINIMO && ano <= getAnoMaximo();
    }

    public boolean placaExiste(String placa) {
        return consultarPorPlaca(placa).isPresent();
    }

    public void cadastrar(String marca, String modelo, int ano, String placa) {
        String placaNormalizada = normalizarPlaca(placa);

        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("A marca não pode ser vazia.");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("O modelo não pode ser vazio.");
        }
        if (placaNormalizada.isEmpty()) {
            throw new IllegalArgumentException("A placa não pode ser vazia.");
        }
        if (!anoValido(ano)) {
            throw new IllegalArgumentException(
                    "Ano inválido. Informe um ano entre " + ANO_MINIMO + " e " + getAnoMaximo() + ".");
        }
        if (placaExiste(placaNormalizada)) {
            throw new IllegalArgumentException(
                    "Já existe um veículo cadastrado com a placa " + placaNormalizada + ".");
        }

        veiculos.add(new Veiculo(marca.trim(), modelo.trim(), ano, placaNormalizada));
    }

    public List<Veiculo> listar() {
        return Collections.unmodifiableList(veiculos);
    }

    public Optional<Veiculo> consultarPorPlaca(String placa) {
        String placaNormalizada = normalizarPlaca(placa);
        return veiculos.stream()
                .filter(v -> v.getPlaca().equals(placaNormalizada))
                .findFirst();
    }
}

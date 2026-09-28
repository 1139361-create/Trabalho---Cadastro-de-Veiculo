public class Veiculo {

    private final String marca;
    private final String modelo;
    private final int ano;
    private final String placa;

    public Veiculo(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return String.format("Marca: %-12s | Modelo: %-15s | Ano: %d | Placa: %s",
                marca, modelo, ano, placa);
    }
}

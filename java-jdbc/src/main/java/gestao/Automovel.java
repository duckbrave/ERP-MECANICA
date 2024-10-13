package gestao;

public class Automovel {

    private long idCliente;
    private String placa;
    private long anoCarro;
    private String marca;  // Ajuste para camelCase
    private String modelo;
    private int codCarro;  // Mantido como int se o código do carro não for muito grande

    // Construtor
    public Automovel(long idCliente, String placa, long anoCarro, String marca, String modelo, int codCarro) {
        this.idCliente = idCliente;
        this.placa = placa;
        this.anoCarro = anoCarro;
        this.marca = marca;  // Ajuste para camelCase
        this.modelo = modelo;
        this.codCarro = codCarro;
    }

    // Getters e Setters
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public long getAnoCarro() {
        return anoCarro;
    }

    public void setAnoCarro(long anoCarro) {
        this.anoCarro = anoCarro;
    }

    public String getMarca() {
        return marca;  // Ajuste para camelCase
    }

    public void setMarca(String marca) {  // Ajuste para camelCase
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCodCarro() {
        return codCarro;
    }

    public void setCodCarro(int codCarro) {
        this.codCarro = codCarro;
    }
}
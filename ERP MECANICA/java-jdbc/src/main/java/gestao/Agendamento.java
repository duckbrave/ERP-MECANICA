package gestao;

import java.time.LocalDateTime;

public class Agendamento {

    private int id;
    private int idCliente;
    private LocalDateTime dataAtendimento;
    private String modelo;
    private String marca;
    private String nomeCliente;
    private int idCarro;
    private int situacao; // Atribuir tipo apropriado, como int para representar estados
    private String observacao;

    public Agendamento(int id, int idCliente, LocalDateTime dataAtendimento, String modelo, String marca, String nomeCliente, int idCarro, int situacao, String sintoma) {
        this.id = id;
        this.idCliente = idCliente;
        this.dataAtendimento = dataAtendimento;
        this.modelo = modelo;
        this.marca = marca;
        this.nomeCliente = nomeCliente;
        this.idCarro = idCarro;
        this.situacao = situacao;
        this.setObservacao(sintoma);
    }
    public Agendamento() {
		this.situacao = 0;
	}
    
    public Agendamento(Object object, int idCliente2, LocalDateTime now, String modelo2, String marca2, String nome,
			int codCarro, long id2, String sintoma2) {
		// TODO Auto-generated constructor stub
	}
	// Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

   
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
package gestao.estoque;

import java.time.LocalDateTime;

public class Produto {

    private long codigo;
    private String nome;
    private long quantidade;
    private boolean estado;
    private float prcCusto;
    private float prcVenda;
    private String embalagem;
    private LocalDateTime data;
    private String prodser;

    // Construtor com parâmetros, estado sempre será true e data será fornecida
    public Produto(long codigo, String nome, long quantidade, float prcCusto, float prcVenda, String embalagem, LocalDateTime data, String prodser) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.estado = true; // Estado sempre será true
        this.prcCusto = prcCusto;
        this.prcVenda = prcVenda;
        this.embalagem = embalagem;
        this.data = data;
        this.prodser = prodser;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrcCusto() {
        return prcCusto;
    }

    public void setPrcCusto(float prcCusto) {
        this.prcCusto = prcCusto;
    }

    public float getPrcVenda() {
        return prcVenda;
    }

    public void setPrcVenda(float prcVenda) {
        this.prcVenda = prcVenda;
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getProdser() {
        return prodser;
    }

    public void setProdser(String prodser) {
        this.prodser = prodser;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

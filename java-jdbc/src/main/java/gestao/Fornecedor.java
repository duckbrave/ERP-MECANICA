package gestao;

public class Fornecedor {
    private long cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;
    private String representante;
    private long telefone;
    private String email;
    private String rua;
    private String bairro;
    private String cidade;
    private String nome; // Novo atributo para o nome do fornecedor

    // Construtor
    public Fornecedor(long cnpj, String razaoSocial, String inscricaoEstadual, String representante,
                      long telefone, String email, String rua, String bairro, String cidade, String nome) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
        this.representante = representante;
        this.telefone = telefone;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.nome = nome; // Atribuição do nome
    }

    // Getters
    public long getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public String getRepresentante() {
        return representante;
    }

    public long getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }
    
    public String getNome() { // Getter para o nome
        return nome;
    }
}

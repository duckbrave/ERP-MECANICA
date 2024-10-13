package gestao;

public class Cliente {
    private long cnpj; // Alterado de CPF para CNPJ
    private long telefone;
    private String nome;
    private String email;
    private String rua;
    private String bairro;
    private String cidade;
    private String nomeFornecedor;
    private String RazaoSocial;
    private long ieCli;
    private String representante;
    
    
    
    
    
    
   

    public Cliente(String nomeFornecedor, String razaoSocial, long ieCli, String representante) {
		super();
		this.nomeFornecedor = nomeFornecedor;
		RazaoSocial = razaoSocial;
		this.ieCli = ieCli;
		this.representante = representante;
	}

	// Construtor sem o ID
    public Cliente(long cnpj, long telefone, String nome, String email, String rua, String bairro, String cidade) {
        this.cnpj = cnpj; // Atribuição para o novo campo
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        
        
    }

    // Getters e setters
    public long getCnpj() { // Alterado de getCpf para getCnpj
        return cnpj;
    }

    public long getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
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

    // Setters (opcional, dependendo de como você quer usar)
    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getRazaoSocial() {
		return RazaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}

	public long getIeCli() {
		return ieCli;
	}

	public void setIeCli(long ieCli) {
		this.ieCli = ieCli;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}
    
    
}

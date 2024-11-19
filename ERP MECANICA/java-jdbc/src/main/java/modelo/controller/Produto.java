package modelo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import gestao.Usuario;

public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private int quantidade;
	private Categoria categoria;
	private Usuario usuario;
	private LocalDateTime datahoracriacao;
	
	
	public Produto(int id, String nome, String descricao, BigDecimal preco, int quantidade, Categoria categoria,
			Usuario usuario, LocalDateTime datahoracriacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.usuario = usuario;
		this.datahoracriacao = datahoracriacao;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public LocalDateTime getDatahoracriacao() {
		return datahoracriacao;
	}


	public void setDatahoracriacao(LocalDateTime datahoracriacao) {
		this.datahoracriacao = datahoracriacao;
	}
	
	
	
	
}

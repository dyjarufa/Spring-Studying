package com.benicio.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja {

	@NotBlank(message = "SKU obrigatório") // Verifica se esta vazio ou com espaços em branco
	private String sku;

	@NotBlank(message = "Nome obrigatório")
	private String nome;

	@Size(min = 1 ,max = 50, message = "Deve estar entre  1 e 50")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

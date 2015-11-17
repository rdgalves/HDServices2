package br.com.hdservices.model;

public enum EstadoChamado {

	ABERTO("Aberto"), ENCERRADO("Encerra");

	private String descricao;

	EstadoChamado(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

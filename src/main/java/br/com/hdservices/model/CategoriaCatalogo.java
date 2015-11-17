package br.com.hdservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoriaCatalogo {

	private Integer idCategoriaCatalogo;
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdCategoriaCatalogo() {
		return idCategoriaCatalogo;
	}

	public void setIdCategoriaCatalogo(Integer idCategoriaCatalogo) {
		this.idCategoriaCatalogo = idCategoriaCatalogo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

package br.com.hdservices.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoCatalogo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idTipoCatalogo;
	private String descricao;
//	private List<Catalogo> catalogos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdTipoCatalogo() {
		return idTipoCatalogo;
	}

	public void setIdTipoCatalogo(Integer idTipoCatalogo) {
		this.idTipoCatalogo = idTipoCatalogo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
//
//	public List<Catalogo> getCatalogos() {
//		return catalogos;
//	}
//
//	public void setCatalogos(List<Catalogo> catalogos) {
//		this.catalogos = catalogos;
//	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		TipoCatalogo other = (TipoCatalogo) obj;
		return getIdTipoCatalogo() == other.getIdTipoCatalogo();
	}

}

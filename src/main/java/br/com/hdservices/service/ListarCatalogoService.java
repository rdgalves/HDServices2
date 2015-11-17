package br.com.hdservices.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.TipoCatalogo;
import br.com.hdservices.repository.Catalogos;



@Model
public class ListarCatalogoService implements Serializable{

	private static final long serialVersionUID = -5344202657124629641L;
	
	@javax.inject.Inject
	private Catalogos catalogos;

	public List<Catalogo> catalogosDe(TipoCatalogo tipo) {
		return catalogos.catalogosDe(tipo);
	}

}

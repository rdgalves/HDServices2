package br.com.hdservices.service;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.repository.Catalogos;

@Model
public class CadastroCatalogoService implements Serializable {

	private static final long serialVersionUID = -7452543456101716474L;

	@Inject
	private Catalogos catalogos;

	public void salvar(Catalogo catalogo) {

		Catalogo catalogoExistente = catalogos.porId(catalogo.getIdCatalogo());

		if (catalogoExistente != null && !catalogoExistente.equals(catalogo)) {
			throw new NegocioException("Catalogo já cadastrado!");
		}

		catalogos.salvar(catalogo);

	}

}

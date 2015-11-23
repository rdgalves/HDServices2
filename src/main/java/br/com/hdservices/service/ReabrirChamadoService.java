package br.com.hdservices.service;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.hdservices.model.Chamado;
import br.com.hdservices.repository.Chamados;

@Model
public class ReabrirChamadoService implements Serializable {

	private static final long serialVersionUID = 1482818454670569010L;

	@Inject
	private Chamados chamados;

	public void salvar(Chamado chamado) {

		if (chamado.getSituacao() == "ENCERRADO") {
			chamado.setSituacao("ABERTO");
		}

		chamados.salvar(chamado);
	}

}

package br.com.hdservices.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.hdservices.model.Chamado;
import br.com.hdservices.repository.Chamados;

@Model
public class ListarFilaAtendimentoService implements Serializable {

	private static final long serialVersionUID = 778463682747479100L;

	@Inject
	private Chamados chamados;

	public List<Chamado> listarChamadosAbertos() {
		return chamados.listarChamadosAbertos();
	}

	public List<Chamado> listarChamadosPorEspecialista(Chamado especialista) {
		return chamados.listarChamadosPorEspecialista(especialista);
	}

}

package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.AbrirChamadoService;
import br.com.hdservices.service.ListarFilaAtendimentoService;

@Model
@ManagedBean
public class FilaAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private ListarFilaAtendimentoService filaAtendimentoService;

	@Inject
	private AbrirChamadoService abrirChamadoService;

	private Chamado chamado;
	private List<Chamado> chamados;
	private boolean check;

	public FilaAtendimentoBean() {

	}

	public String adicionaAtendente() {
		if (check == true) {
			chamado.setEspecialista(SessionContext.getInstance()
					.getUsuarioLogado());

			abrirChamadoService.salvar(chamado);
		} else {
			return "FilaAtendimento";
		}
		return "FilaAtendimento";

	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	private void limpar() {
		chamado = new Chamado();
	}

	@PostConstruct
	public void init() {
		try {
			this.chamados = filaAtendimentoService.listarChamadosAbertos();
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

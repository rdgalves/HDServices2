package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Acao;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.ListarFilaChamadoService;
import br.com.hdservices.service.GerenciarAcaoService;

@Model
@ManagedBean(name = "SelecaoView")
public class AcompanharChamadosBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private ListarFilaChamadoService listarFilaChamadoService;

	@Inject
	private GerenciarAcaoService registrarAcaoService;

	private Acao acao;
	private Chamado chamado;
	private List<Chamado> chamados;
	private Chamado chamadoSelecionado;

	public AcompanharChamadosBean() {

	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
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
		acao = new Acao();
	}

	public void salvarAcao() {
		Date dataAtual = new Date();
		acao.setDataRegistro(dataAtual);
		acao.setEspecialista(SessionContext.getInstance().getUsuarioLogado());
//		acao.setChamado(chamadoSelecionado);

		registrarAcaoService.salvar(acao);
	}


	@PostConstruct
	public void init() {
		try {
			this.chamados = listarFilaChamadoService
					.listarChamadosPorEspecialista(SessionContext.getInstance()
							.getUsuarioLogado());
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

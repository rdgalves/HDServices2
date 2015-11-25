package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Acao;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.ListarFilaChamadoService;
import br.com.hdservices.service.GerenciarAcaoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean(name = "SelecaoView")
@SessionScoped
public class AcompanharChamadosBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private ListarFilaChamadoService listarFilaChamadoService;

	@Inject
	private GerenciarAcaoService acaoService;

	private Acao acao;
	private Chamado chamado;
	private List<Chamado> chamados;
	private Chamado chamadoSelecionado;
	private List<Acao> acoes;

	public AcompanharChamadosBean() {

	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
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
		SessionContext.getInstance().setChamadoSelecionado(chamadoSelecionado);
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
		acao.setChamado(SessionContext.getInstance().getChamadoSelecionado());

		acaoService.salvar(acao);

		FacesUtil.addInfoMessage("Ação Registrada com sucesso");
		SessionContext.getInstance().setChamadoSelecionado(null);
		;
	}

	public void listarPorChamado() {
		acoes = acaoService.listarAcaoPorChamado(SessionContext.getInstance()
				.getChamadoSelecionado());
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

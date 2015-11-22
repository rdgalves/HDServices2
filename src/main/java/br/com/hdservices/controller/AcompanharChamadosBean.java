package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.SessionContext;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.service.ListarFilaChamadoService;

@Model
@ManagedBean
public class AcompanharChamadosBean implements Serializable {

	private static final long serialVersionUID = 520030162362262378L;

	@Inject
	private ListarFilaChamadoService listarFilaChamadoService;

	private Chamado chamado;
	private List<Chamado> chamados;
	private boolean check;
	private String[] valoresChamado;

	public AcompanharChamadosBean() {

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

	public String[] getValoresChamado() {
		return valoresChamado;
	}

	public void setValoresChamado(String[] valoresChamado) {
		this.valoresChamado = valoresChamado;
	}

	private void limpar() {
		chamado = new Chamado();
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

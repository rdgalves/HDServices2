package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.model.Chamado;
import br.com.hdservices.repository.Chamados;
import br.com.hdservices.repository.filter.ChamadoFilter;
import br.com.hdservices.service.ReabrirChamadoService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean
@ViewScoped
public class ReabrirChamadoBean implements Serializable {

	private static final long serialVersionUID = 6494970003382119130L;

	@Inject
	private ReabrirChamadoService reabrirChamadoService;

	@Inject
	private Chamados chamados;

	private Chamado chamadoSelecionado;
	private ChamadoFilter filtro;
	private List<Chamado> chamadoFiltrado;

	public ReabrirChamadoBean() {
		filtro = new ChamadoFilter();
		chamadoFiltrado = new ArrayList<>();
		chamadoSelecionado = null;
	}

	public ChamadoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ChamadoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Chamado> getChamadoFiltrado() {
		return chamadoFiltrado;
	}

	public void setChamadoFiltrado(List<Chamado> chamadoFiltrado) {
		this.chamadoFiltrado = chamadoFiltrado;
	}

	public void pesquisar() {
		chamadoFiltrado = chamados.filtrados(filtro);
	}

	public String reabrirChamado() {
		reabrirChamadoService.salvar(chamadoSelecionado);
		FacesUtil.addInfoMessage("Chamado reaberto!");
		return "index";

	}

	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}

	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
	}

	@PostConstruct
	public void init() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

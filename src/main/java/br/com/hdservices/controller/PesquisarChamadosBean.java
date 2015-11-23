package br.com.hdservices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.hdservices.model.Chamado;
import br.com.hdservices.model.EstadoChamado;
import br.com.hdservices.repository.Chamados;
import br.com.hdservices.repository.filter.ChamadoFilter;

@Model
@ManagedBean
@ViewScoped
public class PesquisarChamadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Chamados chamados;

	private ChamadoFilter filtro;
	private List<Chamado> chamadosFiltrados;

	public PesquisarChamadosBean() {
		filtro = new ChamadoFilter();
		chamadosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		chamadosFiltrados = chamados.filtrados(filtro);
	}
	
	public EstadoChamado[] getEstadoChamados(){
		return EstadoChamado.values();
		
	}

	public ChamadoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ChamadoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Chamado> getChamadosFiltrados() {
		return chamadosFiltrados;
	}

}

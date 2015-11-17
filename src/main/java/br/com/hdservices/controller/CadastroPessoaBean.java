package br.com.hdservices.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices.service.CadastroPessoaService;
import br.com.hdservices.util.jsf.FacesUtil;

@Model
@ManagedBean

public class CadastroPessoaBean implements Serializable {

	private static final long serialVersionUID = -1542160936720433882L;

	@Inject
	private CadastroPessoaService cadastroPessoaService;

	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;

		if (this.pessoa != null) {
			this.pessoa.getPerfil();
			this.pessoa.getStatus();
		}
	}

	private void limpar() {
		//pessoa = new Pessoa();
		setPessoa(new Pessoa());
	}

	public boolean isEditando() {
		if (getPessoa() != null && getPessoa().getMatricula() != null) {
			return true;
		}
		return false;
	}

	public String salvar() {
		cadastroPessoaService.salvar(pessoa);
		limpar();
		FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
		return "CadastroPessoa";
	}

	@PostConstruct
	public void init() {
		try {
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

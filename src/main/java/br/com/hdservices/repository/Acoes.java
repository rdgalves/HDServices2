package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;

import br.com.hdservices.model.Acao;
import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Chamado;

@Stateless
public class Acoes extends BaseRepository {

	/**
	 * Salvar uma {@link Catalogo}
	 * 
	 * @param pessoa
	 *            {@link Catalogo}
	 * @return {@link Catalogo}
	 */

	public void salvar(Acao acao) {
		em.getTransaction().begin();
		try {
			em.merge(acao);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public List<Acao> listarAcaoPorChamado(Chamado numeroChamado) {
		return (List<Acao>) em
				.createQuery("from Acao where chamado = :numeroChamado",
						Acao.class)
				.setParameter("numeroChamado", numeroChamado)
				.getResultList();
	}

	// public Chamado porNumeroChamado(Integer numeroChamado) {
	// try {
	// Chamado chamado = em.find(Chamado.class, numeroChamado);
	// return chamado;
	// } catch (NoResultException nre) {
	// return null;
	// }
	// }
	//

}

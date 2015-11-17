package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.hdservices.model.Catalogo;
import br.com.hdservices.model.Chamado;
import br.com.hdservices.repository.filter.ChamadoFilter;

@Stateless
public class Chamados extends BaseRepository {

	/**
	 * Salvar uma {@link Catalogo}
	 * 
	 * @param pessoa
	 *            {@link Catalogo}
	 * @return {@link Catalogo}
	 */

	public void salvar(Chamado chamado) {
		em.getTransaction().begin();
		try {
			em.merge(chamado);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public Chamado porNumeroChamado(Integer numeroChamado) {
		try {
			Chamado chamado = em.find(Chamado.class, numeroChamado);
			return chamado;
		} catch (NoResultException nre) {
			return null;
		}
	}

	public List<Chamado> listarChamadosAbertos() {
		return (List<Chamado>) em.createQuery(
				"from Chamado where situacao = 'ABERTO' and especialista = null", Chamado.class)
				.getResultList();
	}

	public List<Chamado> listarChamadosPorEspecialista(Chamado especialista) {
		return (List<Chamado>) em
				.createQuery("from Chamado where especialista = :especialista",
						Chamado.class)
				.setParameter("especialista", especialista).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Chamado> filtrados(ChamadoFilter filtro) {
		Session session = this.em.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Chamado.class)
		// fazemos uma associação (join) com cliente e nomeamos como "c"
				.createAlias("relator", "r")
				// fazemos uma associação (join) com vendedor e nomeamos como
				// "v"
				.createAlias("atendente", "a");

		criteria.add(Restrictions.ge("numeroChamado", filtro.getNumeroDe()));
		criteria.add(Restrictions.le("numeroChamado", filtro.getNumeroAte()));

		if (filtro.getDataCriacaoDe() != null) {
			criteria.add(Restrictions.ge("dataCriacao",
					filtro.getDataCriacaoDe()));
		}

		if (filtro.getDataCriacaoAte() != null) {
			criteria.add(Restrictions.le("dataCriacao",
					filtro.getDataCriacaoAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeRelator())) {
			// acessamos o nome do cliente associado ao pedido pelo alias "c",
			// criado anteriormente
			criteria.add(Restrictions.ilike("r.nome", filtro.getNomeRelator(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNomeAtendente())) {
			// acessamos o nome do vendedor associado ao pedido pelo alias "v",
			// criado anteriormente
			criteria.add(Restrictions.ilike("a.nome",
					filtro.getNomeAtendente(), MatchMode.ANYWHERE));
		}

		if (filtro.getEstado() != null && filtro.getEstado().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes
			// da enum StatusPedido
			criteria.add(Restrictions.in("ssituacao", filtro.getEstado()));
		}

		return criteria.addOrder(Order.asc("id")).list();
	}

}

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
import br.com.hdservices.model.Pessoa;
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

	public List<Chamado> listaPorNumero(Integer numeroChamado) {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where numeroChamado = :numeroChamado",
						Chamado.class)
				.setParameter("numeroChamado", numeroChamado).getResultList();
	}

	public List<Chamado> listarChamadosAbertos() {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where situacao = 'ABERTO' and especialista = null",
						Chamado.class).getResultList();
	}

	public List<Chamado> listarChamadosPorEspecialista(Pessoa pessoa) {
		return (List<Chamado>) em
				.createQuery("from Chamado where especialista = :especialista",
						Chamado.class).setParameter("especialista", pessoa)
				.getResultList();
	}

	public List<Chamado> listarChamadosPorRelator(Pessoa pessoa) {
		return (List<Chamado>) em
				.createQuery(
						"from Chamado where relator = :relator and situacao = 'ABERTO'",
						Chamado.class).setParameter("relator", pessoa)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Chamado> filtrados(ChamadoFilter filtro) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Chamado.class)
				.createAlias("relator", "r").createAlias("especialista", "e");

		if (filtro.getNumeroDe() != 0) {
			criteria.add(Restrictions.eq("numeroChamado",
					filtro.getNumeroChamado()));

		}
		if (filtro.getNumeroDe() != 0) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// filtro.numeroDe
			criteria.add(Restrictions.ge("numeroChamado", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != 0) {
			// id deve ser menor ou igual (le = lower or equal) a
			// filtro.numeroDe
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeRelator())) {
			criteria.add(Restrictions.ilike("r.relator",
					filtro.getNomeRelator(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNomeAtendente())) {
			criteria.add(Restrictions.ilike("e.especialista",
					filtro.getNomeAtendente(), MatchMode.ANYWHERE));
		}

		// if (filtro.getStatuses() != null && filtro.getStatuses().length > 0)
		// {
		// // adicionamos uma restrição "in", passando um array de constantes da
		// enum StatusPedido
		// criteria.add(Restrictions.in("status", filtro.getStatuses()));
		// }

		return criteria.addOrder(Order.asc("numeroChamado")).list();

	}
}

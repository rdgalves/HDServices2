package br.com.hdservices.repository;

import java.util.List;

import javax.ejb.Stateless;

import br.com.hdservices.model.TipoCatalogo;

@Stateless
public class TiposCatalogo extends BaseRepository {

	public TipoCatalogo buscar(Integer id) {
		return em.find(TipoCatalogo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoCatalogo> listar() {
		return em.createQuery("select t from TipoCatalogo t").getResultList();
	}

	public TipoCatalogo salvar(TipoCatalogo tipo) {
		em.getTransaction().begin();
		try {
			em.persist(tipo);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		return tipo;
	}

	// public List<Catalogo> catalogosDe(TipoCatalogo tipoCatalogo) {
	// return em
	// .createQuery("from Catalogo where tipo = :tipo", Catalogo.class)
	// .setParameter("tipo", tipoCatalogo).getResultList();
	//
	// }

	//
	// @SuppressWarnings("unchecked")
	// public List<Catalogo> listarPorTipo(String tipo) {
	// List<Catalogo> catalogos = em
	// .createQuery(
	// "select c from Catalogo c where tipo.idTipoCatalogo = :tipo")
	// .setParameter("tipo", tipo).getResultList();
	// return catalogos;
	// }
	//
	// @SuppressWarnings("unchecked")
	// public List<Catalogo> filtrados(CatalogoFilter filtro) {
	// Session session = em.unwrap(Session.class);
	// Criteria criteria = session.createCriteria(Catalogo.class);
	//
	// if (StringUtils.isNotBlank(filtro.getTipo())) {
	// criteria.add(Restrictions.ilike("tipo", filtro.getTipo(),
	// MatchMode.ANYWHERE));
	// }
	//
	// return criteria.addOrder(Order.asc("tipo")).list();
	// }

}

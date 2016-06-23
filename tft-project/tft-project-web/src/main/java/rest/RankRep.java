package rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Modele.Rank;

public class RankRep {
	@Inject
	private EntityManager em;

	public List<Rank>displayRank() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rank> criteria = cb.createQuery(Rank.class);
		Root<Rank> rank = criteria.from(Rank.class);
		criteria.select(rank);
		return em.createQuery(criteria).getResultList();
	}
}

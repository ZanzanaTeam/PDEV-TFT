package services.impl.competition;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Match;
import services.interfaces.competition.ICompetitionServicesRemote;

@Stateless
public class CompetitionServices implements ICompetitionServicesRemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Match> findMatchByCompetition(Integer idCompetition ) {
		String jpql = "select m from match_game m where m.competition_id=: param ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idCompetition);
		return query.getResultList();
	}

}

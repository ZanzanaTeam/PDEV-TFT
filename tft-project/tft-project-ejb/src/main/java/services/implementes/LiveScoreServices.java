package services.implementes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.MatchSingle;
import services.interfaces.LiveScoreServicesLocal;
import services.interfaces.LiveScoreServicesRemote;

/**
 * Session Bean implementation class LiveScoreServices
 */
@Stateless
@LocalBean
public class LiveScoreServices implements LiveScoreServicesRemote, LiveScoreServicesLocal {

	@PersistenceContext
	EntityManager entityManager;

	public LiveScoreServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MatchSingle> getMatchByDate(Date date) {
		String jpql = "select e from MatchSingle e where e.dateMatch BETWEEN :startDate AND :endDate";
		Query query = entityManager.createQuery(jpql);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDate = cal.getTime();

		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		Date endDate = date;
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.getResultList();
	}

	@Override
	public void updateLiveScoreOfMatch(int id, String liveScore) {

		MatchSingle match = entityManager.find(MatchSingle.class, id);
		match.setLiveScore(liveScore);
		entityManager.merge(match);
	}

}

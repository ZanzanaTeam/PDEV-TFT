package services.implementes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Jeu;
import domain.Match;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.SetMatch;
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

	public void addSetToMatch(SetMatch setMatch, Integer match_id) {
		setMatch.setMatch(entityManager.find(Match.class, match_id));
		entityManager.merge(setMatch);
	}

	public void addJeuToSet(Jeu jeu, Integer set_id) {
		jeu.setSet(entityManager.find(SetMatch.class, set_id));
		entityManager.merge(jeu);
	}

	public void addPointToJeu(Point point, Integer jeu_id, Integer player_id) {

		point.setJeu(entityManager.find(Jeu.class, jeu_id));
		
		entityManager.merge(point);
	}

}

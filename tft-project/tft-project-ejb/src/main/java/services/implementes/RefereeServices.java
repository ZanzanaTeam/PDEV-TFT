package services.implementes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Match;
import domain.Referee;
import services.interfaces.RefereeServicesLocal;
import services.interfaces.RefereeServicesRemote;

@Stateless
public class RefereeServices implements RefereeServicesLocal, RefereeServicesRemote {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Referee> findRefereeByWord(String word) {
		List<Referee> lists = null;
		try {
			String jpql = "select e from Referee e where e.fullName like :fullName";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("fullName", "%" + word + "%");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findRefereeByWord");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

	@Override
	public List<Match> findMatchByReferee(Referee referee) {

		List<Match> lists = null;
		try {
			String jpql = "select e from Match e where e.referee = :referee";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("referee", referee);
			lists = query.getResultList();
		} catch (Exception ee) {
			System.out.println("Catch findRefereeByWord");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

}

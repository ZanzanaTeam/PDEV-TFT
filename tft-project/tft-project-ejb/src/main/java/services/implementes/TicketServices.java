package services.implementes;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Match;
import domain.Ticket;
import services.interfaces.TicketServicesLocal;
import services.interfaces.TicketServicesRemote;

@Stateless
public class TicketServices implements TicketServicesLocal, TicketServicesRemote {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Ticket> findTicketByMatch(Match match) {
		List<Ticket> lists = null;
		try {
			String jpql = "select t from Ticket t where t.match = :match";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("match", "match");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findTicketByMatch");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

	@Override
	public List<Match> findNextMatchs() {
		List<Match> lists = null;
		try {
			String jpql = "select m from Match m where m.dateMatch = :dateMatch";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("dateMatch", new Date());
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findNextMatchs");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

}

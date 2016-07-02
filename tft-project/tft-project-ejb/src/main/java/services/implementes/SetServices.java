package services.implementes;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Jeu;
import domain.Match;
import domain.Point;
import domain.SetMatch;
import services.interfaces.SetServicesLocal;
import services.interfaces.SetServicesRemote;

@Stateless
public class SetServices implements SetServicesRemote, SetServicesLocal {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public String addSet(SetMatch set) {
		System.out.println("Begin add");
		Match match = entityManager.find(Match.class, set.getMatch().getId());
		if (match != null)
			System.out.println(match);
		else
			System.out.println("match nulllllllllll");
		set.setMatch(match);
		entityManager.persist(set);
		return "success";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SetMatch> findAllSets(Integer idMatch) {
		List<SetMatch> listSets = null;
		String jpql = "select c from set_match c where match_id = :idMatch";

		// where c.match_id = :id"
		try {
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idMatch", idMatch);
			listSets = query.getResultList();

		} catch (Exception e) {
			System.out.println("Message" + e.getMessage() + "  ***** ");
			System.out.println("bad credentials!!!");
		}
		return listSets;
	}

	public  List<Integer> findPointSet(Integer idSet, Integer idPlayer){
		//SetMatch set = findAllSets(1).get(0);
		List<Integer> countPoint = null ; 
//	String jpql = "select count (p.id) from "+Point.class.getName()+" p , "
//	+ Jeu.class.getName()+ " jeu  where p.jeu = :jeu = (select s from "+SetMatch.class.getName()+" where s.id = :idSet)"
//	//+ "and jeu.set =:idSet and p.player_id=:idPlayer "
//	+ "GROUP BY p.id"; 
//		
		String jpql ="select count(p.id) from "+Point.class.getName()+" p join " ; 
		
		try {
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idSet", idSet);
		//	query.setParameter("idPlayer", idPlayer);
			countPoint = query.getResultList();

		} catch (Exception e) {
			System.out.println("Message" +e.getMessage()+"  ***** ");
			System.out.println("bad credentials!!!");
		}
			return countPoint ; 
		
	}

}

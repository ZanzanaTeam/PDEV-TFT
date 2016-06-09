package services.implementes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Player;
import services.interfaces.PlayerServicesLocal;
import services.interfaces.PlayerServicesRemote;

@Stateless
public class PlayerServices implements PlayerServicesLocal, PlayerServicesRemote {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Player> findPlayerByWord(String word) {
		List<Player> lists = null;
		try {
			String jpql = "select e from Player e where e.fullName like :fullName";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("fullName", "%"+word+"%");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findPlayerByWord");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

}

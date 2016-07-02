package services.implementes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.MatchSingle;
import domain.Player;
import domain.Point;
import enumeration.PointType;
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
			query.setParameter("fullName", "%" + word + "%");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findPlayerByWord");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MatchSingle> findMatchByPlayer(Player player, Integer limit, Integer offset) {
		List<MatchSingle> matchs = new ArrayList<>();
		try {
			String jpql = "select m from " + MatchSingle.class.getSimpleName()
					+ " m where m.player = :player or m.player2 = :player2 ";

			if (limit == null || limit < 0) {
				limit = 0;
			}
			if (offset == null || offset <= 0) {
				offset = 1;
			}

			Query query = entityManager.createQuery(jpql).setFirstResult(offset).setMaxResults(limit);
			query.setParameter("player", player);
			query.setParameter("player2", player);
			matchs = query.getResultList();
			//
		} catch (Exception e) {
			System.out.println("Erreur match by player");
		}
		return matchs;
	}

	@Override
	public List<MatchSingle> findFaceToFace(Player player1, Player player2, Integer limit, Integer offset) {
		List<MatchSingle> matchs = new ArrayList<>();
		try {
			String jpql = "select m from " + MatchSingle.class.getSimpleName()
					+ " m where (m.player = :player and m.player2 = :player2) or (m.player = :player2 and m.player2= :player)  ";

			if (limit == null || limit < 0) {
				limit = 0;
			}
			if (offset == null || offset <= 0) {
				offset = 1;
			}

			Query query = entityManager.createQuery(jpql);
			query.setParameter("player", player1);
			query.setParameter("player2", player2);
			matchs = query.getResultList();
			//
		} catch (Exception e) {
			System.out.println("Erreur match by player");
		}
		return matchs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<PointType, Object> findSkills(Player player, Integer limit, String order) {
		Map<PointType, Object> result = new HashMap<PointType, Object>();
		// try {
		String jpql = "select p.pointtype, Count(p.pointtype) as total from " + Point.class.getSimpleName()
				+ " p where p.player_id = :player " + "  group by p.pointtype order by total DESC";
		if (limit == null || limit < 0) {
			limit = 0;
		}

		Query query = entityManager.createNativeQuery(jpql).setMaxResults(5).setFirstResult(0);
		query.setParameter("player", player.getId());

		List<Object[]> list = query.getResultList();
		Integer total = Integer.valueOf(entityManager
				.createNativeQuery("select Count(p.pointtype) as total from " + Point.class.getSimpleName()
						+ " p where p.player_id = :player ")
				.setParameter("player", player.getId()).getResultList().get(0).toString());
		for (Object[] row : list) {
			System.out.println("Param " + row[0].toString() + " = " + row[1].toString());
			result.put(PointType.values()[Integer.valueOf(row[0].toString())], row[1]);
		}
		for (Map.Entry<PointType, Object> val : result.entrySet()) {
			result.replace(val.getKey(), Integer.valueOf(val.getValue().toString()) * 100 / total);
		}
		System.out.println("Total = " + total);

		// } catch (Exception e) {
		// System.out.println("Erreur Find Skills");
		// }
		return result;
	}

}

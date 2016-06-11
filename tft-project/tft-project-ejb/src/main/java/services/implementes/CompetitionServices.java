package services.implementes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Competition;
import services.interfaces.CompetitionServicesLocal;
import services.interfaces.CompetitionServicesRemote;

@Stateless
public class CompetitionServices  implements CompetitionServicesRemote,CompetitionServicesLocal{
	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<Competition> findCompetitionByWord(String word) {
		List<Competition> lists = null;
		try {
			String jpql = "select c from "+Competition.class.getName() +" c where c.name like :name";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("name", "%"+word+"%");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch find");
			System.out.println(ee.getMessage().toString());
			System.out.println(ee.getStackTrace()+" ");
		}
		return lists;
	}

}

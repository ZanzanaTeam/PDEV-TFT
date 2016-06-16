package services.implementes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Match;
import domain.SetMatch;
import services.interfaces.SetServicesRemote;

@Stateless
public class SetServices implements SetServicesRemote {

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

}

package services.implementes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import domain.Jeu;
import domain.Match;
import domain.Point;
import domain.SetMatch;
import services.implementes.basic.ServicesBasic;
import services.interfaces.PointServicesLocal;
import services.interfaces.PointServicesRemote;
import services.interfaces.basic.ServicesBasicLocal;

/**
 * author Rihab 
 */
/**
 * Session Bean implementation class PointServices
 */
@Stateless
@LocalBean
public class PointServices implements PointServicesRemote, PointServicesLocal {

	@EJB
	ServicesBasicLocal<Match> matchService = new ServicesBasic<>();
	private List<Point> listPoints;

	public PointServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> findAllPointsByMatch(Integer idMatch) {
		listPoints = new ArrayList<>();

		try {

			Match match = matchService.findById(idMatch, Match.class);
			List<SetMatch> setMatch = match.getSets();
			System.out.println("size of sets matchs : "+setMatch.size());
			for (SetMatch sm : setMatch) {
				for (Jeu j : sm.getJeus()) {

					listPoints.addAll(j.getPoints());

				}
			}

		} catch (Exception e) {
			System.out.println("e.Message "+e.getMessage());
		}
		return listPoints;
	}

}

package services.implementes.basic;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import domain.Jeu;
import domain.MatchSingle;
import domain.Player;
import domain.SetMatch;
import enumeration.Surface;
import services.interfaces.PronosticServiceLocal;
import services.interfaces.PronosticServiceRemote;
import services.interfaces.basic.ServicesBasicLocal;

/**
 * Session Bean implementation class PronosticService
 */
@Stateless

public class PronosticService implements PronosticServiceLocal, PronosticServiceRemote {
@EJB
	ServicesBasicLocal<MatchSingle> proxy;
	
	/**
	 * Default constructor.
	 */
	public PronosticService() {
		// TODO Auto-generated constructor stub
	}

	public float pronostic(MatchSingle match) {
		// return new Random().nextFloat();
		float[] classement = classement(match.getPlayer(), match.getPlayer2());
//	 float[] derniersResultats = derniersResultats(match.getPlayer(),
//	 match.getPlayer2());
		// float[] surface = surface(match.getPlayer(), match.getPlayer2(),
		// match.getCourt().getSurface());
		// float[] blessure = blessure(match.getPlayer(), match.getPlayer2());
		// float[] forme = forme(match.getPlayer(), match.getPlayer2());
		// float[] fraisheurPhysique = fraisheurPhysique(match.getPlayer(),
		// match.getPlayer2());
		// float[] lieuDuTournoi = lieuDuTournoi(match.getPlayer(),
		// match.getPlayer2(),
		// match.getCompetition().getCountry());
		float[] somme = { 0, 0 };
		somme[0] = (classement[0]  /*+ derniersResultats[0] + surface[0] +
									 * lieuDuTournoi[0]
									 */);
		somme[1] = (classement[1]
									 /*+ derniersResultats[1] + surface[1] + //
									 * lieuDuTournoi[1]
									 */);
		float res = somme[0] / (somme[0] + somme[1]);
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		formatter.setRoundingMode(RoundingMode.DOWN); 
		Float formatedFloat = new Float(formatter.format(res));
		
		
				
		return formatedFloat;

	}

	public float[] lieuDuTournoi(Player player1, Player player2, String country) {
		float[] res = { 0, 0 };
		res[0] = lieuDuTournoi(player1, country);
		res[1] = lieuDuTournoi(player2, country);
		return res;
	}

	public float lieuDuTournoi(Player player, String country) {
		float res = 0;
		if (player.getCountry().equals(country))
			res = 3;
		return res;
	}

	public float[] surface(Player player1, Player player2, Surface surface) {
		float[] res = { 0, 0 };
		res[0] = surface(player1, surface);
		res[1] = surface(player2, surface);

		return res;
	}

	public float surface(Player player, Surface surface) {
		float[] res = { 0, 0 };
		List<MatchSingle> list = player.getAllMatchSingles();
		list = list.stream().filter(m -> m.getCourt().getSurface().equals(surface)).collect(Collectors.toList());
		for (MatchSingle match : list) {

			for (SetMatch set : match.getSets()) {

				if (set.getWinner() == player)
					res[0]++;
				else
					res[1]++;

			}

		}

		return (res[0] / max(res)) * 10;
	}

	public float[] classement(Player player1, Player player2) {
		float[] res = { 0, 0 };
		res[0] = classement(player1);
		res[1] = classement(player2);
		
		return res;
	}

	public float classement(Player player) {
//		int cj = player.getClassement();
//		if (cj == 0)
//			return 0;
//		if (player.getClassement() < 4)
//			return 40;
//		if (cj < 11 && cj > 3)
//			return 30;
//		if (cj < 51 && cj > 10)
//			return 20;
//		if (cj < 101 && cj > 50)
//			return 15;
//		if (cj > 100)
//			return 10;
//		return 0;
		return player.getClassement();
	}

	public float[] derniersResultats(Player player1, Player player2) {

		float[] dR = { 0, 0 }, dF={ 0, 0 }, res = { 0, 0 };
		dR[0] = derniersResultats(player1);
		dR[1] = derniersResultats(player2);
//		dF = derniersFaceAFace(player1, player2);
		res[1] = (dR[0]+ dF[0]);
		res[1] = (dR[1]+dF[1] );
		return res;
	}

	public float max(float[] tab) {

		return (tab[0] > tab[1] ? tab[0] : tab[1]);
	}

	public int derniersResultats(Player player) {
		int res = 0, i = 0;
		System.out.println("player id   "+ player.getId());
		player.setMatchSingles1(proxy.findBy2(MatchSingle.class, "player.id",String.valueOf(player.getId())));
		player.setMatchSingles2(proxy.findBy2(MatchSingle.class, "player2.id",String.valueOf(player.getId())));
		List<MatchSingle> list = player.getAllMatchSingles();
System.out.println("list matches size  "+list.size());
		for (MatchSingle match : list) {

			i++;
			for (SetMatch set : match.getSets()) {
				if (set.getWinner().getId() == player.getId())
					res++;
				System.out.println(set.getWinner().getId());
			}
			if (i == 5)
				break;
		}
System.out.println("resultat  "+res);
		return res;
	}

	public float[] derniersFaceAFace(Player player1, Player player2) {
		float res[] = { 0, 0 };
		int i = 0;
		List<MatchSingle> list = player1.getAllMatchSingles();
		list = list.stream().filter(m -> (m.getPlayer().equals(player2) || m.getPlayer2().equals(player2)))
				.collect(Collectors.toList());

		for (MatchSingle match : list) {

			i++;
			for (SetMatch set : match.getSets()) {
				for (Jeu j : set.getJeus()) {
					if (j.getWinner() == player1)
						res[0]++;
					else
						res[1]++;
				}

			}
			if (i == 5)
				break;
		}

		return res;
	}

}

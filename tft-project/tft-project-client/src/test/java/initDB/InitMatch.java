package initDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import delegate.ServicesBasicDelegate;
import domain.Court;
import domain.Jeu;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.Referee;
import domain.SetMatch;
import domain.Tour;
import enumeration.Order;
import enumeration.PointType;
import enumeration.StatusMatch;
import enumeration.Surface;

public class InitMatch {

	public static void main(String[] args) {

		List<Tour> tours = new ServicesBasicDelegate<Tour>().doCrud().findBy(Tour.class, "position", "0");
		Random random = new Random();

		for (Tour tour : tours) {
			System.out.println("Season " + tour.getSeason().getTitle());
			List<Player> players = new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class);
			List<Referee> referees = new ServicesBasicDelegate<Referee>().doCrud().findAll(Referee.class);
			for (Integer i = 1; i <= (Math.pow(2, Order.FINAL.ordinal())); i++) {
				Integer index = random.nextInt((players.size() - 1) + 1);

				Player player1 = players.get(index);
				players.remove(index);

				index = random.nextInt((players.size() - 1) + 1);
				Player player2 = players.get(index);
				players.remove(index);

				MatchSingle match = new MatchSingle();
				match.setCompetition(tour.getSeason().getCompetition());//
				match.setTour(tour);
				index = random.nextInt((referees.size() - 1) + 1);
				match.setReferee(referees.get(index));
				referees.remove(index);
				match.setPlayer(player1);
				match.setPlayer2(player2);
				match.setDateMatch(new Date());
				match.setStatus(StatusMatch.PENDING);
				match.setDuration(0);
				System.out.println("Tour: "+ tour.getTitle() );
				System.out.println("Creation Match ----------------");
				ServicesBasicDelegate<MatchSingle> proxy = new ServicesBasicDelegate<MatchSingle>();
				if (proxy.doCrud().add(match) == true) {
					System.out.println("--------Match N° " + i);
					System.out.println("--------" + player1.getFullName() + " VS " + player2.getFullName());
				}

			}
			List<MatchSingle> matchs = new ServicesBasicDelegate<MatchSingle>().doCrud().findBy(MatchSingle.class,
					"status", "0");
			System.out.println("Nombre de match: " + matchs.size());
			for (MatchSingle match : matchs) {
				Court court= new ServicesBasicDelegate<Court>().doCrud().findBy
						(Court.class, "surface", String.valueOf(match.getCompetition().getSurface().ordinal())) .get(0);
				
				match.setCourt(court);
				match.setStatus(StatusMatch.PROGRESS);
				System.out.println("--------Match  -- ID = " + match.getId() + " ------ In Progress");
				new ServicesBasicDelegate<MatchSingle>().doCrud().update(match);

				while ((match.getScore(match.getPlayer()) < tour.getSeason().getCompetition().getNbSet()) == true
						&& (match.getScore(match.getPlayer2()) < tour.getSeason().getCompetition()
								.getNbSet()) == true) {

					SetMatch setMatch = generateSet(match);
					match = new ServicesBasicDelegate<MatchSingle>().doCrud().findById(setMatch.getMatch().getId(),
							MatchSingle.class);

					System.out.println("Score Set =" + match.getScore(match.getPlayer()) + " - "
							+ match.getScore(match.getPlayer2()));
				}

				match.setStatus(StatusMatch.FINISHED);
				new ServicesBasicDelegate<MatchSingle>().doCrud().update(match);

				System.out.println("--------Match  -- ID = " + match.getId() + " ------ FINISHED");
			}

		}
	}

	private static SetMatch generateSet(MatchSingle match) {

		SetMatch setMatch = new SetMatch();
		Integer id = (match.getSets() != null) ? match.getSets().size() + 1 : 1;
		Integer idSetMatch = match.getId() * 10 + id;
		setMatch.setId(idSetMatch);
		setMatch.setMatch(match);
		new ServicesBasicDelegate<SetMatch>().doCrud().add(setMatch);
		setMatch = new ServicesBasicDelegate<SetMatch>().doCrud().findById(idSetMatch, SetMatch.class);
		Boolean serve = true;
		ArrayList<Jeu> jeux = new ArrayList<Jeu>();
		jeux.addAll(setMatch.getJeus());

		if ( jeux != null && jeux.size() > 0) {
			if (jeux.get(setMatch.getJeus().size() - 1).getWinner().getId() != match.getPlayer().getId()) {
				serve = true;
			} else {
				serve = false;
			}
		}
		while (((((setMatch.getScore(match.getPlayer()) == 6)
				&& (Math.abs(setMatch.getScore(match.getPlayer()) - setMatch.getScore(match.getPlayer2())) > 1))
				|| ((setMatch.getScore(match.getPlayer2()) == 6)
						&& (Math.abs(setMatch.getScore(match.getPlayer()) - setMatch.getScore(match.getPlayer2())) > 1))

		) == false

				&& ((setMatch.getScore(match.getPlayer()) == 7) || (setMatch.getScore(match.getPlayer2()) == 7)

				) == false)) {
			jeux.clear();
			jeux.addAll(setMatch.getJeus());
			if (jeux != null && jeux.size() > 0) {
				Jeu lastSet = jeux.get(setMatch.getJeus().size() - 1);
				if (lastSet.getServe().getId() != match.getPlayer().getId()) {
					serve = false;
				} else {
					serve = true;
				}
			}
			Jeu jeu = generateJeu(setMatch, match.getPlayer(), match.getPlayer2(), serve);

			setMatch = new ServicesBasicDelegate<SetMatch>().doCrud().findById(idSetMatch, SetMatch.class);

			jeux.clear();
			jeux.addAll(setMatch.getJeus());

			// System.out.println("Nombre de jeu = " + jeux.size());

			// for (Jeu entity : jeux) {
			// System.out.println("Gagnant Jeu N°" + entity.getId() + " = " +
			// entity.getWinner());
			// }
			// System.out.println("Score Set N° " + match.getSets().size() + 1 +
			// " = " + setMatch.getScore(match.getPlayer())
			// + " - " + setMatch.getScore(match.getPlayer2()));
		}

		System.out.println(
				" ---------------------------------------------------------------------------------------Set winner ="
						+ setMatch.getWinner());
		return setMatch;
	}

	private static Jeu generateJeu(SetMatch setMatch, Player player1, Player player2, Boolean serve) {

		Jeu jeu = new Jeu();
		Integer idSetMatch = (setMatch.getJeus() != null) ? setMatch.getJeus().size() + 1 : 1;
		Integer idJeu = setMatch.getId() * 100 + idSetMatch;
		jeu.setId(idJeu);
		jeu.setSet(setMatch);
		jeu.setServe((serve == true) ? player1 : player2);
		new ServicesBasicDelegate<Jeu>().doCrud().add(jeu);
		jeu = new ServicesBasicDelegate<Jeu>().doCrud().findById(jeu.getId(), Jeu.class);

		System.out.println("Jeu N° " + setMatch.getJeus().size() + 1);

		while (false == (((jeu.getScore(player1) > 3) && (Math.abs(jeu.getScore(player1) - jeu.getScore(player2)) >= 2))
				|| ((jeu.getScore(player2) > 3) && (Math.abs(jeu.getScore(player1) - jeu.getScore(player2)) >= 2)))) {
			// System.out.println("Point = " + jeu.getScore(player1) + " - " +
			// jeu.getScore(player2));
			// System.out.println("Point = " + pointScore(jeu.getScore(player1),
			// jeu.getScore(player2)));
			Point point = generatePoint(jeu, player1, player2);
			jeu = new ServicesBasicDelegate<Jeu>().doCrud().findById(idJeu, Jeu.class);
		}

		// System.out.println("Jeu Fini! Gagnant: " +
		// jeu.getWinner().getFullName());
		System.out.println(" Score Jeu N° " + setMatch.getJeus().size() + 1 + " = " + jeu.getScore(player1) + " - "
				+ jeu.getScore(player2));

		return jeu;
	}

	private static String pointScore(Integer point1, Integer point2) {
		String score = "";
		Integer score1 = 0;
		Integer score2 = 0;
		if (point1 <= 4 && point2 <= 4) {
			if (point1 == point2 && point1 == 3) {
				score = "Deuce";
			} else {
				if (point1 < 3) {
					score1 = point1 * 15;
				} else {
					if (point1 == 3) {
						score1 = 40;
					} else {
						score1 = 45;
					}
				}
				if (point2 < 3) {
					score2 = point2 * 15;
				} else {
					if (score2 == 3) {
						score2 = 40;
					} else {
						score2 = 45;
					}
				}
				score = score1.toString() + " - " + score2.toString();
			}
		} else {
			if (score1 > score2) {
				score = "Avantage - 0";
			}
			if (score1 < score2) {
				score = "0 - Avantage";
			}
			if (score1 == score2) {
				score = "Deuce";
			}
		}
		return score;
	}

	private static Point generatePoint(Jeu jeu, Player player1, Player player2) {
		Point point = new Point();
		Random random = new Random();
		point.setJeu(jeu);
		point.setPlayer((random.nextInt(2) == 0) ? player1 : player2);
		point.setPointType(PointType.values()[random.nextInt(PointType.values().length)]);
		new ServicesBasicDelegate<Point>().doCrud().add(point);
//	System.out.println("Point To => " + point.getPlayer().getFullName());
		return point;
	}

}

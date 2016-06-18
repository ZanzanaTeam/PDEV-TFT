package initDB;

import java.util.Date;
import java.util.List;
import java.util.Random;

import delegate.ServicesBasicDelegate;
import domain.Jeu;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.Referee;
import domain.SetMatch;
import domain.Tour;
import enumeration.Order;
import enumeration.PointType;

public class InitMatch {

	public static void main(String[] args) {

		List<Tour> tours = new ServicesBasicDelegate<Tour>().doCrud().findBy(Tour.class, "position", "4");
		Random random = new Random();

		for (Tour tour : tours) {
			System.out.println("Season " + tour.getSeason().getTitle());
			List<Player> players = new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class);
			List<Referee> referees = new ServicesBasicDelegate<Referee>().doCrud().findAll(Referee.class);
			for (Integer i = 1; i <= (Math.pow(2, Order.R32.ordinal())); i++) {
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
				System.out.println("--------Match° " + i);
				System.out.println("--------" + player1.getFullName() + " VS " + player2.getFullName());
				new ServicesBasicDelegate<MatchSingle>().doCrud().add(match);

				while ((match.getScore(match.getPlayer()) == tour.getSeason().getCompetition().getNbSet() || match
						.getScore(match.getPlayer2()) == tour.getSeason().getCompetition().getNbSet()) == false) {

					SetMatch setMatch = generateSet(match);
					match = new ServicesBasicDelegate<MatchSingle>().doCrud().findById(setMatch.getMatch().getId(),
							MatchSingle.class);
				}
			}

		}
	}

	private static SetMatch generateSet(MatchSingle match) {

		SetMatch setMatch = new SetMatch();

		setMatch.setMatch(match);
		new ServicesBasicDelegate<SetMatch>().doCrud().add(setMatch);
		Boolean serve = true;
		while ((((setMatch.getScore(match.getPlayer()) == 6) || (setMatch.getScore(match.getPlayer2()) == 6)
				&& (Math.abs(setMatch.getScore(match.getPlayer()) - setMatch.getScore(match.getPlayer2())) > 1))
				|| ((setMatch.getScore(match.getPlayer()) == 7) || (setMatch.getScore(match.getPlayer2()) == 7))

		) == false) {
			if (setMatch.getJeus().size() > 0) {
				Jeu lastSet = setMatch.getJeus().get(setMatch.getJeus().size() - 1);
				if (lastSet.getServe() != match.getPlayer()) {
					serve = false;
				} else {
					serve = true;
				}
			}
			Jeu jeu = generateJeu(setMatch, match.getPlayer(), match.getPlayer2(), serve);
			setMatch = new ServicesBasicDelegate<SetMatch>().doCrud().findById(jeu.getSet().getId(), SetMatch.class);

		}

		return setMatch;
	}

	private static Jeu generateJeu(SetMatch setMatch, Player player1, Player player2, Boolean serve) {

		Jeu jeu = new Jeu();

		jeu.setServe((serve == true) ? player1 : player2);
		new ServicesBasicDelegate<Jeu>().doCrud().add(jeu);
		while (false == (jeu.getScore(player1) >= 4
				|| jeu.getScore(player2) >= 4 && Math.abs(jeu.getScore(player1) - jeu.getScore(player2)) == 2)) {

			Point point = generatePoint(jeu, player1, player2);
			jeu = new ServicesBasicDelegate<Jeu>().doCrud().findById(point.getJeu().getId(), Jeu.class);
		}

		return jeu;
	}

	private static Point generatePoint(Jeu jeu, Player player1, Player player2) {
		Point point = new Point();
		Random random = new Random();
		point.setJeu(jeu);
		point.setPlayer((random.nextInt(1) == 0) ? player1 : player2);
		point.setPointType(PointType.values()[random.nextInt(PointType.values().length)]);
		new ServicesBasicDelegate<Point>().doCrud().add(point);
		return point;
	}

}

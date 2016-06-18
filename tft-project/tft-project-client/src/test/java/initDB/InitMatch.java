package initDB;

import java.util.Date;
import java.util.List;
import java.util.Random;

import delegate.ServicesBasicDelegate;
import domain.MatchSingle;
import domain.Player;
import domain.Referee;
import domain.SetMatch;
import domain.Tour;
import enumeration.CompetitionLevel;
import enumeration.Gender;
import enumeration.Order;

public class InitMatch {

	public static void main(String[] args) {

		List<Tour> tours = new ServicesBasicDelegate<Tour>().doCrud().findBy(Tour.class, "position", "4");
		Random random = new Random();
		Referee referee = new ServicesBasicDelegate<Referee>().doCrud().findById(1, Referee.class);

		for (Tour tour : tours) {
			System.out.println("Season "+ tour.getSeason().getTitle());
			List<Player> players = new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class);
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
				match.setPlayer(player1);
				match.setPlayer2(player2);
				match.setDateMatch(new Date());
//				match.setReferee(referee);
				System.out.println("--------Match° " + i);
				System.out.println("--------" + player1.getFullName() + " VS " + player2.getFullName());
				new ServicesBasicDelegate<MatchSingle>().doCrud().add(match);
				//generateMatch(match);
			}

		}
	}

	private static void generateMatch(MatchSingle match) {
		Player player1 = match.getPlayer();
		Player player2 = match.getPlayer2();
		
		List<SetMatch> sets = new ServicesBasicDelegate<SetMatch>().doCrud().findBy(SetMatch.class, "match", match.getId().toString());
		
	}
}

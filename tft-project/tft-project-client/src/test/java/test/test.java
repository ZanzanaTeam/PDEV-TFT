package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;
import domain.Match;
import domain.MatchSingle;
import domain.Player;
import domain.Season;
import domain.Tour;
import enumeration.Order;
import enumeration.Surface;

public class test {
	public static void main(String[] args) {
		ServicesBasicDelegate<Competition> proxy;
		proxy = new ServicesBasicDelegate<Competition>();

		// ****************COMPETITION***********************/

		Competition c1 = new Competition();
		c1.setName("competition1");
		c1.setCountry("tunis");
		Competition c2 = new Competition();
		c2.setName("Competition2");
		c2.setCountry("Sfax");
		Competition c3 = new Competition();
		c3.setName("competition3");
		c3.setCountry("Nabeul");
		Competition c4 = new Competition();
		c4.setName("Competition4");
		c4.setCountry("Djerba");

		List<Competition> competitions = new ArrayList<>();
		competitions.add(c1);
		competitions.add(c2);
		competitions.add(c3);
		competitions.add(c4);

		// ********************Court******************************//
		/////////////// Carpet//////////////////////////
		Court courtCarpet = new Court();
		courtCarpet.setName("Carpet court");
		courtCarpet.setSurface(Surface.CARPET);
		/////////////// CLAY//////////////////////////
		Court courtClay = new Court();
		courtClay.setName("CLAY court");
		courtClay.setSurface(Surface.CLAY);
		/////////////// GRASS//////////////////////////
		Court courtGrass = new Court();
		courtGrass.setName("Grass court");
		courtGrass.setSurface(Surface.GRASS);
		/////////////// HARD//////////////////////////
		Court courtHard = new Court();
		courtHard.setName("Grass court");
		courtHard.setSurface(Surface.HARD);

		List<Court> courts = new ArrayList<>();
		courts.add(courtCarpet);
		courts.add(courtHard);
		courts.add(courtGrass);
		courts.add(courtClay);
		// **************season***********************
		Season season1 = new Season();
		Season season2 = new Season();
		List<Season> seasons = new ArrayList<>();
		seasons.add(season1);
		seasons.add(season2);
		// **************tour***********************
		Tour tour1 = new Tour();
		tour1.setPosition(Order.R32);
		Tour tour2 = new Tour();
		tour2.setPosition(Order.R16);
		Tour tour3 = new Tour();
		tour3.setPosition(Order.QF);
		Tour tour4 = new Tour();
		tour4.setPosition(Order.SF);
		Tour tour5 = new Tour();
		tour5.setPosition(Order.FINAL);
		List<Tour> tours = new ArrayList<>();
		tours.add(tour1);
		tours.add(tour2);
		tours.add(tour3);
		tours.add(tour4);
		tours.add(tour5);

		// ****************player***********************
		//////////////// player1/////////////////////////
		Player player1 = new Player();
		player1.setFullName("Majed Kilani");
		player1.setCountry("Djerba");
		player1.setAge(30);
		player1.setClassement(1);
		//////////////// player2/////////////////////////
		Player player2 = new Player();
		player2.setFullName("Ben Hassen Ameur");
		player2.setCountry("Tunis");
		player2.setAge(23);
		player2.setClassement(35);
		//////////////// player3/////////////////////////
		Player player3 = new Player();
		player3.setFullName("Aziz Dougaz");
		player3.setCountry("Tunis");
		player3.setAge(32);
		player3.setClassement(10);
		//////////////// player4/////////////////////////
		Player player4 = new Player();
		player4.setFullName("Malek Jaziri");
		player4.setCountry("Bizerte");
		player4.setAge(32);
		player4.setClassement(63);
		//////////////// player5/////////////////////////
		Player player5 = new Player();
		player5.setFullName("Aymen Tounsi");
		player5.setCountry("Gafsa");
		player5.setAge(24);
		player5.setClassement(2);
		//////////////// player6/////////////////////////
		Player player6 = new Player();
		player6.setFullName("Mourad Chtiwi");
		player6.setCountry("Kairaouan");
		player6.setAge(32);
		player6.setClassement(15);
		//////////////// player7/////////////////////////
		Player player7 = new Player();
		player7.setFullName("Amen Mejri");
		player7.setCountry("Sousse");
		player7.setAge(22);
		player7.setClassement(113);
		//////////////// player8/////////////////////////
		Player player8 = new Player();
		player8.setFullName("Jaafer Mwelhi");
		player8.setCountry("Nabeul");
		player8.setAge(24);
		player8.setClassement(24);

		/////////////////////////////////////////////////////////////////////////
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);

		////////////////////// match////////////////////////

		for (Competition competition : competitions) {
			competition.setSeasons(seasons);
			for (Season season : competition.getSeasons()) {
				season.setCompetition(competition);
				season.setTours(tours);
				for (Tour tour : season.getTours()) {
					tour.setSeason(season);

					Random r = new Random();
					int i = 1;
					List<Match> matchs = new ArrayList<>();
					for (int k = 0; k < i; k++) {

						Date dateMatch1 = new Date();
						dateMatch1.setDate(r.nextInt(27) + 1);
						dateMatch1.setMonth(r.nextInt(11) + 1);
						dateMatch1.setYear(r.nextInt(16) + 2000);
						MatchSingle m1 = new MatchSingle();
						m1.setDateMatch(dateMatch1);

						m1.setCourt(courts.get(r.nextInt(courts.size())));
						m1.setPlayer(players.get(r.nextInt(players.size())));
						m1.setPlayer2(players.get(r.nextInt(players.size())));
						m1.setCompetition(competition);
						m1.setTour(tour);
						matchs.add(m1);
					}
					i = i * 2;

					tour.setMatchs(matchs);

				}
			}
			proxy.doCrud().add(competition);
		}

	}
}
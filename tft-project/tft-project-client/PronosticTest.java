package main.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;
import domain.Jeu;
import domain.Match;
import domain.MatchSingle;
import domain.Player;
import domain.Season;
import domain.SetMatch;
import domain.Tour;
import enumeration.Meteo;
import enumeration.Surface;

public class PronosticTest {

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
		// **************season***********************
		Season season1 = new Season();
		Season season2 = new Season();
		List<Season> seasons = new ArrayList<>();
		seasons.add(season1);
		seasons.add(season2);
		// **************tour***********************
		Tour tour = new Tour();
		tour.setPosition(1);
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
		// -----------------------------------------------------
		///////////////// Match1/////////////////////////
		Date dateMatch1 = new Date();
		dateMatch1.setDate(21);
		dateMatch1.setMonth(11);
		dateMatch1.setYear(2016);
		MatchSingle m1 = new MatchSingle();
		m1.setDateMatch(dateMatch1);
		m1.setMeteo(Meteo.hot);
		m1.setCourt(courtHard);
		m1.setPlayer(player1);
		m1.setPlayer2(player2);
		///////////////// Match2/////////////////////////
		Date dateMatch2 = new Date();
		dateMatch2.setDate(20);
		dateMatch2.setMonth(11);
		dateMatch2.setYear(2016);
		MatchSingle m2 = new MatchSingle();
		m2.setDateMatch(dateMatch2);
		m2.setMeteo(Meteo.normal);
		m2.setCourt(courtClay);
		m2.setPlayer(player1);
		m2.setPlayer2(player7);
		///////////////// Match3/////////////////////////
		Date dateMatch3 = new Date();
		dateMatch3.setDate(20);
		dateMatch3.setMonth(11);
		dateMatch3.setYear(2016);
		MatchSingle m3 = new MatchSingle();
		m3.setDateMatch(dateMatch3);
		m3.setMeteo(Meteo.normal);
		m3.setCourt(courtCarpet);
		m3.setPlayer(player2);
		m3.setPlayer2(player4);
		///////////////// Match4/////////////////////////
		Date dateMatch4 = new Date();
		dateMatch4.setDate(19);
		dateMatch4.setMonth(11);
		dateMatch4.setYear(2016);
		MatchSingle m4 = new MatchSingle();
		m4.setDateMatch(dateMatch4);
		m4.setMeteo(Meteo.windy);
		m4.setCourt(courtHard);
		m4.setPlayer(player1);
		m4.setPlayer2(player5);
		///////////////// Match5/////////////////////////
		Date dateMatch5 = new Date();
		dateMatch5.setDate(19);
		dateMatch5.setMonth(11);
		dateMatch5.setYear(2016);
		MatchSingle m5 = new MatchSingle();
		m5.setDateMatch(dateMatch5);
		m5.setMeteo(Meteo.windy);
		m5.setCourt(courtGrass);
		m5.setPlayer(player6);
		m5.setPlayer2(player7);
		///////////////// Match6/////////////////////////
		Date dateMatch6 = new Date();
		dateMatch6.setDate(19);
		dateMatch6.setMonth(11);
		dateMatch6.setYear(2016);
		MatchSingle m6 = new MatchSingle();
		m6.setDateMatch(dateMatch6);
		m6.setMeteo(Meteo.windy);
		m6.setCourt(courtCarpet);
		m6.setPlayer(player8);
		m6.setPlayer2(player4);
		///////////////// Match7/////////////////////////
		Date dateMatch7 = new Date();
		dateMatch7.setDate(19);
		dateMatch7.setMonth(11);
		dateMatch7.setYear(2016);
		MatchSingle m7 = new MatchSingle();
		m7.setDateMatch(dateMatch7);
		m7.setMeteo(Meteo.windy);
		m7.setCourt(courtClay);
		m7.setPlayer(player2);
		m7.setPlayer2(player3);
		///////////////////////////////
		List<Match> matchs1 = new ArrayList<>();
		matchs1.add(m1);
		matchs1.add(m2);
		matchs1.add(m3);
		matchs1.add(m4);
		matchs1.add(m5);
		matchs1.add(m6);
		matchs1.add(m7);
		for (Match match : matchs1) {
			SetMatch set1 = new SetMatch();
			SetMatch set2 = new SetMatch();

			List<SetMatch> sets = new ArrayList<>();
			sets.add(set1);
			sets.add(set2);
			match.setSets(sets);

			for (SetMatch set : match.getSets()) {
				set.setJeus(new ArrayList<Jeu>());
				Jeu jeu = new Jeu();
				for (int i = 0; i < 13; i++) {
					boolean b;
					if (i % 2 == 0) {
						b = true;
					} else {
						b = false;
					}
					jeu.setScore(b);
					jeu.setServe(b);
					List<Jeu> a = set.getJeus();
					a.add(jeu);
					set.setJeus(a);
				}
			}

		}
		tour.setMatchs(matchs1);
		List<Tour> tours = new ArrayList<>();
		tours.add(tour);
		for (Competition competition : competitions) {
			competition.setSeasons(seasons);
			for (Season season : competition.getSeasons()) {
				season.setTours(tours);

			}
			// proxy.doCrud().add(competition);

		}

		for (Competition c : competitions) {
			System.out.println(c);
			for (Season s : c.getSeasons()) {
				System.out.println(s);
				for (Tour t : s.getTours()) {
					System.out.println(t);
					for (Match m : t.getMatchs()) {
						System.out.println(m);
						for (SetMatch set : m.getSets()) {
							System.out.println(set);
							for (Jeu j : set.getJeus()) {
								System.out.println(j);

							}
						}
					}
				}
			}
		}
	}

	public float pronostic(MatchSingle match) {
		int[] classement = classement(match.getPlayer(), match.getPlayer2());
		int[] derniersResultats = derniersResultats(match.getPlayer(), match.getPlayer2());
		int[] surface = surface(match.getPlayer(), match.getPlayer2());
		int[] blessure = blessure(match.getPlayer(), match.getPlayer2());
		int[] forme = forme(match.getPlayer(), match.getPlayer2());
		int[] fraisheurPhysique = fraisheurPhysique(match.getPlayer(), match.getPlayer2());
		int[] lieuDuTournoi = lieuDuTournoi(match.getPlayer(), match.getPlayer2());

	}

	public int[] classement(Player player1, Player player2) {
		int[] res = { 0, 0 };
		res[0] = classement(player1);
		res[1] = classement(player2);
		return res;
	}

	public int classement(Player player) {
		int cj = player.getClassement();
		if (cj == 0)
			return 0;
		if (player.getClassement() < 4)
			return 50;
		if (cj < 11 && cj > 3)
			return 40;
		if (cj < 51 && cj > 10)
			return 30;
		if (cj < 101 && cj > 50)
			return 25;
		if (cj > 100)
			return 15;
		return 0;
	}

	public int[] derniersResultats(Player player1, Player player2) {
		int[] dR = { 0, 0 };
		int[] dF;
		dR[0] = derniersResultats(player1);
		dR[1] = derniersResultats(player2);
		dF = derniersFaceAFace(player1, player2);

		return res;
	}

	private int derniersResultats(Player player) {
		int res = 0;
		for (int i = 0; i < 2;) {

			for (Match match : player.getMatchSingles1()) {

				for (SetMatch set : match.getSets()) {
					if (set.getscore())
						res++;
					else
						res--;
				}
			}
			i++;
		}
		for (int i = 0; i < 2;) {

			for (Match match : player.getMatchSingles2()) {

				for (SetMatch set : match.getSets()) {
					if (set.getscore())
						res--;
					else
						res++;
				}
			}
			i++;
		}

		return res;
	}

	private int[] derniersFaceAFace(Player player1, Player player2) {
		int res[] = { 0, 0 };
		List<MatchSingle> matchs1 = player1.getMatchSingles1().stream()
				.filter(m -> m.getPlayer2().getId().equals(player2.getId())).collect(Collectors.toList());
		List<MatchSingle> matchs2 = player1.getMatchSingles2().stream()
				.filter(m -> m.getPlayer2().getId().equals(player2.getId())).collect(Collectors.toList());
		List<MatchSingle> list = matchs1;
		list.addAll(matchs2);
		list = list.stream().sorted((m1, m2) -> m1.getDateMatch().compareTo(m2.getDateMatch())).collect(Collectors.toList());
		for (int i = 0; i < 5;) {

			for (MatchSingle match : list) {

				for (SetMatch set : match.getSets()) {
					for (Jeu j: set.getJeus()) {
						if(matchs1.contains(match)) {
							if (j.getScore())
								res[0]++;
							else
								res[1]++;
						} else  {
							if (j.getScore())
								res[1]++;
							else
								res[0]++;
						}
					}
				}i++;
			}
			
		}

		return res;
	}

}
package initDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;
import domain.Jeu;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.Season;
import domain.SetMatch;

public class initMatchs {

	static ServicesBasicDelegate<Competition> competitionProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<MatchSingle> matchProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();

	static ServicesBasicDelegate<SetMatch> setProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Jeu> jeuProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Point> pointProxy = new ServicesBasicDelegate<>();

	static List<Player> players;
	static List<Court> Courts;
	static List<Competition> competitions;

	static int ind;

	public static int getInd() {
		return ind;
	}

	public static void setInd(int size) {
		if (size != 1) {
			Random random = new Random();
			ind = random.nextInt(size - 1);
		} else {
			ind = 0;
		}
	}

	public static void main(String[] args) {
		players = playerProxy.doCrud().findAll(Player.class);
		competitions = competitionProxy.doCrud().findAll(Competition.class);
		competitions.stream().map(c -> c.getSeasons()).forEach(ls -> ls.stream().forEach(s -> initSeasonMatchs(s)));

	}

	private static void initSeasonMatchs(Season s) {

		List<Player> Seasonplayers = players;
		s.getTours().forEach(t -> {
			//////////////// tour//////////////////////
			do {

				System.out.println("begin " + Seasonplayers.size());
				setInd(Seasonplayers.size());
				Player player = Seasonplayers.get(ind);
				Seasonplayers.remove(ind);
				System.out.println("middle " + Seasonplayers.size());
				setInd(Seasonplayers.size());
				Player player2 = Seasonplayers.get(ind);
				Seasonplayers.remove(ind);
				MatchSingle match = new MatchSingle();
				match.setPlayer(player);
				match.setPlayer2(player2);
				match.setTour(t);
				matchProxy.doCrud().add(match);
				System.out.println("end " + Seasonplayers.size());
			} while (Seasonplayers.size() != 0);
			List<MatchSingle> list = matchProxy.doCrud().findBy(MatchSingle.class, "tour.id",
					String.valueOf(t.getId()));
			list.forEach(m -> {
				initSets(m);
				Seasonplayers.add(m.getWinner());
			});

			// update de la liste des joueurs
			// \\\\\\\\\\\\\\\\\\\\\\\\\tour\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		});

	}

	private static void initSets(MatchSingle m) {
int id=0;
		do {
id++;
			SetMatch set = new SetMatch();
			set.setMatch(m);
			set.setId(m.getId()*10+id);
			setProxy.doCrud().add(set);
m.getSets().add(set);
			
			initJeus(set, m.getPlayer(), m.getPlayer2());
			System.out.println(m.getSets().size()+"-----------------sets-------------------");
		} while (m.getScore(m.getPlayer()) == 2 || m.getScore(m.getPlayer2()) == 2);

	}

	private static void initJeus(SetMatch set, Player player, Player player2) {
		int sc1;
		int sc2;
		int id=0;
		do {
			id++;
			Jeu jeu = new Jeu();
			jeu.setSet(set);
			jeu.setId(set.getId()*100+id);
			jeuProxy.doCrud().add(jeu);
			if(set.getJeus()==null)set.setJeus(new ArrayList<Jeu>());
			set.getJeus().add(jeu);
			initPoints(jeu, player, player2);
			sc1 = set.getScore(player);
			sc2 = set.getScore(player2);
			System.out.println(set.getJeus().size()+"---------------jeux--------------------");
		} while ((sc1 == 6 && sc2 < 5) || (sc2 == 6 && sc1 < 5) || (sc1 == 7 || sc2 == 7));

	}

	private static void initPoints(Jeu jeu, Player player, Player player2) {
		int sc1;
		int sc2;
		int id=0;
		do {
			id++;
			Point point = new Point();
			point.setJeu(jeu);
			setInd(1);
			point.setPlayer(getInd() == 0 ? player : player2);
			point.setId(jeu.getId()*100+id);
			pointProxy.doCrud().add(point);
			if(jeu.getPoints()==null)jeu.setPoints(new ArrayList<Point>());
			jeu.getPoints().add(point);
			

			sc1 = jeu.getScore(player);
			sc2 = jeu.getScore(player2);
			System.out.println(jeu.getPoints().size()+"----------------points---------------------");
		} while ((sc1 == 4 && sc2 < 3) || (sc2 == 4 && sc2 < 3) || (sc1 + sc2 > 6 || Math.abs(sc1 - sc2) > 2));

	}
}

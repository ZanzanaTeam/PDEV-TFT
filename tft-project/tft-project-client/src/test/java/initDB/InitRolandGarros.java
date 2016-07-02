package initDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import enumeration.PointType;

public class InitRolandGarros {


	static ServicesBasicDelegate<Competition> competitionProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<MatchSingle> matchProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Court> courtProxy = new ServicesBasicDelegate<>();

	static ServicesBasicDelegate<SetMatch> setProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Jeu> jeuProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Point> pointProxy = new ServicesBasicDelegate<>();

	static List<Player> players;
	static List<Court> Courts;
	static List<Competition> competitions;
	static Competition competition;

	static int ind;
	static boolean server = true;

	public static int getInd() {
		return ind;
	}

	public static void setInd(int size) {

		Random random = new Random();
		ind = random.nextInt(size);

	}

	public static void main(String[] args) {
		players = playerProxy.doCrud().findAll(Player.class);
		competition = competitionProxy.doCrud().findById(1, Competition.class);
		for (Season s : competition.getSeasons()) {
			 initSeasonMatchsT32(s);
		}
		
	}

	@SuppressWarnings("deprecation")
	private static void initSeasonMatchsT32(Season s) {
//		System.err.println(s.getTours().size());
List<Player> Seasonplayers = playerProxy.doCrud().findAll(Player.class);
//System.err.println(Seasonplayers.size());

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		s.getTours().stream().forEach(t -> {

			int day = 0;
			int d = t.getSeason().getStartDate().getDay()+ day ;
			Date startDate = null;
			try {
				startDate = sdf.parse(t.getSeason().getStartDate().getYear() +"-"+t.getSeason().getStartDate().getMonth() + "-"+ d);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			do {
				
				setInd(Seasonplayers.size());
				Player player = Seasonplayers.get(ind);
				Seasonplayers.remove(ind);

				setInd(Seasonplayers.size());
				Player player2 = Seasonplayers.get(ind);
				Seasonplayers.remove(ind);
				MatchSingle match = new MatchSingle();
				match.setPlayer(player);
				match.setPlayer2(player2);
				match.setTour(t);
				match.setCourt(courtProxy.doCrud().findById(s.getCompetition().getNbSet(), Court.class));
				match.setDateMatch(startDate);
				matchProxy.doCrud().add(match);
			} while (Seasonplayers.size() != 0);
			List<MatchSingle> list = matchProxy.doCrud().findBy2(MatchSingle.class, "tour.id",
					String.valueOf(t.getId()));

			list.forEach(m -> {
				initSets(m);
				Seasonplayers.add(m.getWinner());

			});

			day +=5;
		});

	}

	private static void initSets(MatchSingle m) {
		int id = 0;
		do {
			id++;
			SetMatch set = new SetMatch();
			set.setMatch(m);
			set.setId(m.getId() * 10 + id);
			setProxy.doCrud().add(set);
			m.getSets().add(set);
			// System.out.println(m.getId()+"***********"+m.getSets().size());
			initJeus(set, m.getPlayer(), m.getPlayer2());
			// System.out.println(m.getId()+"
			// "+m.getSets().size()+"-----------------sets-------------------");
			// System.err.println(m.getScore(m.getPlayer()) +" "+
			// m.getScore(m.getPlayer2()));
		} while (!(m.getScore(m.getPlayer()) == 2) && !(m.getScore(m.getPlayer2()) == 2));

	}

	private static void initJeus(SetMatch set, Player player, Player player2) {
		int sc1;
		int sc2;
		int id = 0;
		do {
			id++;
			Jeu jeu = new Jeu();
			jeu.setSet(set);
			jeu.setId(set.getId() * 100 + id);
			jeuProxy.doCrud().add(jeu);
			if (set.getJeus() == null)
				set.setJeus(new ArrayList<Jeu>());
			set.getJeus().add(jeu);
			initPoints(jeu, player, player2);
			sc1 = set.getScore(player);
			sc2 = set.getScore(player2);
			// System.out.println(set.getId()+"
			// "+set.getJeus().size()+"---------------jeux--------------------");
			// System.err.println(sc1+" "+sc2);
		} while (!(sc1 == 6 && sc2 < 5) && !(sc2 == 6 && sc1 < 5) && !(sc1 == 7 || sc2 == 7));

	}

	private static void initPoints(Jeu jeu, Player player, Player player2) {
		int sc1;
		int sc2;
		int id = 0;
		Player servePlayer = null;
		if (server == true){
			servePlayer = player;
			server = false;
		}else{
			servePlayer = player2;
			server = true;
		}
		Random random = new Random();
		do {
			id++;
			Point point = new Point();
			point.setJeu(jeu);
			setInd(2);
			point.setPlayer(getInd() == 0 ? player : player2);
			point.setId(jeu.getId() * 100 + id);
			point.setPointType(PointType.values()[random.nextInt(PointType.values().length)]);
			pointProxy.doCrud().add(point);
			if (jeu.getPoints() == null)
				jeu.setPoints(new ArrayList<Point>());
			jeu.getPoints().add(point);
			jeu.setServe(servePlayer);
			sc1 = jeu.getScore(player);
			sc2 = jeu.getScore(player2);
			// System.out.println(jeu.getId()+"
			// "+jeu.getPoints().size()+"----"+getInd()+"---------points-------"+point.getPlayer().getId()
			// +"--------------");
			// System.err.println(sc1+" "+sc2);
			// System.out.println(sc1 == 4 && sc2 < 3);System.out.println(sc2 ==
			// 4 && sc1 < 3);System.out.println(sc1 + sc2 > 6 && Math.abs(sc1 -
			// sc2) >= 2);
		} while (!(sc1 == 4 && sc2 < 3) && !(sc2 == 4 && sc1 < 3) && !(sc1 + sc2 > 6 && Math.abs(sc1 - sc2) > 1));

	}
}

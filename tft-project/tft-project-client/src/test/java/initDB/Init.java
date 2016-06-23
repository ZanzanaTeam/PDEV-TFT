package initDB;

import java.util.List;
import java.util.stream.Stream;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;
import domain.Player;
import domain.Season;
import domain.Tour;
import enumeration.Order;
import enumeration.Surface;

public class Init {
	static ServicesBasicDelegate<Court> courtProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Competition> competitionProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Season> seasonProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Tour> tourProxy = new ServicesBasicDelegate<>();

	static List<Player> players;
	static List<Court> Courts;
	static List<Competition> competitions;

	public static void main(String[] args) {
		initPlayers();
		 players.stream().forEach(p->System.out.println(p.getFullName()));
		initCourts();
		 Courts.stream().forEach(c->System.out.println(c.getSurface()));
		initCompetitions();
		 competitions.stream().map(c -> c.getSeasons()).forEach(
		 ss -> ss.stream().forEach(s ->
		 System.out.println(s.getCompetition().getId() + " " + s.getId())));
		
		 competitions.stream().map(c -> c.getSeasons()).forEach(
		 ls -> ls.stream().map(s ->
		 s.getTours()).forEach(lt->lt.stream().forEach(t->
		 System.out.println(t.getSeason().getCompetition().getId()+"	 "+t.getSeason().getId() + " " + t.getId()
		
		
		 ))));

	}

	private static void initCompetitions() {
		 Stream.of("c1","c2","c3","c4").forEach(c->{
		 Competition competition=new Competition();
		 competition.setName(c);
		 competitionProxy.doCrud().add(competition);
		
		 });
		competitions = competitionProxy.doCrud().findAll(Competition.class);
		competitions.forEach(c -> initSeasons(c));
		competitions.stream().map(c -> c.getSeasons()).forEach(ss -> ss.stream().forEach(s -> initTours(s)));

	}

	private static void initTours(Season s) {

		 Stream.of(Order.R32, Order.R16, Order.QF, Order.SF,
		 Order.FINAL).forEach(p -> {
		 Tour tour = new Tour();
		 tour.setPosition(p);
		 tour.setSeason(s);
		 tourProxy.doCrud().add(tour);
		 });
		 s.setTours(tourProxy.doCrud().findBy(Tour.class, "season.id", String.valueOf(s.getId())));

	}

	private static void initSeasons(Competition c) {
		 for (int i = 0; i < 2; i++) {
		 Season season = new Season();
		 season.setCompetition(c);
		 seasonProxy.doCrud().add(season);
		 }
		 c.setSeasons(seasonProxy.doCrud().findBy(Season.class,
		 "competition.id", String.valueOf(c.getId())));
	}

	private static void initPlayers() {
		for (int i = 0; i < 64; i++) {
			Player player=new Player();
			 player.setFullName(String.valueOf(i));
			 playerProxy.doCrud().add(player);
		}
		
		 
		players = playerProxy.doCrud().findAll(Player.class);
	}

	private static void initCourts() {
		 Stream.of(Surface.CARPET, Surface.CLAY, Surface.GRASS,
		 Surface.HARD).forEach
		 (s-> {
		 Court court = new Court();
		 court.setSurface(s);
		 courtProxy.doCrud().add(court);
		 });
		Courts = courtProxy.doCrud().findAll(Court.class);
	}
}

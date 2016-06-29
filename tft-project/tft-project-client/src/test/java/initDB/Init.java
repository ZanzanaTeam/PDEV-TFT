package initDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
	static Random random = new Random();
	static ServicesBasicDelegate<Court> courtProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Competition> competitionProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Season> seasonProxy = new ServicesBasicDelegate<>();
	static ServicesBasicDelegate<Tour> tourProxy = new ServicesBasicDelegate<>();

	static List<Player> players;
	static List<Court> Courts;
	static List<Competition> competitions;
	private static int i;

	public static void main(String[] args) {
		initPlayers();

		initCourts();
		Courts.stream().forEach(c -> System.out.println(c.getSurface()));
		initCompetitions();
		competitions.stream().map(c -> c.getSeasons())
				.forEach(ls -> ls.stream().map(s -> s.getTours()).forEach(lt -> lt.stream().forEach(t -> System.out
						.println(t.getSeason().getCompetition().getId() + "	 " + t.getSeason().getId() + " " + t.getId()

		))));

	}

	private static void initCompetitions() {
		List<Surface> list=Arrays.asList(Surface.CARPET,Surface.CLAY,Surface.GRASS,Surface.HARD);
		i=0;
		Stream.of("Roland Garros", "US Open", "Australian Open", "Wimbledon").forEach(c -> {
			Competition competition = new Competition();
			competition.setName(c);
			competition.setSurface(list.get(i));
			competition.setNbSet(i+1);
			competitionProxy.doCrud().add(competition);
			i++;

		});
		competitions = competitionProxy.doCrud().findAll(Competition.class);
		competitions.forEach(c -> initSeasons(c));
		competitions.stream().map(c -> c.getSeasons()).forEach(ss -> ss.stream().forEach(s -> initTours(s)));

	}

	private static void initTours(Season s) {

		Stream.of(Order.R32, Order.R16, Order.R8,Order.QF, Order.SF, Order.FINAL).forEach(p -> {
			Tour tour = new Tour();
			tour.setPosition(p);
			tour.setSeason(s);
			tour.setTitle(""+s);
			tourProxy.doCrud().add(tour);
		});
		s.setTours(tourProxy.doCrud().findBy(Tour.class, "season.id", String.valueOf(s.getId())));

	}

	private static void initSeasons(Competition c) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;

		for (int i = 0; i < 5; i++) {

			try {
				startDate = sdf.parse((2012 + i) + "-06-10");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				endDate = sdf.parse((2012 + i) + "-07-10");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Season season = new Season();
			season.setCompetition(c);
			season.setStartDate(startDate);
			season.setEndDate(endDate);
			seasonProxy.doCrud().add(season);
		}
		c.setSeasons(seasonProxy.doCrud().findBy(Season.class, "competition.id", String.valueOf(c.getId())));
	}

	private static void initPlayers() {
		for (int i = 1; i < 65; i++) {
			Player player = new Player();
			player.setFullName("Player " + i);

			player.setClassement(random.nextInt(150));
			playerProxy.doCrud().add(player);
		}

		players = playerProxy.doCrud().findAll(Player.class);
	}

	private static void initCourts() {
		Stream.of(Surface.CARPET, Surface.CLAY, Surface.GRASS, Surface.HARD).forEach(s -> {
			Court court = new Court();
			court.setName("Court " + s);
			court.setSurface(s);
			courtProxy.doCrud().add(court);
		});
		Courts = courtProxy.doCrud().findAll(Court.class);
	}
}

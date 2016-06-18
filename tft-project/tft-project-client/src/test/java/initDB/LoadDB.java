package initDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Player;
import domain.Season;
import domain.Tour;
import enumeration.CompetitionLevel;
import enumeration.Gender;
import enumeration.Order;

public class LoadDB {

	// create an object of SingleObject
	private static LoadDB db = new LoadDB();

	private LoadDB() {
	}

	// Get the only object available
	public static LoadDB getInstance() {
		return db;
	}

	public final static Integer MIN_YEAR = 2012;
	public final static Integer MAX_YEAR = 2016;
	private static int number = 0;

	public static void main(String[] args) {
		List<Player> players = db.generatePlayers();
		try {
			List<Competition> competitions = db.generateCompetition();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public List<Player> generatePlayers() {

		List<Player> list = null;
		Random random = new Random();
		for (int i = 1; i <= 32; i++) {
			Player player = new Player();
			player.setFullName("Player " + i);
			player.setAge(random.nextInt((30 - 20) + 1) + 20);
			player.setCountry("Tunisia");
			player.setGender(Gender.Male);
			player.setHeight((float) 1.8);
			player.setBirthDate(new Date(1989, 01, 01));
			player.setBirthPlace("Tunis");
			new ServicesBasicDelegate<Player>().doCrud().add(player);

		}
		list = new ServicesBasicDelegate<Player>().doCrud().findAll(Player.class);
		return list;
	}

	public List<Competition> generateCompetition() throws ParseException {

		Competition rolandGarros = new Competition();
		rolandGarros.setName("Roland Garros");
		rolandGarros.setCountry("France");
		rolandGarros.setCompetitionLevel(CompetitionLevel.International);
		rolandGarros.setNbSet(2);

		Competition usOpen = new Competition();
		usOpen.setName("US Open");
		usOpen.setCountry("USA");
		usOpen.setCompetitionLevel(CompetitionLevel.International);
		usOpen.setNbSet(3);
		
		Competition australianOpen = new Competition();
		australianOpen.setName("Australian Open");
		australianOpen.setCountry("Australie");
		australianOpen.setCompetitionLevel(CompetitionLevel.International);
		australianOpen.setNbSet(3);
		
		Competition wimbledon = new Competition();
		wimbledon.setName("Wimbledon");
		wimbledon.setCountry("Australie");
		wimbledon.setCompetitionLevel(CompetitionLevel.International);
		wimbledon.setNbSet(2);

		new ServicesBasicDelegate<Competition>().doCrud().add(rolandGarros);
		new ServicesBasicDelegate<Competition>().doCrud().add(usOpen);
		new ServicesBasicDelegate<Competition>().doCrud().add(australianOpen);
		new ServicesBasicDelegate<Competition>().doCrud().add(wimbledon);

		List<Competition> competitions = new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);

		for (Competition competition : competitions) {
			System.out.println("-----Competition " + competition.getId() + " = " + competition.getName() + " -----\n");
			List<Season> seasons = db.generateSeasons(competition);
			competition.setSeasons(seasons);

		}
		List<Competition> list = new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
		return list;
	}

	@SuppressWarnings("deprecation")
	public List<Season> generateSeasons(Competition competition) throws ParseException {
		List<Season> list = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date endDate;
		for (Integer i = MIN_YEAR; i <= MAX_YEAR; i++) {
			Season season = new Season();
			startDate = sdf.parse((2011 + i)+"-06-10");
			endDate = sdf.parse((2011 + i)+"-07-10");
			season.setStartDate(startDate);
			season.setEndDate(endDate);
			season.setGender(Gender.Male);
			season.setTitle(competition.getName() + "-" + i + " Men");
			season.setCompetition(competition);
			new ServicesBasicDelegate<Season>().doCrud().add(season);
		}

		List<Season> seasons = new ServicesBasicDelegate<Season>().doCrud().findBy(Season.class, "competition",
				competition.getId().toString());
		for (Season season : seasons) {
			System.out.println("----------Season " + seasons.indexOf(season) + " = " + season.getTitle() + " -----\n");
			List<Tour> tours = db.genreateTours(season);
			season.setTours(tours);
		}
		return list;
	}

	private List<Tour> genreateTours(Season season) {

		Tour f = new Tour(null, "Final", Order.FINAL, season, null);

		Tour sf = new Tour(null, "Semi-Final", Order.SF, season, null);

		Tour qf = new Tour(null, "Quart-Final", Order.QF, season, null);

		Tour r16 = new Tour(null, "Round-16", Order.R16, season, null);

		Tour r32 = new Tour(null, "Round-32", Order.R32, season, null);

		new ServicesBasicDelegate<Tour>().doCrud().add(f);
		new ServicesBasicDelegate<Tour>().doCrud().add(sf);
		new ServicesBasicDelegate<Tour>().doCrud().add(qf);
		new ServicesBasicDelegate<Tour>().doCrud().add(r16);
		new ServicesBasicDelegate<Tour>().doCrud().add(r32);
		System.out.println("--------------- Tour " + f.getTitle() + " -----\n");
		System.out.println("--------------- Tour " + sf.getTitle() + " -----\n");
		System.out.println("--------------- Tour " + qf.getTitle() + " -----\n");
		System.out.println("--------------- Tour " + r16.getTitle() + " -----\n");
		System.out.println("--------------- Tour " + r32.getTitle() + " -----\n");
		List<Tour> tours = new ServicesBasicDelegate<Tour>().doCrud().findBy(Tour.class, "season_id",
				season.getId().toString());
		return tours;
	}
}

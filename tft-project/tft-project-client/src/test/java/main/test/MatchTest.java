package main.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;
import domain.Match;
import domain.Referee;
import enumeration.CompetitionLevel;

public class MatchTest {
	private ServicesBasicDelegate<Match> proxy;
	private Match match;

	@Before
	public void setUp() throws Exception {
		
		proxy = new ServicesBasicDelegate<Match>();
		
		match = new Match(new Date(), new Court("CourtName", "CourtAddress", 95.3, 65.7),
				new Referee("RefreeName", 45, CompetitionLevel.National),new Competition("competitionName", new Date(), new Date(), "countryName", "siteName", CompetitionLevel.Amateur, new ArrayList<>()) );
	
	}

	@Test
	public void testAdd() {
		proxy.doCrud().add(match);
	}

	@Test
	public void testUpdate() {
		
		
//		Court court1 = new ServicesBasicDelegate<Court>().doCrud().findById(1, Court.class);
//		Court court2 = new ServicesBasicDelegate<Court>().doCrud().findById(1, Court.class);
//		new ServicesBasicDelegate<Court>().doCrud().add(court1);
//		new ServicesBasicDelegate<Court>().doCrud().add(court2);
//		Referee referee1=new Referee("RefreeName1", 45, CompetitionLevel.Amateur);
//		Referee referee2=new Referee("RefreeName2", 67, CompetitionLevel.International);
//		new ServicesBasicDelegate<Referee>().doCrud().add(referee1);
//		new ServicesBasicDelegate<Referee>().doCrud().add(referee2);
		
		Match match1=proxy.doCrud().findById(11, Match.class);
		Match match2=proxy.doCrud().findById(12, Match.class);
		
		match1.setReferee(new ServicesBasicDelegate<Referee>().doCrud().findById(1, Referee.class));
		match2.setReferee(new ServicesBasicDelegate<Referee>().doCrud().findById(2, Referee.class));
		match1.setCourt(new ServicesBasicDelegate<Court>().doCrud().findById(1, Court.class));
		match2.setCourt(new ServicesBasicDelegate<Court>().doCrud().findById(2, Court.class));
		
		proxy.doCrud().update(match1);
		proxy.doCrud().update(match2);
		
		
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		
		for (Match match : proxy.doCrud().findAll(Match.class)) {
			System.out.println(match.getId());
			System.out.println(match.getCompetition().getId());
		}
	}

	@Test
	public void testFindBy() {
		fail("Not yet implemented");
	}

}

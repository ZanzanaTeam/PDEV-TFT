package main.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Match;
import enumeration.CompetitionLevel;

public class CompetitionCrudTest {
	private ServicesBasicDelegate<Competition> proxy;
	private Competition competition;

	@Before
	public void setUp() throws Exception {
		proxy = new ServicesBasicDelegate<Competition>();
		List<Match> matchs = new ArrayList<>();
		Match match1 = new Match();
		match1.setDateMatch(new Date());
		Match match2 = new Match();
		match2.setDateMatch(new Date());
		matchs.add(match1);
		matchs.add(match2);
		competition = new domain.Competition("CompetName", new Date(), new Date(), "countryName", "siteName",
				CompetitionLevel.Amateur, matchs);
		competition.getMatchs().get(0).setCompetition(competition);
		competition.getMatchs().get(1).setCompetition(competition);

	}

	@Test
	public void testServicesBasic() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
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
		List<Competition> competitions = proxy.doCrud().findAll(Competition.class);
		for (Competition com : competitions) {
			System.out.println("id comp:   " + com.getId());
			for (Match match : com.getMatchs()) {
				System.out.println("id match:   " + match.getId());
				System.out.println("date " + match.getDateMatch());

			}
		}

	}

	@Test
	public void testAdd() {
		proxy.doCrud().add(competition);
	}

	@Test
	public void testFindBy() {
		fail("Not yet implemented");
	}

}

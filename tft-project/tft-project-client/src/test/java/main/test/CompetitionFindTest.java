package main.test;

import java.util.List;

import javax.naming.NamingException;

import delegate.ServicesBasicDelegate;
import domain.Match;

public class CompetitionFindTest {

	public static void main(String[] args) throws NamingException {


		List<Match> matchs = new ServicesBasicDelegate<Match>().doCrud().findBy(Match.class, "competition_id", "7");

		System.out.println(matchs.isEmpty());
	}

}

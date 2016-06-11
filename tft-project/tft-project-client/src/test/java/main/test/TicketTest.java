package main.test;

import java.util.List;

import delegate.ServicesBasicDelegate;
import domain.Match;
import domain.Ticket;

public class TicketTest {

	public static void main(String[] args) {
		Match match = new ServicesBasicDelegate<Match>().doCrud().findById(1, Match.class);

		Ticket ticket = new Ticket("Gradin", match, 100, (float) 10.4);
		
		new ServicesBasicDelegate<Ticket>().doCrud().add(ticket);
		
	}
}

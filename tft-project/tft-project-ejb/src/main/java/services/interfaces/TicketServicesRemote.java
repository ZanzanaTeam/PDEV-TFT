package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Match;
import domain.Ticket;

@Remote
public interface TicketServicesRemote {

	List<Ticket> findTicketByMatch(Match match);
	List<Match>  findNextMatchs();
}

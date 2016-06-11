package services.interfaces.basic;

import javax.ejb.Local;

import domain.Player;
import domain.Referee;
import domain.Ticket;

/**
 * @author MedAymen
 * 
 */

@Local
public interface FactoryServiceLocal {

	ServicesBasicLocal<Player> getSimplePlayerEjb();
	ServicesBasicLocal<Referee> getRefereeEjb();
	ServicesBasicLocal<Ticket>  getTicketEjb();

}

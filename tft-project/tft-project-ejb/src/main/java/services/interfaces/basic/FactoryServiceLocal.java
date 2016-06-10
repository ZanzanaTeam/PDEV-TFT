package services.interfaces.basic;

import javax.ejb.Local;

import domain.Player;
import domain.Referee;

/**
 * @author MedAymen
 * 
 */

@Local
public interface FactoryServiceLocal {

	ServicesBasicLocal<Player> getSimplePlayerEjb();
	ServicesBasicLocal<Referee> getRefereeEjb();

}

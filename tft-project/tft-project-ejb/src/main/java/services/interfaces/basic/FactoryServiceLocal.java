package services.interfaces.basic;

import javax.ejb.Local;

import domain.Player;

/**
 * @author MedAymen
 * 
 */

@Local
public interface FactoryServiceLocal {

	ServicesBasicLocal<Player> getSimplePlayerEjb();
}

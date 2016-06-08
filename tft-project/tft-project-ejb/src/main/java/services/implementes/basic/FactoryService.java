package services.implementes.basic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import domain.Player;
import services.interfaces.basic.FactoryServiceLocal;
import services.interfaces.basic.ServicesBasicLocal;

/**
 * @author MedAymen
 * 
 */

@Stateless
public class FactoryService implements FactoryServiceLocal {

	@EJB
	ServicesBasicLocal<Player> player;

	public FactoryService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ServicesBasicLocal<Player> getSimplePlayerEjb() {
		return player;
	}

}
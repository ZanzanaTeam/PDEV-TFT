package delegate;

import locator.ServiceLocator;
import services.interfaces.CompetitionServicesRemote;


public class CompetitionServicesDelegate{

	public final String jndiName = "tft-project-ear/tft-project-ejb/PlayerServices!services.interfaces.PlayerServicesRemote";

	public CompetitionServicesRemote getProxy() {
		return (CompetitionServicesRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}

}
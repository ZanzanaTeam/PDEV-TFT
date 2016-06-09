package delegate;

import locator.ServiceLocator;
import services.interfaces.PlayerServicesRemote;

public class PlayerServicesDelegate{
	public final String jndiName = "tft-project-ear/tft-project-ejb/PlayerServices!services.interfaces.PlayerServicesRemote";

	public PlayerServicesRemote getProxy() {
		return (PlayerServicesRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}

}
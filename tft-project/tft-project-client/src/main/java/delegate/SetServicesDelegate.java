package delegate;

import locator.ServiceLocator;
import services.interfaces.SetServicesRemote;

public class SetServicesDelegate{
	public final String jndiName = "tft-project-ear/tft-project-ejb/SetServices!services.interfaces.SetServicesRemote";

	public SetServicesRemote getProxy() {
		return (SetServicesRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}

}
package delegate;

import locator.ServiceLocator;
import services.interfaces.RefereeServicesRemote;

public class RefereeServicesDelegate{
	public final String jndiName = "tft-project-ear/tft-project-ejb/RefereeServices!services.interfaces.RefereeServicesRemote";

	public RefereeServicesRemote getProxy() {
		return (RefereeServicesRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}

}
package delegate;

import locator.ServiceLocator;
import services.interfaces.TicketServicesRemote;

public class TicketServicesDelegate {
	public final String jndiName = "tft-project-ear/tft-project-ejb/TicketServices!services.interfaces.TicketServicesRemote";

	public TicketServicesRemote getProxy() {
		return (TicketServicesRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}
}

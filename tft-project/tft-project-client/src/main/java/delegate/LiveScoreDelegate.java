package delegate;

import locator.ServiceLocator;
import services.interfaces.LiveScoreServicesRemote;

public class LiveScoreDelegate {
	public final String jndiName = "tft-project-ear/tft-project-ejb/LiveScoreServices!services.interfaces.LiveScoreServicesRemote";

	public LiveScoreServicesRemote getProxy() {
		return (LiveScoreServicesRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}
}

package delegate;

import locator.ServiceLocator;
import services.interfaces.basic.ServicesBasicRemote;

public class ServicesBasicDelegate<T> {
	public final String jndiName = "tft-project-ear/tft-project-ejb/ServicesBasic!services.interfaces.basic.ServicesBasicRemote";

	private ServicesBasicRemote<T> getProxy() {
		return (ServicesBasicRemote<T>) ServiceLocator.getInstance().getProxy(
				jndiName);
	}

	public ServicesBasicRemote<T> doCrud() {
		return getProxy();
	}
}
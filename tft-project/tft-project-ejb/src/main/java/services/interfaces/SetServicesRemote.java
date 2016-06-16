package services.interfaces;

import javax.ejb.Remote;

import domain.SetMatch;

@Remote
public interface SetServicesRemote {
	
	String addSet(SetMatch set);
}

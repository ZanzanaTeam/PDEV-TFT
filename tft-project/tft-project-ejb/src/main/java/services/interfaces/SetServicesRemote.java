package services.interfaces;

import java.util.List;


import javax.ejb.Remote;

import domain.SetMatch;

@Remote

public interface SetServicesRemote {
	
	String addSet(SetMatch set);
	List<SetMatch> findAllSets(Integer idMatch);
	List<Integer>findPointSet(Integer idSet, Integer idpalyer) ; 
}

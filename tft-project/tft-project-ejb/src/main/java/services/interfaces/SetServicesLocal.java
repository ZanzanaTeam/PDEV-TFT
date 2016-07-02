package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.SetMatch;

@Local
public interface SetServicesLocal {
	String addSet(SetMatch set);
	List<SetMatch> findAllSets(Integer idMatch);
	List<Integer>findPointSet(Integer idSet, Integer idpalyer) ; 
}

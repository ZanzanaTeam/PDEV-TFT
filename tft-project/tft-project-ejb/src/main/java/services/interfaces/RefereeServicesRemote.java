package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Referee;


@Remote
public interface RefereeServicesRemote {
	
	List<Referee> findRefereeByWord(String word);
}

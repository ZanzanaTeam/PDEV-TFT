package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Match;
import domain.Referee;


@Remote
public interface RefereeServicesRemote {
	
	List<Referee> findRefereeByWord(String word);
	List<Match> findMatchByReferee(Referee referee);
}

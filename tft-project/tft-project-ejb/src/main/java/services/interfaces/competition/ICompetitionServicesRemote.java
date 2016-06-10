package services.interfaces.competition;

import java.util.List;

import javax.ejb.Remote;

import domain.Match;

@Remote
public interface ICompetitionServicesRemote {

	public List<Match> findMatchByCompetition (Integer idCompetition  ) ;
		
	
}

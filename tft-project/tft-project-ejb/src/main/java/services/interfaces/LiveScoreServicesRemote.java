package services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import domain.Match;
import domain.MatchSingle;

@Remote
public interface LiveScoreServicesRemote {
	List<MatchSingle> getMatchByDate(Date date);
	void updateLiveScoreOfMatch(int id , String liveScore );
}

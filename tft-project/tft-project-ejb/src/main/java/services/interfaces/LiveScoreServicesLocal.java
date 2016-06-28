package services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import domain.MatchSingle;

@Local
public interface LiveScoreServicesLocal {
	void updateLiveScoreOfMatch(int id , String liveScore );
	List<MatchSingle> getMatchByDate(Date date);

}

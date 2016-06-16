package services.interfaces;

import javax.ejb.Local;

@Local
public interface LiveScoreServicesLocal {
	void updateLiveScoreOfMatch(int id , String liveScore );
}

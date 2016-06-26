package bean;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import modele.PlayerRank;
import utility.RankingInternationalWeb;

@ManagedBean
@ApplicationScoped
public class RankInternational {

	private List<PlayerRank> playerRanks;
	private List<PlayerRank> playerRanksWTA;

	/**
	 * @gender : atp or wta
	 * @date : date now
	 * @age : 0
	 * @county : TUN / 0 for all pays
	 * @return List PlayerRank object
	 * @throws IOException
	 */

	public List<PlayerRank> getPlayerRanks() throws IOException {
		return RankingInternationalWeb.getClassement(new Date(), "0", "0", "atp");
	}

	public void setPlayerRanks(List<PlayerRank> playerRanks) {
		this.playerRanks = playerRanks;
	}

	public List<PlayerRank> getPlayerRanksWTA() throws IOException {
		return RankingInternationalWeb.getClassement(new Date(), "0", "0", "wta");
	}

	public void setPlayerRanksWTA(List<PlayerRank> playerRanksWTA) {
		this.playerRanksWTA = playerRanksWTA;
	}

}

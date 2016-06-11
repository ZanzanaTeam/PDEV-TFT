package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MatchDuel extends Match {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerDuel playerDuel;
	private PlayerDuel playerDuel2;
	
	public MatchDuel() {
		// TODO Auto-generated constructor stub
	}

	public MatchDuel(PlayerDuel playerDuel, PlayerDuel playerDuel2) {
		super();
		this.playerDuel = playerDuel;
		this.playerDuel2 = playerDuel2;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public PlayerDuel getPlayerDuel() {
		return playerDuel;
	}

	public void setPlayerDuel(PlayerDuel playerDuel) {
		this.playerDuel = playerDuel;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public PlayerDuel getPlayerDuel2() {
		return playerDuel2;
	}

	public void setPlayerDuel2(PlayerDuel playerDuel2) {
		this.playerDuel2 = playerDuel2;
	}	
}

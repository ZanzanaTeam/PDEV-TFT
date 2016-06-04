package domain;

import javax.persistence.Embedded;

import embedded.PlayerDuel;

public class MatchDuel extends Match{
	
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

	@Embedded
	public PlayerDuel getPlayerDuel() {
		return playerDuel;
	}
	@Embedded
	public void setPlayerDuel(PlayerDuel playerDuel) {
		this.playerDuel = playerDuel;
	}

	public PlayerDuel getPlayerDuel2() {
		return playerDuel2;
	}

	public void setPlayerDuel2(PlayerDuel playerDuel2) {
		this.playerDuel2 = playerDuel2;
	}	
}

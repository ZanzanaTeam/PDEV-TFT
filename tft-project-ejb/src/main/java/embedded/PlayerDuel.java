package embedded;

import javax.persistence.Embeddable;

import domain.Player;

@Embeddable
public class PlayerDuel {
	
	private Player player1;
	private Player player2;
	
	public PlayerDuel() {
		// TODO Auto-generated constructor stub
	}

	public PlayerDuel(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}
}

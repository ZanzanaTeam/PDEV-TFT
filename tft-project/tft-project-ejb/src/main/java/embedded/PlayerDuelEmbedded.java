package embedded;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import domain.Player;

@Embeddable
public class PlayerDuelEmbedded {

	private Player player1;
	private Player player2;

	public PlayerDuelEmbedded() {
		// TODO Auto-generated constructor stub
	}

	public PlayerDuelEmbedded(Player player1, Player player2) {
		super();
		this.setPlayer1(player1);
		this.setPlayer2(player2);
	}

	@ManyToOne
	@JoinColumn(name="dual1" , referencedColumnName="id" , updatable =false , insertable = false)
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	@ManyToOne
	@JoinColumn(name="dual2" , referencedColumnName="id" , updatable =false , insertable = false)
	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
}

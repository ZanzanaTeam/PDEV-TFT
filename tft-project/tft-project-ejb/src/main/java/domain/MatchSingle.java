package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MatchSingle extends Match {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private Player player;
	private Player player2;

	public MatchSingle() {

	}

	public MatchSingle(Player player, Player player2) {
		super();
		this.player = player;
		this.player2 = player2;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
}

package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

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

	@ManyToOne
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@ManyToOne
	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	@XmlTransient
	@Transient
	public int getScore(Player player1) {
		int score = 0;

		if (getSets() != null) {
			for (SetMatch set : getSets()) {
				if (set.getWinner().getId() == player1.getId())
					score++;
			}
		}
		return score;
	}

	@XmlTransient
	@Transient
	public Player getWinner() {

		Player winner = null;
		Player player1 = getSets().get(0).getWinner();
		Player player2 = null;

		if (getSets() == null)
			return null;

		for (SetMatch setMatch : getSets()) {
			if (setMatch.getWinner().getId() != player1.getId()) {
				player2 = setMatch.getWinner();
			}
		}
		if (player2 == null)
			return player1;
		if (getScore(player1) == getScore(player2)) {
			return winner;
		} else {
			winner = (getScore(player1) > getScore(player2) ? player1 : player2);
		}
		return winner;

	}
}

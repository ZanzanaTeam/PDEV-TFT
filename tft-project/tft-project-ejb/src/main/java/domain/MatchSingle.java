package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
				if (set.getWinner() == player1)
					score++;
			}
		}
		return score;
	}

	@XmlTransient
	@Transient
	public Player getWinner() {
		List<Player> players = new ArrayList<>();
		if (getSets() == null)
			return null;
		for (SetMatch set : getSets()) {
			if (!players.contains(set.getWinner())) {
				players.add(set.getWinner());
			}
		}
		if (players.size() == 1)
			return players.get(0);
		return (getScore(players.get(0)) > getScore(players.get(1)) ? players.get(0) : players.get(1));

	}
}

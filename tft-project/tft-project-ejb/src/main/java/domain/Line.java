package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Line implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private MatchSingle match;
	private int game;
	private Combination combination;

	public Line() {

	}

	public Line(MatchSingle match, int game) {
		this.match = match;
		this.game = game;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	public Combination getCombination() {
		return combination;
	}

	public void setCombination(Combination combination) {
		this.combination = combination;
	}

	@ManyToOne
	@JoinColumn(name = "match_id", referencedColumnName = "id")
	public MatchSingle getMatch() {
		return match;
	}

	public void setMatch(MatchSingle match) {
		this.match = match;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}

}

package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Jeu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private SetMatch set;
	private List<Point> points;

	private boolean serve;//
	private boolean lostServe;//

	public Jeu() {
		// TODO Auto-generated constructor stub
	}

	public Jeu(Integer id) {
		super();
		this.setId(id);

	}

	@ManyToOne(cascade = CascadeType.ALL)
	public SetMatch getSet() {
		return set;
	}

	public void setSet(SetMatch set) {
		this.set = set;
	}

	@OneToMany(mappedBy = "jeu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isServe() {
		return serve;
	}

	public void setServe(boolean serve) {
		this.serve = serve;
	}

	public boolean isLostServe() {
		return lostServe;
	}

	public void setLostServe(boolean lostServe) {
		this.lostServe = lostServe;
	}

	public int getScore(Player player1) {
		int score = 0;

		if (points != null) {
			for (Point point : points) {
				if (point.getPlayer() == player1) {
					score++;
				}
			}

		}

		return score;
	}

	public Player getWinner() {

		List<Player> players = new ArrayList<>();

		if (points == null)
			return null;

		for (Point point : points) {
			if (!players.contains(point.getPlayer())) {
				players.add(point.getPlayer());
			}
		}

		if (players.size() == 1)
			return players.get(0);
		return (getScore(players.get(0)) > getScore(players.get(1)) ? players.get(0) : players.get(1));

	}
}
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Jeu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private SetMatch set;
	private List<Point> points;
	private Player serve;
	private boolean lostServe;

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
	@XmlTransient
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

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@XmlTransient
	public Player getServe() {
		return serve;
	}

	public void setServe(Player serve) {
		this.serve = serve;
	}

	public boolean isLostServe() {
		return lostServe;
	}

	public void setLostServe(boolean lostServe) {
		this.lostServe = lostServe;
	}

	@XmlTransient
	@Transient
	public Integer getScore(Player player) {
		Integer score = 0;

		if (points != null && player != null) {
			for (Point point : points) {
				if (point.getPlayer().getId() == player.getId()) {
					score++;
				}
			}
		}
		return score;
	}

	@XmlTransient
	@Transient
	public Player getWinner() {
		Player winner = null;
		Player player1 = points.get(0).getPlayer();
		Player player2 = null;
		
		if (points == null){
			return winner;
		}
		for (Point point : points) {
			if (point.getPlayer().getId() != player1.getId()){
				player2 = point.getPlayer() ;
			}
		}
		if (player2 == null) {
			winner = player1;
		}else {
			winner  = getScore(player1) > getScore(player2) ? player1 : player2;
		}
//		System.out.println("Jeu!! Player1 =" + player1.getFullName() + " ---- Player2 =" + player2.getFullName());
//		System.out.println("Winner Jeu :" + winner.getFullName());
		return winner;

	}
}

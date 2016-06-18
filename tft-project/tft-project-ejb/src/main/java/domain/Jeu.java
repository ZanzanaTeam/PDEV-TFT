package domain;

import java.io.Serializable;
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
	
	private int[] score;//
	private Player serve;//
	private boolean lostServe;//

	public Jeu() {
		// TODO Auto-generated constructor stub
	}

	public Jeu(Integer id, int[] score) {
		super();
		this.setId(id);
		this.score = score;
	}

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		this.score = score;
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
	@ManyToOne()
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

}

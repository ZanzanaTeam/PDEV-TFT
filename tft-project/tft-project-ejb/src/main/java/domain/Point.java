package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enumeration.PointType;

@Entity
public class Point implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String value;
	private boolean pointValue;
	private PointType pointType;
	private Player player;

	private Jeu jeu;

	public Point() {
		
	}

	public Point(String value, PointType pointType) {
		super();
		this.value = value;
		this.setPointType(pointType);
	}

	public Point(Boolean value, PointType pointType) {
		super();
		this.pointValue = value;
		this.setPointType(pointType);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToOne
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@ManyToOne
	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", jeu=" + jeu + "]";
	}

	public PointType getPointType() {
		return pointType;
	}

	public void setPointType(PointType pointType) {
		this.pointType = pointType;
	}

	public boolean isPointvalue() {
		return pointValue;
	}

	public void setPointvalue(boolean pointvalue) {
		this.pointValue = pointvalue;
	}
}

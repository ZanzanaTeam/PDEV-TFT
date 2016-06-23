package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import embedded.Resultat;

@Entity(name = "set_match")
public class SetMatch implements Serializable {

	/**
	 * 
	 */

	private Integer id;
	private int[] score;
	private int[] duration;
	private Match match;
	private Resultat resultat;
	private List<Jeu> jeus;

	private static final long serialVersionUID = 1L;

	public SetMatch() {
		// TODO Auto-generated constructor stub
	}

	public SetMatch(Integer id, Resultat resultat) {
		super();
		this.setId(id);
		this.resultat = resultat;
	}

	public SetMatch(Integer id, int[] score, int[] duration) {
		super();
		this.setId(id);
		this.setscore(score);
		this.setDuration(duration);
	}

	@Embedded
	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@OneToMany(mappedBy = "set", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Jeu> getJeus() {

//		ArrayList<Jeu> list = new ArrayList<Jeu>();
//		if (jeus.size() > 0) {
//			for (Jeu jeu : jeus) {
//				if (list.contains(jeu) == false) {
//					list.add(jeu);
//				}
//			}
//			jeus.clear();
//			jeus.addAll(list);
//		}
		return jeus;

	}

	public void setJeus(List<Jeu> jeus) {
		this.jeus = jeus;
	}

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int[] getscore() {
		return score;
	}

	public void setscore(int[] score) {
		this.score = score;
	}

	public int[] getDuration() {
		return duration;
	}

	public void setDuration(int[] duration) {
		this.duration = duration;
	}

	@SuppressWarnings("null")
	@Transient
	public int getScore(Player player) {
		Integer score = 0;
		Map<Integer, Integer> list = new HashMap<>();
		list.put(0, 0);
		if (jeus != null) {
			for (Jeu jeu : jeus) {
				if (list.containsValue(jeu.getId()) == false && jeu.getWinner().getId() == player.getId()) {
					score++;
					list.put(jeu.getId(), jeu.getId());
				}
			}
		}
		return score;
	}

	@Transient
	public Player getWinner() {

		Player winner = null;
		Player player2 = null;
		if (jeus == null || jeus.size() == 0) {
			return winner;
		}

		Player player1 = jeus.get(0).getWinner();
		for (Jeu jeu : jeus) {
			if (jeu.getWinner().getId() != player1.getId()) {
				player2 = jeu.getWinner();
			}
		}
		if (player2 == null) {
			winner = player1;
		} else if (getScore(player1) == getScore(player2)) {
			winner = player1;
		} else {
			winner = (getScore(player1) > getScore(player2) ? player1 : player2);
		}
		return winner;
	}
}

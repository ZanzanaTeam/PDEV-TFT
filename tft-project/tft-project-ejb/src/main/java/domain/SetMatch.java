package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

	@ManyToOne
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@OneToMany(mappedBy = "set", fetch=FetchType.EAGER )
	public List<Jeu> getJeus() {
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
	@Transient
	public int getScore(Player player1) {
		int score = 0;

		if (jeus != null) {
			for (Jeu jeu: jeus) {
				if (jeu.getWinner()== player1)

					score++;

			}

		}

		return score;
	}
	@Transient
	public Player getWinner() {
		
		List<Player> players = new ArrayList<>();
		

		if (jeus == null) return null;
			
		for (Jeu jeu: jeus) {
				if (!players.contains(jeu.getWinner())) {
					players.add(jeu.getWinner());
				}
			}
		
		if(players.size()==1)return players.get(0);
		return(getScore(players.get(0))>getScore(players.get(1))?players.get(0):players.get(1));
		
	}
}

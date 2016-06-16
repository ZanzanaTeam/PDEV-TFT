package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import embedded.Resultat;

@Entity(name = "set_match")
public class SetMatch implements Serializable {

	/**
	 * 
	 */

	private Integer id;
	private Resultat resultat;
	private Match match;

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

	@OneToMany(mappedBy = "set", cascade = CascadeType.ALL)
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
}

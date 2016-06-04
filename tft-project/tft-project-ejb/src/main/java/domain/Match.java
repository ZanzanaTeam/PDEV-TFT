package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="match_game")
public class Match implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateMatch;
	private Court court;
	private Referee referee;
	private Competition competition;

	public Match() {
		// TODO Auto-generated constructor stub
	}
	
	public Match(Date dateMatch, Court court, Referee referee, Competition competition) {
		super();
		this.dateMatch = dateMatch;
		this.court = court;
		this.referee = referee;
		this.competition = competition;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateMatch() {
		return dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}
	
	@ManyToOne
	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}
	@ManyToOne
	public Referee getReferee() {
		return referee;
	}

	public void setReferee(Referee referee) {
		this.referee = referee;
	}

	@ManyToOne(optional=true)
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
}

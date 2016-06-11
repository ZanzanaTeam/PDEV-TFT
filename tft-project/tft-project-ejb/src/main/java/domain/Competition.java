package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enumeration.CompetitionLevel;

@Entity
public class Competition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date startDate;

	private Date endDate;
	private String country;
	private String Site;
	private CompetitionLevel competitionLevel;

	private List<Match> matchs;

	public Competition() {
		// TODO Auto-generated constructor stub
	}

	public Competition(String name, Date startDate, Date endDate, String country, String site,
			CompetitionLevel competitionLevel, List<Match> matchs) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
		Site = site;
		this.competitionLevel = competitionLevel;
		this.matchs = matchs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public CompetitionLevel getCompetitionLevel() {
		return competitionLevel;
	}

	public void setCompetitionLevel(CompetitionLevel competitionLevel) {
		this.competitionLevel = competitionLevel;
	}

	@OneToMany(mappedBy = "competition", fetch = FetchType.EAGER)

	public List<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}

	@Override
	public String toString() {
		return name;
	}

}

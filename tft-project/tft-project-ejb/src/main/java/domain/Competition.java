package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import enumeration.CompetitionLevel;
import enumeration.Surface;

@Entity
public class Competition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date startDate;
	private Integer nbSet;
private Surface surface;
	private Date endDate;
	private String country;
	private String Site;
	private CompetitionLevel competitionLevel;

	private List<Match> matchs;
	private List<Season> seasons;

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
	public Competition(String name, Date startDate, Date endDate, String country, String site,
			CompetitionLevel competitionLevel, List<Match> matchs, List<Season> seasons) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
		Site = site;
		this.competitionLevel = competitionLevel;
		this.matchs = matchs;
		this.seasons = seasons;
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

	@OneToMany(mappedBy = "competition", fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
	@XmlTransient
	public List<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}
	
	
	@OneToMany(mappedBy = "competition")
	@XmlTransient
	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	@Override
	public String toString() {
		return name;
	}

	public Integer getNbSet() {
		return nbSet;
	}

	public void setNbSet(Integer nbSet) {
		this.nbSet = nbSet;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

}

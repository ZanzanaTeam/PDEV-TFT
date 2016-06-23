package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import enumeration.StatusMatch;
import enumeration.WeatherState;

@Entity(name = "match_game")
public class Match implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private WeatherState weather;
	private Date dateMatch;
	private int[] score;//
	private int duration;//
	private Tour tour;
	private StatusMatch status;
	private Competition competition;
	private Court court;
	private Referee referee;
	private List<SetMatch> sets;

	private List<Ticket> tickets;

	private String liveScore;

	public Match() {
		// TODO Auto-generated constructor stub
	}

	public Match(Date dateMatch, Court court, Referee referee, Competition competition, Tour tour,
			List<Ticket> tickets) {
		super();
		this.dateMatch = dateMatch;
		this.court = court;
		this.referee = referee;
		this.competition = competition;
		this.tour = tour;
		this.tickets = tickets;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@ManyToOne(optional = false)
	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", dateMatch=" + dateMatch + "]";
	}

	@OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
	@XmlTransient
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getLiveScore() {
		return liveScore;
	}

	public void setLiveScore(String liveScore) {
		this.liveScore = liveScore;
	}

	@OneToMany(mappedBy = "match", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlTransient
	public List<SetMatch> getSets() {
		return sets;
	}

	public void setSets(List<SetMatch> sets) {
		this.sets = sets;
	}
	@Column(nullable= true)
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public WeatherState getWeather() {
		return weather;
	}

	public void setWeather(WeatherState weather) {
		this.weather = weather;
	}

	public StatusMatch getStatus() {
		return status;
	}

	public void setStatus(StatusMatch status) {
		this.status = status;
	}

}

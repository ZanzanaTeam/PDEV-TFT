package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MatchReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String reviewCourt;
	private String reviewPlayers;
	private Date matchReportDate;
	private Player player1;
	private Player player2;
	private MatchSingle match_game;
	private Court court; 

	public MatchReport() {
		// TODO Auto-generated constructor stub
	}

	public MatchReport(String reviewCourt, String reviewPlayers, Date matchReportDate) {
		super();
		this.reviewCourt = reviewCourt;
		this.reviewPlayers = reviewPlayers;
		this.matchReportDate = matchReportDate;
	}

	@Override
	public String toString() {
		return "MatchReport [id=" + id + ", reviewCourt=" + reviewCourt + ", reviewPlayers=" + reviewPlayers
				+ ", matchReportDate=" + matchReportDate + "]";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReviewCourt() {
		return reviewCourt;
	}

	public void setReviewCourt(String reviewCourt) {
		this.reviewCourt = reviewCourt;
	}

	public String getReviewPlayers() {
		return reviewPlayers;
	}

	public void setReviewPlayers(String reviewPlayers) {
		this.reviewPlayers = reviewPlayers;
	}

	public Date getMatchReportDate() {
		return matchReportDate;
	}

	public void setMatchReportDate(Date matchReportDate) {
		this.matchReportDate = matchReportDate;
	}
	@ManyToOne
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	@ManyToOne
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	@ManyToOne
	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public MatchSingle getMatch_game() {
		return match_game;
	}

	public void setMatch_game(MatchSingle match_game) {
		this.match_game = match_game;
	}
}

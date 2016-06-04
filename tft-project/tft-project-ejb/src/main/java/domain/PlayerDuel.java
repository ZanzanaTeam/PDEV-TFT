package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import embedded.PlayerDuelEmbedded;


@Entity
public class PlayerDuel implements Serializable{

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private PlayerDuelEmbedded playerDuel;
	private PlayerDuelEmbedded playerDuel2;
	private List<MatchDuel> matchDuels;

	public PlayerDuel() {
		// TODO Auto-generated constructor stub
	}

	public PlayerDuel(PlayerDuelEmbedded playerDuel, PlayerDuelEmbedded playerDuel2) {
		super();
		this.playerDuel = playerDuel;
		this.playerDuel2 = playerDuel2;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Embedded
	public PlayerDuelEmbedded getPlayerDuel() {
		return playerDuel;
	}

	public void setPlayerDuel(PlayerDuelEmbedded playerDuel) {
		this.playerDuel = playerDuel;
	}
	
	@Embedded
	public PlayerDuelEmbedded getPlayerDuel2() {
		return playerDuel2;
	}

	public void setPlayerDuel2(PlayerDuelEmbedded playerDuel2) {
		this.playerDuel2 = playerDuel2;
	}


	@OneToMany(mappedBy="playerDuel")
	public List<MatchDuel> getMatchDuels() {
		return matchDuels;
	}

	public void setMatchDuels(List<MatchDuel> matchDuels) {
		this.matchDuels = matchDuels;
	}
}

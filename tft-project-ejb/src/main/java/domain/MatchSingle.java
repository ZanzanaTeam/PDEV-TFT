package domain;

public class MatchSingle extends Match{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private Player player2;
	
	public MatchSingle() {
		// TODO Auto-generated constructor stub
	}

	public MatchSingle(Player player, Player player2) {
		super();
		this.player = player;
		this.player2 = player2;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
}

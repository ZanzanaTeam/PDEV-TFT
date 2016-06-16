package gui.modele;

import java.util.ArrayList;
import java.util.List;

import domain.Player;

public class Score {
	
	private Player player;
	private List<Integer> manches;
	private Integer currentManche;
	private String point;
	private Boolean indServices;
	
	
	public Score() {
		// TODO Auto-generated constructor stub
		manches=new ArrayList<Integer>();
	}


	public Score(Player player, List<Integer> manches, Integer currentManche, String point, Boolean indServices) {
		super();
		this.player = player;
		this.manches = manches;
		this.currentManche = currentManche;
		this.point = point;
		this.indServices = indServices;
	}


	@Override
	public String toString() {
		return "Score [player=" + player + ", manches=" + manches + ", currentManche=" + currentManche + ", point="
				+ point + ", indServices=" + indServices + "]";
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public List<Integer> getManches() {
		return manches;
	}


	public void setManches(List<Integer> manches) {
		this.manches = manches;
	}


	public Integer getCurrentManche() {
		return currentManche;
	}


	public void setCurrentManche(Integer currentManche) {
		this.currentManche = currentManche;
	}


	public String getPoint() {
		return point;
	}


	public void setPoint(String point) {
		this.point = point;
	}


	public Boolean getIndServices() {
		return indServices;
	}


	public void setIndServices(Boolean indServices) {
		this.indServices = indServices;
	}
}

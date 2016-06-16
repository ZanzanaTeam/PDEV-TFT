package gui.modele;

import gui.modele.enumeration.StatusRanks;

public class PlayerRank {

	private String name;
	private Integer point;
	private Integer rank;
	private Integer age;
	private String winPoint;
	private Integer countPlace;
	private StatusRanks status;
	
	public PlayerRank() {
		// TODO Auto-generated constructor stub
	}

	public PlayerRank(String name, Integer point, Integer rank, Integer age, String winPoint, Integer countPlace,
			StatusRanks status) {
		super();
		this.name = name;
		this.point = point;
		this.rank = rank;
		this.age = age;
		this.winPoint = winPoint;
		this.countPlace = countPlace;
		this.status = status;
	}

	@Override
	public String toString() {
		return "PlayerRanks [name=" + name + ", point=" + point + ", rank=" + rank + ", age=" + age + ", winPoint="
				+ winPoint + ", countPlace=" + countPlace + ", status=" + status + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getWinPoint() {
		return winPoint;
	}

	public void setWinPoint(String winPoint) {
		this.winPoint = winPoint;
	}

	public Integer getCountPlace() {
		return countPlace;
	}

	public void setCountPlace(Integer countPlace) {
		this.countPlace = countPlace;
	}

	public StatusRanks getStatus() {
		return status;
	}

	public void setStatus(StatusRanks status) {
		this.status = status;
	}
}


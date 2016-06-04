package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Referee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer Id;
	private String CompetitionLevel;

	@Id
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCompetitionLevel() {
		return CompetitionLevel;
	}

	public void setCompetitionLevel(String competitionLevel) {
		CompetitionLevel = competitionLevel;
	}

}

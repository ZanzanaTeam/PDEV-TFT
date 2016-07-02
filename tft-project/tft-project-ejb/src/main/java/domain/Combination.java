package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Combination implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifier;
	private List<Line> lines;

	public Combination() {
		lines = new ArrayList<>();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Combination(List<Line> lines) {

		this.lines = lines;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "combination", fetch = FetchType.EAGER)
	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public Line containsKey(MatchSingle key) {
		for (Line line : lines) {
			if (line.getMatch().getId() == key.getId())
				return line;
		}
		return null;
	}

	public Integer getValue(MatchSingle key) {
		for (Line line : lines) {
			if (line.getMatch().getId() == key.getId())
				return line.getGame();
		}
		return null;
	}



}

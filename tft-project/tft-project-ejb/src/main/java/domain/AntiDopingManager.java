package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AntiDopingManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fullName;
	private List<AntiDopingTest> antiDopingTests;

	public AntiDopingManager() {
	}

	public AntiDopingManager(String fullName, List<AntiDopingTest> antiDopingTests) {
		super();
		this.fullName = fullName;
		this.antiDopingTests = antiDopingTests;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@OneToMany(mappedBy = "antiDopingManager",cascade=CascadeType.ALL)
	public List<AntiDopingTest> getAntiDopingTests() {
		return antiDopingTests;
	}

	public void setAntiDopingTests(List<AntiDopingTest> antiDopingTests) {
		this.antiDopingTests = antiDopingTests;
	}
}

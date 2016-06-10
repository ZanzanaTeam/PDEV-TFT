package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enumeration.CompetitionLevel;
import enumeration.Gender;

@Entity
public class Referee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String fullName;
	private Integer age;
	private Gender gender;
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	private CompetitionLevel competitionLevel;
	private List<Match> matchs;
	private Training training; // formation
	private Contest contest; // concours



	public Referee() {
		// TODO Auto-generated constructor stub
	}

	



	public Referee(String fullName, Integer age, Gender gender, CompetitionLevel competitionLevel) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.competitionLevel = competitionLevel;
	}

	public Referee(String fullName, Integer age, CompetitionLevel competitionLevel) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.competitionLevel = competitionLevel;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public CompetitionLevel getCompetitionLevel() {
		return competitionLevel;
	}

	public void setCompetitionLevel(CompetitionLevel competitionLevel) {
		this.competitionLevel = competitionLevel;
	}

	@OneToMany(mappedBy = "referee")
	public List<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}

	@ManyToOne
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@ManyToOne
	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	@Override
	public String toString() {
		return "Referee [id=" + id + ", fullName=" + fullName + ", age=" + age + ", gender=" + gender
				+ ", competitionLevel=" + competitionLevel + "]";
	}
	
}

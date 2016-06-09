package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enumeration.AgeRange;
import enumeration.Gender;

@Entity
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fullName;
	private Gender gender;
	private Integer age;
	private AgeRange ageRange;
	private Club club;
	private Doctor doctor;
	private Training training;
	private List<MatchSingle> matchSingles;
	private List<AntiDopingTest> antiDopingTests;

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String fullName, Gender gender, Integer age, AgeRange ageRange) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.ageRange = ageRange;
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", age=" + age + ", ageRange="
				+ ageRange + "]";
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public AgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@ManyToOne
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@ManyToOne
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@OneToMany(mappedBy = "player")
	public List<AntiDopingTest> getAntiDopingTests() {
		return antiDopingTests;
	}

	public void setAntiDopingTests(List<AntiDopingTest> antiDopingTests) {
		this.antiDopingTests = antiDopingTests;
	}

	@OneToMany(mappedBy = "player")
	public List<MatchSingle> getMatchSingles() {
		return matchSingles;
	}

	public void setMatchSingles(List<MatchSingle> matchSingles) {
		this.matchSingles = matchSingles;
	}
}

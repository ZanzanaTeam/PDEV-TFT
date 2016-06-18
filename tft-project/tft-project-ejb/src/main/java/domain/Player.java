package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

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
	private List<MatchSingle> matchSingles1;
	private List<MatchSingle> matchSingles2;
	private List<AntiDopingTest> antiDopingTests;

	/**
	 * By Aymen EL Ghoul
	 */

	private Date birthDate;
	private String birthPlace;
	private String country;
	private Float height;
	private Float weight;
	private String plays;
	private String coach;
	
	private int classement;//
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	

	public Player(String fullName, Gender gender, Integer age, AgeRange ageRange, Club club, Doctor doctor,
			Training training, List<MatchSingle> matchSingles, List<AntiDopingTest> antiDopingTests, Date birthDate,
			String birthPlace, String country, Float height, Float weight, String plays, String coach) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.ageRange = ageRange;
		this.club = club;
		this.doctor = doctor;
		this.training = training;
		this.matchSingles1 = matchSingles1;
		this.antiDopingTests = antiDopingTests;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.country = country;
		this.height = height;
		this.weight = weight;
		this.plays = plays;
		this.coach = coach;
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
		return fullName;
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

	@ManyToOne(cascade=CascadeType.ALL)
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@XmlTransient
	@ManyToOne(cascade=CascadeType.ALL)
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@XmlTransient
	@OneToMany(mappedBy = "player",cascade=CascadeType.ALL)
	public List<AntiDopingTest> getAntiDopingTests() {
		return antiDopingTests;
	}

	public void setAntiDopingTests(List<AntiDopingTest> antiDopingTests) {
		this.antiDopingTests = antiDopingTests;
	}

	@XmlTransient
	@OneToMany(mappedBy = "player",cascade=CascadeType.ALL)
	public List<MatchSingle> getMatchSingles1() {
		return matchSingles1;
	}

	public void setMatchSingles1(List<MatchSingle> matchSingles) {
		this.matchSingles1 = matchSingles;
	}
	
	@XmlTransient
	@OneToMany(mappedBy = "player2",cascade=CascadeType.ALL)
	public List<MatchSingle> getMatchSingles2() {
		return matchSingles2;
	}

	public void setMatchSingles2(List<MatchSingle> matchSingles) {
		this.matchSingles2 = matchSingles;
	}
	public List<MatchSingle> getAllMatchSingles() {
		List<MatchSingle> list=new ArrayList<>();
		list.addAll(getMatchSingles1());list.addAll(getMatchSingles2());
		list.stream().sorted((m1, m2) -> m1.getDateMatch().compareTo(m2.getDateMatch())).collect(Collectors.toList());
		return matchSingles2;
	}

	
	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getBirthPlace() {
		return birthPlace;
	}


	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Float getHeight() {
		return height;
	}


	public void setHeight(Float height) {
		this.height = height;
	}


	public Float getWeight() {
		return weight;
	}


	public void setWeight(Float weight) {
		this.weight = weight;
	}


	public String getPlays() {
		return plays;
	}


	public void setPlays(String plays) {
		this.plays = plays;
	}


	public String getCoach() {
		return coach;
	}


	public void setCoach(String coach) {
		this.coach = coach;
	}


	public int getClassement() {
		return classement;
	}


	public void setClassement(int classement) {
		this.classement = classement;
	}
	
	
}

package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AntiDopingTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateTest;
	private String result;
	private AntiDopingManager antiDopingManager;
	private Employee employee;
	private Player player;

	public AntiDopingTest() {
		// TODO Auto-generated constructor stub
	}

	public AntiDopingTest(Date dateTest, String result, AntiDopingManager antiDopingManager, Employee employee,
			Player player) {
		super();
		this.dateTest = dateTest;
		this.result = result;
		this.antiDopingManager = antiDopingManager;
		this.employee = employee;
		this.player = player;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateTest() {
		return dateTest;
	}

	public void setDateTest(Date dateTest) {
		this.dateTest = dateTest;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@ManyToOne
	public AntiDopingManager getAntiDopingManager() {
		return antiDopingManager;
	}

	public void setAntiDopingManager(AntiDopingManager antiDopingManager) {
		this.antiDopingManager = antiDopingManager;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}

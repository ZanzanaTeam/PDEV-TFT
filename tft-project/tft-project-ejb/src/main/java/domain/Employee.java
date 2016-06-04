package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fullname;
	private String Email;
	private String password;
	private List<AntiDopingTest> antiDopingTests;
	//droit access RoleId
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String fullname, String email, String password) {
		super();
		this.fullname = fullname;
		Email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy="employee")
	public List<AntiDopingTest> getAntiDopingTests() {
		return antiDopingTests;
	}

	public void setAntiDopingTests(List<AntiDopingTest> antiDopingTests) {
		this.antiDopingTests = antiDopingTests;
	}
}

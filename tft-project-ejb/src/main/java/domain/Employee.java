package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.registry.infomodel.EmailAddress;

@Entity
public class Employee implements Serializable{

	private Integer id;
	private String fullname;
	private String Email;
	private String password;
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
}

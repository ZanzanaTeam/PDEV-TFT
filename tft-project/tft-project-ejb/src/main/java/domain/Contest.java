package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Contest implements Serializable {

	/**
	 * Concours
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String Title;
	private Date startDate;
	private Date endDate;
	private List<Referee> referees;

	public Contest() {
		// TODO Auto-generated constructor stub
	}
	
	public Contest(String title, Date startDate, Date endDate, List<Referee> referees) {
		super();
		Title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.referees = referees;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(mappedBy="contest",cascade=CascadeType.ALL)
	public List<Referee> getReferees() {
		return referees;
	}

	public void setReferees(List<Referee> referees) {
		this.referees = referees;
	}

}

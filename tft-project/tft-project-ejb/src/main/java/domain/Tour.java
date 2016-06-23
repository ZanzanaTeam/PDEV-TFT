package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;
import enumeration.Order;

@Entity
public class Tour implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private Order position;
	private Season season;
	private List<Match> matchs;

	public Tour() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tour(Integer id, String title, Order position, Season season , List<Match> matchs) {
		super();
		this.id = id;
		this.title = title;
		this.position = position;
		this.season = season;
		this.matchs = matchs;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the position
	 */
	public Order getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Order position) {
		this.position = position;
	}

	
	@ManyToOne(optional = true,cascade=CascadeType.PERSIST)
	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	@OneToMany(mappedBy = "tour",fetch=FetchType.EAGER)
	@XmlTransient
	public List<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}

	@Override
	public String toString() {
		return title;
	}

}

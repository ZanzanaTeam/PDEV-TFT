package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Club;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@ApplicationScoped
public class ClubBean {
	@EJB
	ServicesBasicLocal<Club> proxy;

	public List<Club> getAllCompetitions() {
		List<Club> clubs = proxy.findAll(Club.class);
		return clubs;
	}

	// public int getID () {
	//
	// Map<String, String> params =
	// FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	// id = Integer.valueOf(params.get("id"));
	//
	

}

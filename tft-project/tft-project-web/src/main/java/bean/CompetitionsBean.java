package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Competition;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@SessionScoped
public class CompetitionsBean {
	@EJB
	ServicesBasicLocal<Competition> proxy;

	public List<Competition> getAllCompetitions() {
		List<Competition> competitions = new ArrayList<Competition>();
		competitions.addAll(proxy.findAll(Competition.class));
		return competitions;
	}

}

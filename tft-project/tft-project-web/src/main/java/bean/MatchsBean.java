package bean;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.MatchSingle;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@SessionScoped
public class MatchsBean {
	@EJB
	ServicesBasicLocal<MatchSingle> proxy;
	
	
	
	public List<MatchSingle> getAll() {
		List<MatchSingle> matchs = proxy.findAll(MatchSingle.class);
		//System.out.println(matchs.size());
		return matchs;
	}
	
	
	
}
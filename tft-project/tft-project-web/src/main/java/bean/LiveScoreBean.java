package bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import domain.Court;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
public class LiveScoreBean {
	@ManagedProperty(value = "#{param.id}")
	private Integer idCourt;
	private Court court;

	@EJB ServicesBasicLocal<Court> servicesBasicLocal;

    @PostConstruct
    public void init() {
    	court = servicesBasicLocal.findById(idCourt, Court.class);
        System.out.println("Stade => " + idCourt); // 9099 as in your example.
    }

	public Integer getIdCourt() {
		return idCourt;
	}

	public void setIdCourt(Integer idCourt) {
		this.idCourt = idCourt;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

}

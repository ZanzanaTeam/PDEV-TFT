package fluxrss;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.News;
import services.interfaces.basic.ServicesBasicLocal;

@Path("/rss")
@Stateless
@LocalBean
public class FluxRssRest {

	@EJB
	ServicesBasicLocal<News> servicesBasicLocal;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Rss getRss() {
		Rss rss = new Rss();
		rss.getChannel().setNews(servicesBasicLocal.findAll(News.class));
		return rss;
	}

}

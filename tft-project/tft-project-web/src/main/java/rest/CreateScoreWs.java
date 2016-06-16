package rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Match;
import domain.SetMatch;
import embedded.Resultat;
import services.implementes.basic.ServicesBasic;

@Path("score")
@Stateless
@LocalBean
public class CreateScoreWs {

	@EJB
	ServicesBasic<Match> matchServices;

	@EJB
	ServicesBasic<SetMatch> setServices;

	@GET
	@Path("{match}/{player}/{set}/{jeu}/{point}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String addPoint(@PathParam("match") Integer match_id, @PathParam("player") Integer player_id,
			@PathParam("set") Integer set, @PathParam("jeu") Integer jeu, @PathParam("point") Integer point) {
		
		return "addScore";
	}

	@GET
	@Path("{match}/{set}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String addSet(@PathParam("match") Integer match_id,
						@PathParam("set") Integer set_id) {
		Match match = matchServices.findById(match_id, Match.class);
		SetMatch set = new SetMatch(1, new Resultat(2, 3));
		set.setMatch(match);
		setServices.add(set);
		return "addSet";
	}

	@GET
	@Path("{match}/{set}")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String addJeu(@PathParam("match") Integer match_id, @PathParam("set") Integer set) {

		return "addJeu";
	}

}

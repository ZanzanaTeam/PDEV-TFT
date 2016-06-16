package rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Court;
import domain.MatchSingle;
import domain.Player;
import domain.Referee;
import services.implementes.LiveScoreServices;
import services.interfaces.basic.ServicesBasicLocal;

@Path("/liveScore")
@Stateless
@LocalBean
public class LiveScoreWS {

	@EJB
	LiveScoreServices liveSocreServices;

	@EJB
	ServicesBasicLocal<Court> courtServices;
	@EJB
	ServicesBasicLocal<Referee> refereeServices;
	@EJB
	ServicesBasicLocal<Player> playerServices;
	
	@GET
	@Path("{id}/{score}/")
	public String updateScore(@PathParam("id") String id, @PathParam("score") String score) {
		liveSocreServices.updateLiveScoreOfMatch(Integer.parseInt(id), score);
		return "update true";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MatchSingle> getMatch() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2016-06-18");
		List<MatchSingle> matchSingles = new ArrayList<MatchSingle>();
		
		for (MatchSingle matchSingle : liveSocreServices.getMatchByDate(date)) {
			matchSingle.setCourt(courtServices.findById(matchSingle.getCourt().getId(), Court.class));
			matchSingle.setReferee(refereeServices.findById(matchSingle.getReferee().getId(), Referee.class));
			matchSingle.setPlayer(playerServices.findById(matchSingle.getPlayer().getId(), Player.class));
			matchSingle.setPlayer2(playerServices.findById(matchSingle.getPlayer2().getId(), Player.class));

			//System.out.println(matchSingle);
			
			matchSingles.add(matchSingle);
		}
		
		System.out.println(matchSingles);
		return matchSingles;
	}

}

//package rest;
//
//import javax.ejb.EJB;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import domain.Jeu;
//import domain.Match;
//import domain.MatchSingle;
//import domain.Player;
//import domain.Point;
//import domain.SetMatch;
//import enumeration.PointType;
//import services.implementes.LiveScoreServices;
//import services.implementes.basic.ServicesBasic;
//
//@Path("score")
//@Stateless
//@LocalBean
//public class CreateScoreWs {
//
//	@EJB
//	ServicesBasic<Match> matchServices;
//
//	@EJB
//	ServicesBasic<SetMatch> setServices;
//
//	@EJB
//	ServicesBasic<Jeu> jeuServices;
//
//	@EJB
//	ServicesBasic<Point> pointServices;
//
//	@EJB
//	LiveScoreServices liveSocreServices;
//
//	@GET
//	@Path("{match}/{player}/{set}/{jeu}/{point}/{type}")
//	@Produces(value = MediaType.TEXT_PLAIN)
//	public String addPoint(@PathParam("match") Integer match_id, @PathParam("player") Integer player_id,
//			@PathParam("set") Integer set_id, @PathParam("jeu") Integer jeu_id, @PathParam("point") String point_value,
//			@PathParam("type") String point_type) {
//
//		MatchSingle match = (MatchSingle) matchServices.findById(match_id, Match.class);
//		if (match == null)
//			return "not found match";
//		else {
//			Player player = (player_id == 1) ? match.getPlayer() : match.getPlayer2();
//			set_id = match_id * 10 + set_id;
//			jeu_id = set_id * 100 + jeu_id;
//			SetMatch setMatch = setServices.findById(set_id, SetMatch.class);
//			if (setMatch == null)
//	//			liveSocreServices.addSetToMatch(new SetMatch(set_id, null), match_id);
//
//	//		Jeu jeu = jeuServices.findById(jeu_id, Jeu.class);
//	//s		if (jeu == null)
//	//			liveSocreServices.addJeuToSet(new Jeu(jeu_id, null), set_id);
//
//	//		liveSocreServices.addPointToJeu(new Point(point_value, PointType.valueOf(point_type)), jeu_id,
//	//				player.getId());
//
//			return "add point to " + player.getFullName();
//		}
//
//	}
//}

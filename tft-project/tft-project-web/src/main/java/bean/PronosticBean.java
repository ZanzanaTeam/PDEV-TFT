package bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Court;
import domain.MatchSingle;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@SessionScoped
public class PronosticBean {
	@EJB
	ServicesBasicLocal<MatchSingle> proxy;
	MatchSingle match;


	public List<MatchSingle> faceAFace() {
		return 	match.getPlayer().getAllMatchSingles().stream().filter(m -> (m.getPlayer().getId().equals(match.getPlayer2().getId())||m.getPlayer2().getId().equals(match.getPlayer2().getId()))).collect(Collectors.toList());
	}
	public MatchSingle getMatch() {
		System.out.println("-----------------*****************");
		return match;
	}

	public void setMatch(MatchSingle match) {
		this.match = match;
	}

	public String match(MatchSingle match) {
		Court court=new Court();
		court.setName("court");
		match.setCourt(court);
		match.getPlayer().setMatchSingles1(
				proxy.findBy2(MatchSingle.class, "player.id", String.valueOf(match.getPlayer().getId())));
		match.getPlayer().setMatchSingles2(
				proxy.findBy2(MatchSingle.class, "player2.id", String.valueOf(match.getPlayer().getId())));
		match.getPlayer2().setMatchSingles1(
				proxy.findBy2(MatchSingle.class, "player.id", String.valueOf(match.getPlayer2().getId())));
		match.getPlayer2().setMatchSingles2(
				proxy.findBy2(MatchSingle.class, "player2.id", String.valueOf(match.getPlayer2().getId())));
		
		this.match = match;
		return "pronostic?faces-redirect=true";
	}

}

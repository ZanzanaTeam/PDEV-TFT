package bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.MatchSingle;
import enumeration.Surface;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@SessionScoped
public class PronosticBean {
	@EJB
	ServicesBasicLocal<MatchSingle> proxy;
	MatchSingle match;

	int selectedRadio1;
	int selectedRadiof;
	int selectedRadio2;

	public int getSelectedRadio1() {
		return selectedRadio1;
	}

	public void setSelectedRadio1(int selectedRadio1) {
		this.selectedRadio1 = selectedRadio1;
	}

	public int getSelectedRadiof() {
		return selectedRadiof;
	}

	public void setSelectedRadiof(int selectedRadiof) {
		this.selectedRadiof = selectedRadiof;
	}

	public int getSelectedRadio2() {
		return selectedRadio2;
	}

	public void setSelectedRadio2(int selectedRadio2) {
		this.selectedRadio2 = selectedRadio2;
	}

	public MatchSingle getMatch() {

		return match;
	}

	public List<MatchSingle> getPlayerMatches(int numPlayer) {
		List<Surface> surfaces = Arrays.asList(Surface.CARPET, Surface.CLAY, Surface.GRASS, Surface.HARD);
		if (numPlayer == 1) {
			if (selectedRadio1 != (-1)) {
				return match.getPlayer().getAllMatchSingles().stream()
						.filter(m -> m.getCourt().getSurface() == surfaces.get(selectedRadio1))
						.collect(Collectors.toList());
			}

			return match.getPlayer().getAllMatchSingles();
		}

		if (numPlayer == 2) {
			if (selectedRadio2 != (-1)) {
				return match.getPlayer2().getAllMatchSingles().stream()
						.filter(m -> m.getCourt().getSurface() == surfaces.get(selectedRadio2))
						.collect(Collectors.toList());
			}

			return match.getPlayer2().getAllMatchSingles();
		}
		if (numPlayer == 0) {
			if (selectedRadiof != (-1)) {
				return match.getPlayer().getAllMatchSingles().stream()
						.filter(m -> (m.getPlayer().getId().equals(match.getPlayer2().getId())
								|| m.getPlayer2().getId().equals(match.getPlayer2().getId())))
						.filter(m -> m.getCourt().getSurface() == surfaces.get(selectedRadiof))
						.collect(Collectors.toList());
			}

			return match.getPlayer().getAllMatchSingles().stream()
					.filter(m -> (m.getPlayer().getId().equals(match.getPlayer2().getId())
							|| m.getPlayer2().getId().equals(match.getPlayer2().getId())))
					.collect(Collectors.toList());
		}
		return null;

	}

	public String match(MatchSingle match) {

		match.getPlayer().setMatchSingles1(
				proxy.findBy2(MatchSingle.class, "player.id", String.valueOf(match.getPlayer().getId())));
		match.getPlayer().setMatchSingles2(
				proxy.findBy2(MatchSingle.class, "player2.id", String.valueOf(match.getPlayer().getId())));
		match.getPlayer2().setMatchSingles1(
				proxy.findBy2(MatchSingle.class, "player.id", String.valueOf(match.getPlayer2().getId())));
		match.getPlayer2().setMatchSingles2(
				proxy.findBy2(MatchSingle.class, "player2.id", String.valueOf(match.getPlayer2().getId())));

		this.match = match;
		selectedRadio1 = -1;
		selectedRadiof = -1;
		selectedRadio2 = -1;
		return "pronostic?faces-redirect=true";
	}

}

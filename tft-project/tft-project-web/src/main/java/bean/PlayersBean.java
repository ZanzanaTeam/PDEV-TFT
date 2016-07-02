package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import domain.MatchSingle;
import domain.Player;
import enumeration.PointType;
import services.interfaces.PlayerServicesRemote;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@ApplicationScoped
public class PlayersBean {

	@EJB
	ServicesBasicLocal<Player> proxy;
	@EJB
	PlayerServicesRemote playerProxy;

	private List<Player> players;
	private List<MatchSingle> matchs = new ArrayList<>();
	private Player player;

	Integer id;
	private Map<PointType, Object> skills = new HashMap<PointType, Object>();
	private Integer id2;
	private Player player2;
	private Map<PointType, Object> skills2;

	public List<Player> getPlayers() {
		try {
			Map<String, Object> criteria = new HashMap<String, Object>();
			Map<String, String> orderBy = new HashMap<String, String>();

			criteria.put("gender", "0");
			orderBy.put("classement", "ASC");
			orderBy.put("age", "ASC");
			orderBy.put("fullName", "ASC");
			players = proxy.find(Player.class, criteria, orderBy, 10, 0);
			// players = proxy.findAll(Player.class);
		} catch (Exception e) {
			players = new ArrayList<Player>();
		}
		return players;
	}

	public Player getPlayer2() {
		try {
			id2 = getId2();
			player2 = proxy.findById(id2, Player.class);

		} catch (Exception e) {
			System.out.println("Id introuvable");
		}
		return player2;
	}

	public List<Player> getOtherPlayers() {
		List<Player> otherPlayers;
		try {
			otherPlayers = getPlayers();
			player = getPlayer();
			otherPlayers.remove(player);

		} catch (Exception e) {
			otherPlayers = new ArrayList<Player>();
		}
		return otherPlayers;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getPlayer() {
		try {
			id = getId();
			player = proxy.findById(id, Player.class);

		} catch (Exception e) {
			System.out.println("Id introuvable");
		}
		return player;
	}

	private Integer getId() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = Integer.valueOf(params.get("id"));
		return id;
	}

	private Integer getId2() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id2 = Integer.valueOf(params.get("id2"));
		return id2;
	}

	public List<MatchSingle> getComparedMatchs() {
		List<MatchSingle> faceToFace = new ArrayList<MatchSingle>();
		try {

			faceToFace = playerProxy.findFaceToFace(player,player2, 0, 0);

			for (MatchSingle matchSingle : faceToFace) {
				System.out.println("Matchs1 "+ matchSingle.getPlayer().getId() + " VS "+ matchSingle.getPlayer2().getId() );
			}
			
		} catch (Exception e) {
			System.out.println("match not Found");
		}
		return faceToFace;
	}

	public List<MatchSingle> getMatchs() {
		try {
			matchs = playerProxy.findMatchByPlayer(player, 5, 0);
		} catch (Exception e) {
			System.out.println("match not Found");
		}
		return matchs;
	}

	public void setMatchs(List<MatchSingle> matchs) {
		this.matchs = matchs;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map<PointType, Object> getSkills() {
		player = getPlayer();
		// try {
		System.out.println("Player: " + player.getId());
		skills = playerProxy.findSkills(player, 10, "DESC");
		for (Entry<PointType, Object> row : skills.entrySet()) {
			System.out.println("Skills " + row.getKey() + " = " + row.getValue().toString());
		}
		// } catch (Exception e) {
		//
		// }
		return skills;
	}

	public Map<PointType, Object> getSkills2() {
		player2 = getPlayer2();
		// try {
		System.out.println("Player: " + player.getId());
		skills2 = playerProxy.findSkills(player2, 10, "DESC");
		for (Entry<PointType, Object> row : skills2.entrySet()) {
			System.out.println("Skills " + row.getKey() + " = " + row.getValue().toString());
		}
		// } catch (Exception e) {
		//
		// }
		return skills2;
	}

	public void setSkills(Map<PointType, Object> skills) {
		this.skills = skills;
	}

	public void setSkills2(Map<PointType, Object> skills2) {
		this.skills2 = skills2;
	}

}

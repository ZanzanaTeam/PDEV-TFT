package services.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import domain.MatchSingle;
import domain.Player;
import enumeration.PointType;

@Remote
public interface PlayerServicesRemote {

	List<Player> findPlayerByWord(String word);

	List<MatchSingle> findMatchByPlayer(Player player, Integer limit, Integer offset);

	Map<PointType, Object> findSkills(Player player, Integer limit, String order);
	

	List<MatchSingle> findFaceToFace(Player player1,Player player2, Integer limit, Integer offset);
}

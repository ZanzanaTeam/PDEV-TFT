package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Player;

@Remote
public interface PlayerServicesRemote {
	
	List<Player> findPlayerByWord(String word);
}

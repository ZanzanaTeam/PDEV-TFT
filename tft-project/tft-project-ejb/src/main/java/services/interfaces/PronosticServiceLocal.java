package services.interfaces;

import javax.ejb.Local;

import domain.MatchSingle;
import domain.Player;
import enumeration.Surface;

@Local
public interface PronosticServiceLocal {
	public float pronostic(MatchSingle match);

	float[] lieuDuTournoi(Player player1, Player player2, String country);

	float lieuDuTournoi(Player player, String country);

	float[] surface(Player player1, Player player2, Surface surface);

	float surface(Player player, Surface surface);

	float[] classement(Player player1, Player player2);

	float classement(Player player);

	float[] derniersResultats(Player player1, Player player2);

	float max(float[] tab);

	int derniersResultats(Player player);

	float[] derniersFaceAFace(Player player1, Player player2);

}

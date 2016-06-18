package services.interfaces;

import javax.ejb.Remote;

import domain.MatchSingle;

@Remote
public interface PronosticServiceRemote {
	public float pronostic(MatchSingle match);
}

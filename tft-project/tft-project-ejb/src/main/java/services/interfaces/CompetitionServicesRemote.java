package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Competition;
@Remote
public interface CompetitionServicesRemote {
public List<Competition>findCompetitionByWord(String word); 
}

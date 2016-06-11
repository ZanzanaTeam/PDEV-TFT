package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.Competition;
@Local
public interface CompetitionServicesLocal {
public List<Competition>findCompetitionByWord(String word); 
}

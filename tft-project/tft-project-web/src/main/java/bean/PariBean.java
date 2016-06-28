package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Competition;
import domain.MatchSingle;
import domain.Player;
import services.interfaces.PronosticServiceLocal;

@ManagedBean
@SessionScoped
public class PariBean {
	@EJB
	PronosticServiceLocal proxy;
float amount;

	int i ;
	float j ;
	private Competition competition;
	private Map<MatchSingle, Integer> bets;
	float money;

	@PostConstruct
	public void init() {
i=0;j=0;amount=1;money=0;
		bets = new HashMap<MatchSingle, Integer>();
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String showCompetetion(Competition competition) {
		this.competition=competition;

		return "pari?faces-redirect=true";
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public List<MatchSingle> getAllMatchs() {
		List<MatchSingle> list = new ArrayList<>();

		competition.getSeasons()
				.forEach(s -> s.getTours().forEach(t -> t.getMatchs().forEach(m -> list.add((MatchSingle) m))));
		return list;
	}

	public float cote(MatchSingle m) {
		Random random=new Random();
		if(m.getPlayer().getClassement()==0)m.getPlayer().setClassement(random.nextInt(105));
		if(m.getPlayer2().getClassement()==0)m.getPlayer2().setClassement(random.nextInt(105));
		if (i == 0) {
			i++;
			j = proxy.pronostic(m);
			return j + 1;
		}
		i--;
		return 2 - j;

	}

	public String bet(MatchSingle key, int value) {
		if(bets.containsKey(key))removeBet(key, bets.get(key));
		if(value==1)amount*= (1+ proxy.pronostic(key));
		else amount*= (2 - proxy.pronostic(key));
		bets.put(key, value);

		return null;
	}

	public String removeBet(MatchSingle key, Integer value) {
		if(value==1)amount/= (1+ proxy.pronostic(key));
		else amount/= (2 - proxy.pronostic(key));
		bets.remove(key, value);
		return null;
	}

	public Map<MatchSingle, Integer> getBets() {
		return bets;
	}

	public void setBets(Map<MatchSingle, Integer> bets) {
		this.bets = bets;
	}

}

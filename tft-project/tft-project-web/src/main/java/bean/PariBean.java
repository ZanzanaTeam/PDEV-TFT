package bean;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.MatchSingle;
import services.interfaces.PronosticServiceLocal;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@SessionScoped
public class PariBean {
	@EJB
	PronosticServiceLocal proxy;
	@EJB
	ServicesBasicLocal<MatchSingle> matchProxy;
	float amount;

	int i;
	float j;
	private int competitionId;
	List<MatchSingle> list;

	public int getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}

	private Map<MatchSingle, Integer> bets;
	float money;

	@PostConstruct
	public void init() {
		i = 0;
		j = 0;
		amount = 1;
		money = 0;
		bets = new HashMap<MatchSingle, Integer>();
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getAmount() {
		return formatFloat(amount);
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String showCompetetion(int competitionId) {
		this.competitionId = competitionId;
		amount = 1;
		return "pari?faces-redirect=true";
	}

	public List<MatchSingle> getAllMatchs() {
		if (list == null)
			list = matchProxy.findBy2(MatchSingle.class, "tour.season.competition.id", String.valueOf(competitionId));

		return list;
	}

	public float formatFloat(float res) {
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		// formatter.setRoundingMode(RoundingMode.DOWN);
		Float formatedFloat = new Float(formatter.format(res));
		return formatedFloat;
	}

	public float cote(MatchSingle m) {
		float proba;
		if (i == 0) {
			i++;
			j = proxy.pronostic(m);
			proba = j;
		} else {
			i--;
			proba = 1 - j;
		}

		return formatFloat(1 - (float) Math.log(proba));

	}

	public void bet(MatchSingle key, int value) {
		if (bets.containsKey(key))
			removeBet(key, bets.get(key));
		if (value == 1)
			amount *= formatFloat(1 - (float) Math.log(proxy.pronostic(key)));
		else
			amount *= formatFloat(1 - (float) Math.log(1 - proxy.pronostic(key)));
		bets.put(key, value);
	}

	public void removeBet(MatchSingle key, Integer value) {
		if (value == 1)
			amount /= formatFloat(1 - (float) Math.log(proxy.pronostic(key)));
		else
			amount /= formatFloat(1 - (float) Math.log(1 - proxy.pronostic(key)));
		bets.remove(key, value);
	}

	public Map<MatchSingle, Integer> getBets() {
		return bets;
	}

	public void setBets(Map<MatchSingle, Integer> bets) {
		this.bets = bets;
	}

}

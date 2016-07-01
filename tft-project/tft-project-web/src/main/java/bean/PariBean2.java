package bean;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Combination;
import domain.Line;
import domain.MatchSingle;
import services.interfaces.PronosticServiceLocal;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@SessionScoped
public class PariBean2 {
	@EJB
	PronosticServiceLocal proxy;
	@EJB
	ServicesBasicLocal<MatchSingle> matchProxy;
	@EJB
	ServicesBasicLocal<Combination> combinationProxy;
	@EJB
	ServicesBasicLocal<Line> lineProxy;
	float amount;
	String codeLoader;
	String codeSaver;
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

	private Combination combination;
	float money;

	@PostConstruct
	public void init() {
		i = 0;
		j = 0;
		amount = 1;
		money = 0;
		combination = new Combination();
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
		combination = new Combination();
		return "newPari?faces-redirect=true";
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
		if (combination.containsKey(key))
			removeBet(key, combination.getValue(key));
		if (value == 1)
			amount *= formatFloat(1 - (float) Math.log(proxy.pronostic(key)));
		else
			amount *= formatFloat(1 - (float) Math.log(1 - proxy.pronostic(key)));
		combination.getLines().add(new Line(key, value));
	}

	public void removeBet(MatchSingle key, Integer value) {
		if (value == 1)
			amount /= formatFloat(1 - (float) Math.log(proxy.pronostic(key)));
		else
			amount /= formatFloat(1 - (float) Math.log(1 - proxy.pronostic(key)));
		combination.remove(key);
	}

	public Combination getCombination() {
		return combination;
	}

	public void setCombination(Combination combination) {
		this.combination = combination;
	}

	public String randomString() {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}

	public String getCodeLoader() {
		return codeLoader;
	}

	public void setCodeLoader(String codeLoader) {
		this.codeLoader = codeLoader;
	}

	public String getCodeSaver() {
		return codeSaver;
	}

	public void setCodeSaver(String codeSaver) {
		this.codeSaver = codeSaver;
	}

	public void saveCombination() {
		combination.setIdentifier(randomString());
		codeSaver = combination.getIdentifier();
		combinationProxy.add(combination);
		combination.setId(((List<Combination>) combinationProxy.findBy(Combination.class, "identifier", codeSaver))
				.get(0).getId());
		System.out.println(combination.getId());
		for (Line l : combination.getLines()) {
			l.setCombination(combination);
			System.out.println(l.getMatch().getId());
			System.out.println(l.getGame());

			lineProxy.add(l);
		}
	}

	public void loadCombination() {
		
		System.out.println(codeLoader);
		
		setCombination(
				((List<Combination>) combinationProxy.findBy(Combination.class, "identifier", codeLoader)).get(0));

	}

}

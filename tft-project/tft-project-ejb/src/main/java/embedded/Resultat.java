package embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Resultat {

	private Integer resultat1;
	private Integer resultat2;
	
	public Resultat() {
		// TODO Auto-generated constructor stub
	}

	public Resultat(Integer resultat1, Integer resultat2) {
		super();
		this.resultat1 = resultat1;
		this.resultat2 = resultat2;
	}

	public Integer getResultat1() {
		return resultat1;
	}

	public void setResultat1(Integer resultat1) {
		this.resultat1 = resultat1;
	}

	public Integer getResultat2() {
		return resultat2;
	}

	public void setResultat2(Integer resultat2) {
		this.resultat2 = resultat2;
	}

	@Override
	public String toString() {
		return "Resultat [resultat1=" + resultat1 + ", resultat2=" + resultat2 + "]";
	}
}
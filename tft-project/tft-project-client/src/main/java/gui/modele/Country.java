package gui.modele;

import java.util.ArrayList;
import java.util.List;

public class Country {
	private String name;
	private String code;
	
	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Country(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public static List<Country> getCountry(){
		return new ArrayList<Country>(){{
			add(new Country("All Pays", "0"));
			add(new Country("Tunisia", "TUN"));
		}};	
	}
}

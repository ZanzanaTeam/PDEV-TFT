package main.test;

import java.util.List;

import javax.naming.NamingException;

import delegate.ServicesBasicDelegate;
import domain.Competition;
import domain.Court;

public class CrudTest2 {
	public List<Competition> me() {
		return new ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
	}

	public static void main(String[] args) throws NamingException {

		// int id;
		// String name = "";
		// Date startDate = new Date();
		// Date endDate = new Date();
		// String country = "";
		// String site = "";
		// for (int i = 0; i < 10; i++) {
		//
		// id = i;
		// name = name + "a";
		// country = country + "b";
		// site = site + "c";
		//
		// Competition competition = new Competition(name, startDate, endDate,
		// country, site,
		// CompetitionLevel.International, null);
		//
		// new ServicesBasicDelegate<Competition>().doCrud().add(competition);
		// }
		// List<Competition> competitions = new
		// ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
		// for (Competition competition : competitions) {
		// System.out.println(competition.getName());
		// }
		/////////////////////////////////////////////////////////////////////////// :
		// String name = "";
		// String address= "";
		// double latitude= 0;
		// double longitude= 0;
		//
		//
		// for (int i = 0; i < 10; i++) {
		//
		//
		// name = name + "a";
		// address = address + "b";
		// latitude = latitude +1;
		// longitude = longitude + 1;
		//
		// Court court = new Court(name, address, latitude, longitude);
		//
		// new ServicesBasicDelegate<Court>().doCrud().add(court);
		// }
		// List<Competition> competitions = new
		// ServicesBasicDelegate<Competition>().doCrud().findAll(Competition.class);
		// for (Competition competition : competitions) {
		// System.out.println(competition.getName());
		// }
		List<Court> courts = (new ServicesBasicDelegate<Court>().doCrud().findBy(Court.class, "name", "aaaaa"));
		for (Court court : courts) {
			System.out.println(court.getName());
		}
	}

}

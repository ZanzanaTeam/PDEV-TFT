package main.test;
import javax.naming.NamingException;

import delegate.ServicesBasicDelegate;
import domain.Club;
import domain.Player;
import enumeration.AgeRange;
import enumeration.Gender;

public class PlayerTest {

	public static void main(String[] args) throws NamingException {
		Player player = new Player("jendoubi med aymen", Gender.Male, 26, AgeRange.Adulte);
		player.setClub(new Club("club1","tunis","adresse"));
		
		new ServicesBasicDelegate<Player>().doCrud()
				.add(player);	

	}

}

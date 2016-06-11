package main.test;
import javax.naming.NamingException;

import delegate.ServicesBasicDelegate;
import domain.Player;
import enumeration.AgeRange;
import enumeration.Gender;

public class PlayerTest {

	public static void main(String[] args) throws NamingException {
		Player player = new Player("MedAymen", Gender.Male, 26, AgeRange.Adulte);
		
		new ServicesBasicDelegate<Player>().doCrud()
				.add(player);	

	}

}

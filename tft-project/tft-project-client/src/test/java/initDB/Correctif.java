package initDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;

import delegate.ServicesBasicDelegate;
import domain.Player;
import domain.Point;
import enumeration.Gender;
import services.interfaces.basic.ServicesBasicLocal;

public class Correctif {
	@EJB
	static ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();
	@EJB
	static ServicesBasicLocal<Point> pointProxy;
	private static Random random;

	public static void main(String[] args) {
		List<Player> players = playerProxy.doCrud().findAll(Player.class);
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Player player : players) {

			player.setAge(random.nextInt((30 - 20)) + 10);
			player.setCountry("Tunisia");
			player.setGender(Gender.Male);
			player.setHeight((float) 1.8);
			player.setWeight((float) 75.8);

			try {
				player.setBirthDate(sdf.parse(random.nextInt(2000 - 1970) + 1970 + "-" + random.nextInt(11) + 1 + "-"
						+ random.nextInt(29) + 1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player.setBirthPlace("Tunis");
			playerProxy.doCrud().update(player);
		}

	}

}

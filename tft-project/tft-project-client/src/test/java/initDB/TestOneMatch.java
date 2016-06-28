package initDB;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import delegate.ServicesBasicDelegate;
import domain.MatchSingle;
import domain.Player;
import services.implementes.basic.PronosticService;
import services.interfaces.PronosticServiceRemote;

public class TestOneMatch {

	public static void main(String[] args) throws NamingException {
		PronosticService pro = new PronosticService();
		ServicesBasicDelegate<MatchSingle> matchProxy = new ServicesBasicDelegate<>();

		MatchSingle match = matchProxy.doCrud().findById(1, MatchSingle.class);

		match.setSets(Arrays.asList(match.getSets().get(0), match.getSets().get(1)));
		System.out.println("score player 1 " + match.getScore(match.getPlayer()));
		System.out.println("score player 2 " + match.getScore(match.getPlayer2()));
		System.out.println("winner id      " + match.getWinner().getId());
		System.out.println("sets size      " + match.getSets().size());

		System.out.println("--------------------------------");
//		List<MatchSingle> list = matchProxy.doCrud().findBy2(MatchSingle.class, "player.id",
//				String.valueOf(match.getPlayer().getId()));
//		list.forEach(m -> System.out.println(m.getPlayer().getId() + " " + m.getPlayer2().getId()));
	/*Classement*/	
		Player player = match.getPlayer();
		Player player2 = match.getPlayer2();
		player.setClassement(0);
		player2.setClassement(3);
		System.out.println(pro.classement(player) + " " + pro.classement(player2));
		/*derniers résultats*/	
		player.setMatchSingles1(matchProxy.doCrud().findBy2(MatchSingle.class,"player.id", String.valueOf(player.getId())));
		player.setMatchSingles2(matchProxy.doCrud().findBy2(MatchSingle.class,"player2.id", String.valueOf(player.getId())));
		player2.setMatchSingles1(matchProxy.doCrud().findBy2(MatchSingle.class,"player.id", String.valueOf(player2.getId())));
		player2.setMatchSingles2(matchProxy.doCrud().findBy2(MatchSingle.class,"player2.id", String.valueOf(player2.getId())));
System.out.println(player.getAllMatchSingles().size());
				System.out.println(pro.derniersResultats(player) + " " + pro.derniersResultats(player2));
				float res[] =pro.derniersFaceAFace(player, player2);
				System.out.println(res[0]+" "+res[1]);
				
				
	}
}

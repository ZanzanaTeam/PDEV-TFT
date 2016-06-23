package initDB;

import java.util.Arrays;
import java.util.List;

import delegate.ServicesBasicDelegate;
import domain.Jeu;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.SetMatch;
import domain.Tour;

public class TestOneMatch {
	
	public static void main(String[] args) {
//		ServicesBasicDelegate<Tour> tourProxy = new ServicesBasicDelegate<>();
		ServicesBasicDelegate<MatchSingle> matchProxy = new ServicesBasicDelegate<>();
//		ServicesBasicDelegate<SetMatch> setProxy = new ServicesBasicDelegate<>();
//		ServicesBasicDelegate<Jeu> jeuProxy = new ServicesBasicDelegate<>();
//		ServicesBasicDelegate<Point> pointProxy = new ServicesBasicDelegate<>();
//		ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();
		MatchSingle match=matchProxy.doCrud().findById(1, MatchSingle.class);
//		match.getSets().forEach(s->s.getJeus().forEach(j->j.getPoints().forEach(p->{System.out.println(p.getPlayer());})));
//		match.setSets(setProxy.doCrud().findBy(SetMatch.class,"match.id" ,String.valueOf(match.getId())));
//		match.getSets().stream().forEach(s->s.setJeus(jeuProxy.doCrud().findBy(Jeu.class,"set.id", String.valueOf(s.getId()))));
//		match.getSets().stream().map(s->s.getJeus()).forEach(action);
		match.setSets(Arrays.asList(match.getSets().get(0),match.getSets().get(1)));
		System.out.println("score player 1 "+match.getScore(match.getPlayer()));
		System.out.println("score player 2 "+match.getScore(match.getPlayer2()));
		System.out.println("winner id      "+match.getWinner().getId());
		System.out.println("sets size      "+match.getSets().size());
		
		System.out.println("--------------------------------");
//		for (SetMatch set : match.getSets()) {
//			System.out.println("set   "+set.getId());
//			System.out.println("nbjeu "+set.getJeus().size());
//			
//		for (Jeu jeu : set.getJeus()) {
//			System.out.println("jeu id   "+jeu.getId());
//			System.out.println("nb point "+jeu.getPoints().size());
//			for (Point point : jeu.getPoints()) {
//				System.out.println("point   "+point.getId());
//			}
//		}
//		}
	}
}

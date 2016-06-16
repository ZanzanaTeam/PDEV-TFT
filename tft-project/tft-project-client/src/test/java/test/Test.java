package test;

import delegate.ServicesBasicDelegate;
import domain.Jeu;
import domain.Match;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.SetMatch;

public class Test {

	public static void main(String[] args) {

		MatchSingle match =(MatchSingle) new ServicesBasicDelegate<Match>().doCrud().findById(1, Match.class);
		
		Point point = new Point(1, "30", null);
		/*Jeu jeu = new Jeu(1, null);
		SetMatch set = new SetMatch(3, null);
		set.setMatch(match);
		jeu.setSet(set);
		point.setJeu(jeu);*/
		
		System.out.println(point);
		
		new ServicesBasicDelegate<Point>().doCrud().add(point);

	}
}

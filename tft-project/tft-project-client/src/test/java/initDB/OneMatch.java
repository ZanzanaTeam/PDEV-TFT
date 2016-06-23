package initDB;

import delegate.ServicesBasicDelegate;
import domain.Jeu;
import domain.MatchSingle;
import domain.Player;
import domain.Point;
import domain.SetMatch;
import domain.Tour;

public class OneMatch {

	public static void main(String[] args) {
		ServicesBasicDelegate<Tour> tourProxy = new ServicesBasicDelegate<>();
		ServicesBasicDelegate<MatchSingle> matchProxy = new ServicesBasicDelegate<>();
		ServicesBasicDelegate<SetMatch> setProxy = new ServicesBasicDelegate<>();
		ServicesBasicDelegate<Jeu> jeuProxy = new ServicesBasicDelegate<>();
		ServicesBasicDelegate<Point> pointProxy = new ServicesBasicDelegate<>();
		ServicesBasicDelegate<Player> playerProxy = new ServicesBasicDelegate<>();
MatchSingle match=new MatchSingle();
match.setTour(tourProxy.doCrud().findById(1, Tour.class));
match.setPlayer(playerProxy.doCrud().findById(1, Player.class));
match.setPlayer2(playerProxy.doCrud().findById(2, Player.class));

matchProxy.doCrud().add(match);
match=matchProxy.doCrud().findById(1,MatchSingle.class);
for (int i =1; i < 3; i++) {
	SetMatch set= new SetMatch();
	set.setId(10+i);
	set.setMatch(match);
	setProxy.doCrud().add(set);
	set=setProxy.doCrud().findById(10+i, SetMatch.class);
	for (int j = 1; j < 14; j++) {
		Jeu jeu=new Jeu();
		jeu.setSet(set);
		jeu.setId(i*100+j);
		jeuProxy.doCrud().add(jeu);
		jeu=jeuProxy.doCrud().findById(i*100+j, Jeu.class);
		for (int k = 1; k < 8; k++) {
			Point point=new Point();
			point.setJeu(jeu);
			point.setId(i*1000+j*10+k);
			point.setPlayer((k%2==1)?
					match.getPlayer():match.getPlayer2());
			pointProxy.doCrud().add(point);
			
		}
	}
}

	}

}

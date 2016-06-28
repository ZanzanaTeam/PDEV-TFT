package bean.websocket;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import domain.MatchSingle;
import services.interfaces.LiveScoreServicesRemote;
import services.interfaces.basic.ServicesBasicLocal;

@ApplicationScoped
public class LiveScoreSessionHandler {
	private final HashSet<Session> sessions = new HashSet<Session>();
	private List<MatchSingle> matchSingles = new ArrayList<MatchSingle>();

	@EJB
	LiveScoreServicesRemote liveScoreServicesLocal;

	@EJB
	ServicesBasicLocal<MatchSingle> servicesBasicLocal;

	public void addSession(Session session) {
		sessions.add(session);
		matchSingles = getMatchLive();
		for (MatchSingle matchSingle : matchSingles) {
			JsonObject addMessage = createAddMessage(matchSingle);
			sendToSession(session, addMessage);
		}
	}

	public void removeSession(Session session) {
		sessions.remove(session);
	}

	public List<MatchSingle> getMatchLive() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2016-06-18");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(liveScoreServicesLocal.getMatchByDate(date));

		return liveScoreServicesLocal.getMatchByDate(date);
	}

	private MatchSingle getDeviceById(int id) {
		return servicesBasicLocal.findById(id, MatchSingle.class);
	}

	private JsonObject createAddMessage(MatchSingle device) {
		JsonProvider provider = JsonProvider.provider();
		JsonObject addMessage = provider.createObjectBuilder().add("action", "add")
				.add("id", device.getId())
				.add("player1", device.getPlayer().getFullName())
				.add("player2", device.getPlayer2().getFullName())
				.add("score", device.getLiveScore())
				.add("tour", device.getTour().getTitle())
				.add("competition", device.getCompetition().getName())
				.add("court", device.getCourt().getName())
				.add("courtId" , device.getCourt().getId())
				.build();
		return addMessage;
	}

	private void sendToAllConnectedSessions(JsonObject message) {
		for (Session session : sessions) {
			sendToSession(session, message);
		}
	}

	private void sendToSession(Session session, JsonObject message) {
		try {
			session.getBasicRemote().sendText(message.toString());
		} catch (IOException ex) {
			sessions.remove(session);
			Logger.getLogger(LiveScoreSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void updateLiveScore(int id, String liveScore) {
		
		liveScoreServicesLocal.updateLiveScoreOfMatch(id, liveScore);
		
		
	}
	
	public void refresh(){
		JsonProvider provider = JsonProvider.provider();
		for (MatchSingle matchSingle : getMatchLive()) {
			JsonObject addMessage = provider.createObjectBuilder().add("action", "refresh")
					.add("id", matchSingle.getId())
					.add("player1", matchSingle.getPlayer().getFullName())
					.add("player2", matchSingle.getPlayer2().getFullName())
					.add("score", matchSingle.getLiveScore())
					.add("tour", matchSingle.getTour().getTitle())
					.add("competition", matchSingle.getCompetition().getName())
					.add("court", matchSingle.getCourt().getName()).build();
			sendToAllConnectedSessions(addMessage);
		}
	}
}

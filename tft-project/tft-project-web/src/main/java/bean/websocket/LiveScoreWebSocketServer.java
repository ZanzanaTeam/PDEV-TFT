package bean.websocket;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint(value = "/actions")
public class LiveScoreWebSocketServer {

	@Inject
	private LiveScoreSessionHandler sessionHandler;

	@OnOpen
	public void open(Session session) {
		sessionHandler.addSession(session);
	}

	@OnClose
	public void close(Session session) {
		sessionHandler.removeSession(session);
	}

	@OnError
	public void onError(Throwable error) {
		Logger.getLogger(LiveScoreWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();

			if ("updateLiveScore".equals(jsonMessage.getString("action"))) {
				String liveScore = jsonMessage.getString("liveScore");
				//System.out.println(liveScore + " => " + jsonMessage.getString("id"));
				int id = Integer.parseInt(jsonMessage.getString("id"));
				sessionHandler.updateLiveScore(id, liveScore);
				sessionHandler.refresh();
			}
		}
	}
	
	public static LiveScoreSessionHandler getSession(){
		return null;
	}
}
package utility;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class InitDB {

	public static void main(String[] args) {

		List<String> PointType = Arrays.asList("ACE", "FOREHAND", "BACKHAND", "FAULT", "DOUBLE_FAULT", "SLICE");

		Client client = ClientBuilder.newClient();

		Random rand;
		int match_id = 1;
		int set_id = 1;
		int jeu_id = 1;
		for (int i = 0; i < 300; i++) {
			if (i % 5 == 0)
				jeu_id++;
			if (jeu_id % 12 == 0) {
				jeu_id = 1;
				set_id++;
			}
			if (set_id > 3) {
				match_id = 2;
				set_id = 1;
				jeu_id = 1;
			}
			rand = new Random();
			int n = rand.nextInt(5) + 1;

			String query = "score/" + match_id + "/" + (i % 2) + "/" + set_id + "/" + jeu_id + "/15/"
					+ PointType.get(n);

			WebTarget target = client.target("http://localhost:18080/tft-project-web/tft/");
			WebTarget score = target.path(query);

			Response response = score.request().get();

			System.out.println(response.readEntity(String.class));
			response.close();

		}

	}

}

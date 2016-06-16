package jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import gui.modele.PlayerRank;
import gui.modele.enumeration.StatusRanks;

public class TestJsoup {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://fr.tennistemple.com/classement-atp").get();
		Elements elements = doc.select(".ranking_line");
		
		List<PlayerRank> playerRanks = new ArrayList<PlayerRank>();
		for (Element element : elements) {
			String result = element.text();
			String[] arrayResult = result.split(" ");

			PlayerRank playerRank = new PlayerRank();
			playerRank.setName(getName(arrayResult));
			arrayResult = getResultInfo(arrayResult);

			if (arrayResult.length == 5) {
				playerRank.setRank(Integer.parseInt(arrayResult[0].replace(".", "").trim()));
				playerRank.setAge(Integer.parseInt(arrayResult[1]));
				playerRank.setWinPoint(arrayResult[3]);
				playerRank.setPoint(Integer.parseInt(arrayResult[4]));
				
			} else {
				playerRank.setCountPlace(Integer.parseInt(arrayResult[0]));
				playerRank.setRank(Integer.parseInt(arrayResult[1].replace(".", "").trim()));
				playerRank.setAge(Integer.parseInt(arrayResult[2]));
				playerRank.setWinPoint(arrayResult[4]);
				playerRank.setPoint(Integer.parseInt(arrayResult[5]));
			}
			playerRank.setStatus(StatusRanks.CONSTANT);
			Element e = element.select("div div").first();
			if(e.attr("style").contains("up"))playerRank.setStatus(StatusRanks.UP);
			if(e.attr("style").contains("down"))playerRank.setStatus(StatusRanks.DOWN);

			playerRanks.add(playerRank);
		}
		System.out.println(playerRanks);
	}

	private static String getName(String[] result) {
		String name = "";
		for (int i = 0; i < result.length - 2; i++) {
			try {
				Integer.parseInt(result[i].replace(".", ""));
			} catch (Exception ee) {
				name += result[i];
			}
		}
		return name;
	}

	private static String[] getResultInfo(String[] result) {
		String resultArray = "";
		for (int i = 0; i < result.length; i++) {
			if (i < result.length - 2) {
				try {
					Integer.parseInt(result[i].replace(".", ""));
					resultArray += result[i].replace(".", "") + " ";
				} catch (Exception ee) {
				}
			} else {
				resultArray += result[i] + " ";
			}
		}
		return resultArray.split(" ");
	}
}

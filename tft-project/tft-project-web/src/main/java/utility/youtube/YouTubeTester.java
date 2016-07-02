package utility.youtube;

import java.util.List;

public class YouTubeTester {
	 
    public static void main(String[] args) throws Exception {
  
        String clientID = "454081177324-uta9c83rv9rj2q2k15cv3g2llspuoi1d.apps.googleusercontent.com";
        String textQuery = "tft-project";
        int maxResults = 10;
        boolean filter = true;
        int timeout = 2000;
  
        YouTubeManager ym = new YouTubeManager(clientID);
  
        List<YouTubeVideo> videos = ym.retrieveVideos(textQuery, maxResults, filter, timeout);
  
        for (YouTubeVideo youtubeVideo : videos) {
            System.out.println(youtubeVideo.getWebPlayerUrl());
            System.out.println("Thumbnails");
            for (String thumbnail : youtubeVideo.getThumbnails()) {
                System.out.println("\t" + thumbnail);
            }
            System.out.println(youtubeVideo.getEmbeddedWebPlayerUrl());
            System.out.println("************************************");
        }
  
    }

}

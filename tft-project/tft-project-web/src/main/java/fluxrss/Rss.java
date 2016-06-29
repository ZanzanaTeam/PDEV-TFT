package fluxrss;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import domain.News;

@XmlRootElement(name = "rss")
public class Rss {
	private Channel channel;
	private String xmlns;
	private String version;

	public Rss() {
		channel = new Channel();
		channel.setTitle("Federationtunisiennedetennis");
		channel.setDescription("");
		channel.setLanguage("en-US");
		channel.setLink("www.tft.tn");
		channel.setPubDate(new Date());
		setVersion("2.0");
		setXmlns("xmlns:atom=http://www.w3.org/2005/Atom");

	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@XmlAttribute
	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	@XmlAttribute
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}

@XmlRootElement(name = "channel")
class Channel {

	private String title;
	private String link;
	private String description;
	private String language;
	private Date pubDate;
	private List<News> news;

	public Channel() {
		// TODO Auto-generated constructor stub
	}

	public Channel(String title, String link, String description, String language, Date pubDate, List<News> news) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.pubDate = pubDate;
		this.news = news;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	@XmlElement(name = "item")
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}

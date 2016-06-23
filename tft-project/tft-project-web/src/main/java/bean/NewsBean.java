package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import domain.News;
import enumeration.ArticleStatus;
import services.interfaces.NewsServiceLocal;

@ManagedBean
@Stateless
public class NewsBean {

	private String title;
	private String body;
	private Date publishDate;
	private ArticleStatus articleStatus;
	private String thumbnail;
	private String summary;

	@EJB
	private NewsServiceLocal newsService;

	List<News> news;

	public NewsBean() {
	}

	@PostConstruct
	public void init() {
		Date date = new Date();
		System.out.println("Today's date is: " + date.toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		System.out.println("Today's date is: " + dateFormat.format(date));
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String strdate2 = "23-06-2016 11:35:42";
		try {
			Date newdate = dateformat2.parse(strdate2);
			System.out.println(newdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		news = new ArrayList<News>();
		try {
			news.add(new News(1, "Nadal is dead", "No worries, Djokovic killed him",
					date = dateformat2.parse(strdate2)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			news.add(new News(1, "Nadal is dead", "No worries, Djokovic killed him",
					date = dateformat2.parse(strdate2)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			news.add(new News(1, "Nadal is dead", "No worries, Djokovic killed him",
					date = dateformat2.parse(strdate2)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			news.add(new News(1, "Nadal is dead", "No worries, Djokovic killed him",
					date = dateformat2.parse(strdate2)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	// public String addNews() {
	// News news = (News) FacesContext.getCurrentInstance()
	// .getExternalContext().getSessionMap().get("News");
	//
	// return "";
	//
	// }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public ArticleStatus getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(ArticleStatus articleStatus) {
		this.articleStatus = articleStatus;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}

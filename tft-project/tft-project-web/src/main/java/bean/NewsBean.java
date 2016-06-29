package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import domain.News;
import enumeration.ArticleStatus;
import services.interfaces.NewsServiceLocal;
import services.interfaces.basic.ServicesBasicLocal;

@ManagedBean
@ApplicationScoped
public class NewsBean {

	private int id;
	private String title;
	private String body;
	private Date publishDate;
	private ArticleStatus articleStatus;
	private String thumbnail;
	private String summary;
	private int idSelected;

	@EJB
	private NewsServiceLocal newsService;
	@EJB
	private ServicesBasicLocal<News> servicesBasicLocal;

	List<News> news;
	private News singleNews;

	public NewsBean() {
	}

	public String NewsById(int id) {
		// idSelected = singleNews.getId();
		setId(id);
		singleNews = servicesBasicLocal.findById(id, News.class);
		return "single_news?faces-redirect=true";
	}

	public int getIdSelected() {
		return idSelected;
	}

	public void setIdSelected(int idSelected) {
		this.idSelected = idSelected;
	}

	public List<News> getNews() {
		return servicesBasicLocal.findAll(News.class);
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public News getSingleNews() {
		return singleNews;
	}

	public void setSingleNews(News singleNews) {
		this.singleNews = singleNews;
	}

}

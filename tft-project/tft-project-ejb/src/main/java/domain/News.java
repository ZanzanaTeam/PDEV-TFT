package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import enumeration.ArticleStatus;

@Entity
public class News implements Serializable {

	/**
	 * Author Samih
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String body;
	private Date publishDate;
	private ArticleStatus articleStatus;
	private String thumbnail;
	private String summary;

	public News() {
		// TODO Auto-generated constructor stub
	}

	public News(int id, String title, String body, Date publishDate) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.publishDate = publishDate;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(columnDefinition = "text")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", body=" + body + ", publishDate=" + publishDate
				+ ", articleStatus=" + articleStatus + ", thumbnail=" + thumbnail + ", summary=" + summary + "]";
	}

}

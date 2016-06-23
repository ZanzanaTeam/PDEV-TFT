package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.News;

@Remote
public interface NewsServiceRemote {
	List<News> findNewsById(int id);
	List<News> findNewsByTitle(String title);
}

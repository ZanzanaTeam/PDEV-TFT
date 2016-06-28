package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.News;

@Local
public interface NewsServiceLocal {
	News findSingleNewsById(int id);

	List<News> findNewsById(int id);

	List<News> findNewsByTitle(String title);

}
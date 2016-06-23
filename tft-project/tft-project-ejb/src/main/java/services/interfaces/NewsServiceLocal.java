package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.News;

@Local
public interface NewsServiceLocal {
	List<News> findNewsByCategory();
}
package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.News;
import services.interfaces.basic.ICrud;


@Local
public interface INewsService extends ICrud<News>{
	Boolean add(News t);
	Boolean update(News t);
	Boolean delete(News t);
	News findById(Integer id);
	List<News> findAll();
}
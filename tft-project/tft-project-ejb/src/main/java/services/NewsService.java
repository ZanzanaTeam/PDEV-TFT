package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.News;
import services.interfaces.INewsService;


@Stateless
public class NewsService implements INewsService{
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	public News findByTitle(String title){
		try {
			return entityManager.find(News.class, title);
		} catch (Exception e) {
			return null;
		} 

	}

	@Override
	public Boolean delete(Integer id, Class<News> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News findById(Integer id, Class<News> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> findAll(Class<News> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> findBy(Class<News> type, String param, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean add(News t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(News t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(News t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


	
}

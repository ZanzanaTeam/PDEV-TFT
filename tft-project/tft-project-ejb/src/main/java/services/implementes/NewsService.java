package services.implementes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.News;
import services.interfaces.NewsServiceLocal;
import services.interfaces.NewsServiceRemote;

@Stateless
public class NewsService implements NewsServiceLocal , NewsServiceRemote {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<News> findNewsByCategory() {
		// TODO Auto-generated method stub
		return null;
	}
}

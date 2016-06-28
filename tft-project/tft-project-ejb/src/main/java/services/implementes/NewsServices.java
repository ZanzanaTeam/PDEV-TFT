package services.implementes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.News;
import services.interfaces.NewsServiceLocal;
import services.interfaces.NewsServiceRemote;

@Stateless
public class NewsServices implements NewsServiceLocal, NewsServiceRemote {

	@PersistenceContext
	EntityManager entityManager;

	public List<News> findNewsById(int id) {
		List<News> lists = null;
		try {
			String jpql = "select e from News e where e.id like :id";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("id", "%" + id + "%");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findNewsById");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;

	}

	public List<News> findNewsByTitle(String title) {
		List<News> lists = null;
		try {
			String jpql = "select e from News e where e.title like :title";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("title", "%" + title + "%");
			lists = query.getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findNewsByTitle");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;

	}

	@Override
	public News findSingleNewsById(int id) {
		return entityManager.find(News.class, id);
	}

}

package services.implementes.basic;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.basic.ServicesBasicLocal;
import services.interfaces.basic.ServicesBasicRemote;

/**
 * @author MedAymen
 * 
 */

@Stateless
@LocalBean
public class ServicesBasic<T> implements ServicesBasicRemote<T>, ServicesBasicLocal<T> {

	@PersistenceContext
	EntityManager entityManager;

	public ServicesBasic() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean update(T t) {
		try {
			entityManager.merge(t);
			return true;
		} catch (Exception ee) {
			return false;
		}
	}

	@Override
	public Boolean delete(Integer id, Class<T> type) {
		try {
			entityManager.remove(entityManager.find(type, id));
			return true;
		} catch (Exception ee) {
			return false;
		}
	}

	@Override
	public T findById(Integer id, Class<T> type) {
		return entityManager.find(type, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> type) {
		List<T> lists = null;
		try {
			String jpql = "select e from " + type.getSimpleName() + " e";

			lists = entityManager.createQuery(jpql).getResultList();

		} catch (Exception ee) {
			System.out.println("Catch findAll");
			System.out.println(ee.getMessage());
			System.out.println(ee.getStackTrace());
		}
		return lists;
	}

	@Override
	public Boolean add(T t) {
		try {

			entityManager.merge(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBy(Class<T> type, String param, String value) {
		List<T> list = null;
		String jpql = "select c from " + type.getSimpleName() + " c where c." + param + " LIKE '%" + value + "%'";

		try {
			list = entityManager.createQuery(jpql).getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("bad credentials!!!");
		}

		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	
	public List<T> findBy2(Class<T> type, String param, String value) {
		List<T> list = null;
		String jpql = "select c from " + type.getSimpleName() + " c where c." + param + " = " + value ;

		try {
			list = entityManager.createQuery(jpql).getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("bad credentials!!!");
		}

		return list;
	}
}

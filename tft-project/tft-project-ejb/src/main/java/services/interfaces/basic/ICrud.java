package services.interfaces.basic;

import java.util.List;

/**
 * @author MedAymen
 * 
 */

public interface ICrud<T> {

	Boolean add(T t);

	Boolean update(T t);

	Boolean delete(Integer id, Class<T> type);

	T findById(Integer id, Class<T> type);

	List<T> findAll(Class<T> type);
		
	List<T> findBy(Class<T> type, String param,String value);
	List<T> findBy2(Class<T> type, String param,String value);

}

package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.Point;

@Local
public interface PointServicesLocal {
	List<Point> findAllPointsByMatch(Integer idMatch);
}

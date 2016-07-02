package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Point;

@Remote
public interface PointServicesRemote {
	List<Point> findAllPointsByMatch(Integer idMatch); 
}

package initDB;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;

import delegate.ServicesBasicDelegate;
import domain.Point;
import enumeration.PointType;

public class CorrectifPoint {

	@EJB
	static ServicesBasicDelegate<Point> pointProxy= new ServicesBasicDelegate<>();

	public static void main(String[] args) {

		List<Point> points = pointProxy.doCrud().findAll(Point.class);

		Random random = new Random();
		for (Point point : points) {

			point.setPointType(PointType.values()[random.nextInt(PointType.values().length)]);
			pointProxy.doCrud().update(point);
		}
	}

}

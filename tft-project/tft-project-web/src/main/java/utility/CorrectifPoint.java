package utility;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;

import domain.Point;
import enumeration.PointType;
import services.interfaces.basic.ServicesBasicLocal;

public class CorrectifPoint {

	@EJB
	static ServicesBasicLocal<Point> pointProxy;

	public static void main(String[] args) {

		List<Point> points = pointProxy.findAll(Point.class);

		Random random = new Random();
		for (Point point : points) {

			point.setPointType(PointType.values()[random.nextInt(PointType.values().length)]);
			pointProxy.update(point);
		}
	}

}

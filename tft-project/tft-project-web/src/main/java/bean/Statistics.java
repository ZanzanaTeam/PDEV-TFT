package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import domain.MatchSingle;
import domain.Point;
import domain.SetMatch;
import enumeration.PointType;
import services.implementes.PointServices;
import services.interfaces.PointServicesLocal;
import services.interfaces.SetServicesLocal;
import services.interfaces.basic.ServicesBasicLocal;

/**
 * 
 * @author Rihab
 * 
 *
 */
@ManagedBean

public class Statistics {
	static String SET = "SET ";
	private BarChartModel barModel;

	@EJB
	PointServicesLocal pointServices = new PointServices();
	@EJB
	ServicesBasicLocal<MatchSingle> servicesBasicMatch;
	@EJB
	SetServicesLocal setServices;

	@EJB
	ServicesBasicLocal<SetMatch> servicesBasicSets;

	List<SetMatch> setsMatch;
	String playerOne = null;
	String playerTwo = null;
	MatchSingle match = null;

	List<Point> listPointPlayer1 = new ArrayList<>();
	List<Point> listPointPlayer2 = new ArrayList<>();

	Map<PointType, Integer> listPointPlayer1Regroupe;
	Map<PointType, Integer> listPointPlayer2Regroupe;

	String setNumber;
	int i = 0;

	public String getSetNumber() {

		return SET + (++i) + "";
	}

	public String getPlayerOne() {
		return playerOne;
	}

	public String getPlayerTwo() {
		return playerTwo;
	}

	public List<Point> getListPointPlayer1() {
		return listPointPlayer1;
	}

	public List<Point> getListPointPlayer2() {
		return listPointPlayer2;
	}

	@PostConstruct
	public String init(Integer id) {

		createBarModels(id);
		return "matchDetails.jsf?faces-redirect=true";

	}

	public BarChartModel getBarModel() {

		return barModel;
	}

	/**
	 * @return BarChartModel
	 * @param
	 */
	private BarChartModel initBarModel(Integer idMatch) {

		BarChartModel model = new BarChartModel();

		ChartSeries playertwoChart = new ChartSeries();

		ChartSeries playerOneChart = new ChartSeries();

		List<Point> points = pointServices.findAllPointsByMatch(idMatch);
		System.out.println(points.size());
		match = servicesBasicMatch.findById(idMatch, MatchSingle.class);

		if (match instanceof MatchSingle) {
			playerOne = ((MatchSingle) match).getPlayer().getFullName();
			playerTwo = ((MatchSingle) match).getPlayer2().getFullName();
		}

		playerOneChart.setLabel(playerOne);

		playertwoChart.setLabel(playerTwo);

		for (Point point : points) {

			if (point.getPlayer().getFullName().equals(playerOne)) {

				listPointPlayer1.add(point);

			} else if ((point.getPlayer().getFullName().equals(playerTwo))) {

				listPointPlayer2.add(point);
			}
		}

		listPointPlayer1Regroupe = CountPointByType(listPointPlayer1);
		listPointPlayer2Regroupe = CountPointByType(listPointPlayer2);
		for (Entry<PointType, Integer> entry : listPointPlayer1Regroupe.entrySet()) {

			playerOneChart.set(entry.getKey(), entry.getValue().intValue());
		}
		for (Entry<PointType, Integer> entry : listPointPlayer2Regroupe.entrySet()) {

			playertwoChart.set(entry.getKey(), entry.getValue().intValue());
		}

		model.addSeries(playerOneChart);
		model.addSeries(playertwoChart);

		return model;
	}

	public MatchSingle getMatch() {
		System.out.println("msets match " + match.getSets().size());
		match.getSets().forEach(s -> {
			System.out.println(s.getScore(match.getPlayer()));
			System.out.println(s.getScore(match.getPlayer2()));
		});

		return match;
	}

	private Map<PointType, Integer> CountPointByType(List<Point> listPointPlayer1) {
		Map<PointType, Integer> mapPoint = new HashMap<>();

		Integer countAce = 0;
		Integer countBACKHAND = 0;
		Integer countDOUBLE_FAULT = 0;
		Integer countFAULT = 0;
		Integer countFIRST_SERVE = 0;
		Integer countFOREHAND = 0;
		Integer countSLICE = 0;

		for (Point point : listPointPlayer1) {
			if (point != null) {
				switch (point.getPointType()) {
				case ACE:
					countAce++;
					break;
				case BACKHAND:
					countBACKHAND++;
					break;

				case DOUBLE_FAULT:
					countDOUBLE_FAULT++;
					break;
				case FAULT:
					countFAULT++;
					break;
				case FIRST_SERVE:
					countFIRST_SERVE++;
					break;

				case FOREHAND:
					countFOREHAND++;
					break;

				case SLICE:
					countSLICE++;
					break;

				default:
					break;
				}
			}
		}

		mapPoint.put(PointType.ACE, countAce);
		mapPoint.put(PointType.BACKHAND, countBACKHAND);

		mapPoint.put(PointType.DOUBLE_FAULT, countDOUBLE_FAULT);
		mapPoint.put(PointType.FAULT, countFAULT);
		mapPoint.put(PointType.FIRST_SERVE, countFIRST_SERVE);

		mapPoint.put(PointType.FOREHAND, countFOREHAND);

		mapPoint.put(PointType.SLICE, countSLICE);

		System.out.println("MAp :: " + mapPoint.toString());
		return mapPoint;
	}

	private void createBarModels(Integer id) {
		createBarModel(id);

	}

	/**
	 * @return Void
	 */
	private void createBarModel(Integer idMatch) {

		barModel = initBarModel(idMatch);

		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");
		barModel.setShowDatatip(true);
		barModel.setShowPointLabels(false);
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Type de points ");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Nombre des points ");
		yAxis.setMin(0);
		Integer key = Collections.max(listPointPlayer2Regroupe.entrySet(), Map.Entry.comparingByValue()).getValue();
		yAxis.setMax(key + 10);

	}

	public List<SetMatch> getSetsMatch() {

		// List<SetMatch> lists= servicesBasicMatch.findById(1,
		// Match.class).getSets();
		// List<SetMatch> lists= servicesBasicSets.findBy(SetMatch.class,
		// "match_id", "1");
		List<SetMatch> lists = setServices.findAllSets(1);
		System.out.println(lists.size());
		return lists;
	}

	public void listpointbySet(List<Point> points) {
		int i = 0;
		Map<SetMatch, Integer> mapPointBySet = new HashMap<>();
		for (Point point : points) {
			for (SetMatch set : setsMatch) {
				if (point.getJeu().getSet().equals(set)) {
					mapPointBySet.put(set, i++);
				}

				List<SetMatch> pointBySet = null;

				pointBySet.add(point.getJeu().getSet());
			}
		}
	}
}

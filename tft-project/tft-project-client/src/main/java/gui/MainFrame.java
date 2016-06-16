package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import gui.javafx.ClubController;
import gui.javafx.CompetitionController;
import gui.javafx.CourtController;
import gui.javafx.LiveScore;
import gui.javafx.PlayerController;
import gui.javafx.PlayerRanksController;
import gui.javafx.RefereeController;
import gui.javafx.TicketController;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainFrame extends JRibbonFrame {
	private static final long serialVersionUID = 1L;
	// Ribbon1;
	private JRibbonBand band1;
	private JRibbonBand band2;
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(800, 600);
		setVisible(true);
		setExtendedState(MAXIMIZED_BOTH);
		band1 = new JRibbonBand("", null);
		band2 = new JRibbonBand("Player", getResizableIconFromResource("icon/player.png"));
		
		initButton();

		band1.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band1.getControlPanel()),
				new IconRibbonBandResizePolicy(band1.getControlPanel())));
		band2.setResizePolicies((List) Arrays.asList(new CoreRibbonResizePolicies.None(band1.getControlPanel()),
				new IconRibbonBandResizePolicy(band1.getControlPanel())));

		RibbonTask task1 = new RibbonTask("Gestion TFT", band1);
		RibbonTask task2 = new RibbonTask("Player", band2);
		
		getRibbon().addTask(task1);
		getRibbon().addTask(task2);

		setApplicationIcon(getResizableIconFromResource("icon/ball2.png"));
		getRibbon().configureHelp(getResizableIconFromResource("icon/ball2.png"), null);
		getRibbon().setApplicationMenu(new RibbonApplicationMenu());
	}

	private void initButton() {
		// Creation des Bouttons
		JCommandButton player_btn = new JCommandButton("Player", getResizableIconFromResource("icon/player.png"));
		JCommandButton club_btn = new JCommandButton("Club", getResizableIconFromResource("icon/match.png"));
		JCommandButton match_btn = new JCommandButton("Match", getResizableIconFromResource("icon/stade.png"));

		JCommandButton court_btn = new JCommandButton("Satde", getResizableIconFromResource("icon/stade.png"));
		JCommandButton competition_btn = new JCommandButton("Competition",
				getResizableIconFromResource("icon/match.png"));
		JCommandButton referee_btn = new JCommandButton("Referee", getResizableIconFromResource("icon/referee.png"));
		JCommandButton ticket_btn = new JCommandButton("Ticket", getResizableIconFromResource("icon/ticket.png"));

		// Ajouter les boutton dans le ribbon1
		band1.addCommandButton(player_btn, RibbonElementPriority.TOP);
		band1.addCommandButton(club_btn, RibbonElementPriority.TOP);
		band1.addCommandButton(match_btn, RibbonElementPriority.TOP);
		band1.addCommandButton(court_btn, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(competition_btn, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(referee_btn, RibbonElementPriority.TOP);
		band1.addCommandButton(ticket_btn, RibbonElementPriority.TOP);

		// Action pour les bouttons
		player_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createContainer(PlayerController.class, "player");
			}
		});

		club_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createContainer(ClubController.class, "club");
			}
		});

		match_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// createContainer(nomdu controleur .class, "Nom fichier xml");
			}

		});
		referee_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createContainer(RefereeController.class, "Referee");
			}

		});

		competition_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createContainer(CompetitionController.class, "competition");
			}
		});

		ticket_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createContainer(TicketController.class, "ticket");
			}
		});
		
		court_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createContainer(CourtController.class, "court");
			}
		});
		
		JCommandButton playerRankInternational_btn = new JCommandButton("Rankings International", getResizableIconFromResource("icon/ranks.png"));
		JCommandButton playerRankNational_btn = new JCommandButton("Rankings National", getResizableIconFromResource("icon/ranks.png"));
		JCommandButton liveScore_btn = new JCommandButton("Live Score", getResizableIconFromResource("icon/ranks.png"));

		band2.addCommandButton(playerRankInternational_btn, RibbonElementPriority.TOP);
		band2.addCommandButton(playerRankNational_btn, RibbonElementPriority.TOP);
		band2.addCommandButton(liveScore_btn, RibbonElementPriority.TOP);

		playerRankInternational_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createContainer(PlayerRanksController.class, "playerRanks");
			}
		});
		
		liveScore_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createContainer(LiveScore.class, "liveScore");

			}
		});
		
	}

	private JFXPanel fxPanel;

	public void createContainer(Class t, String nameFileFXML) {
		if (fxPanel != null)
			fxPanel.setVisible(false);
		fxPanel = new JFXPanel();
		Scene scene = createScene(t, nameFileFXML);
		fxPanel.setScene(scene);

		getContentPane().add(fxPanel, BorderLayout.CENTER);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	private Scene createScene(Class t, String nameFileFXML) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(t.getResource(nameFileFXML + ".fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResizableIcon getResizableIconFromResource(String resource) {

		return ImageWrapperResizableIcon.getIcon(MainFrame.class.getClassLoader().getResource(resource),
				new Dimension(128, 128));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
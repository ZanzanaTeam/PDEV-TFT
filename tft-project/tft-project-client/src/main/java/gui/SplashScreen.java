package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class SplashScreen extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static boolean isRegistered;
	private static JProgressBar progressBar = new JProgressBar();
	private static SplashScreen execute;
	private static int count;
	private static Timer timer1;

	public SplashScreen() {

		Container container = getContentPane();
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new javax.swing.border.EtchedBorder());
		panel.setBackground(new Color(255, 255, 255));
		container.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("icon/logo2.png")));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Fédération Tunisienne de Tennis");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		panel_1.add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("© Copyright 2016 Esprit");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_1.add(lblNewLabel_2, BorderLayout.EAST);
		progressBar.setForeground(Color.BLACK);

		progressBar.setMaximum(50);
		container.add(progressBar, BorderLayout.SOUTH);
		loadProgressBar();
		setSize(309, 444);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void loadProgressBar() {
		ActionListener al = new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				count++;

				progressBar.setValue(count);
				if (count == 50) {

					createFrame();

					execute.setVisible(false);// swapped this around with
												// timer1.stop()
					timer1.stop();
				}
			}

			private void createFrame() throws HeadlessException {
				new MainFrame().setVisible(true);
			}
		};
		timer1 = new Timer(50, al);
		timer1.start();
	}

	public static void main(String[] args) {
		execute = new SplashScreen();
	}
};
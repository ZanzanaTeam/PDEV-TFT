package utility;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

public class GoogleDrive {

	private static String CLIENT_ID = "737614758904-l4l1k25qt40pv4tjnonadb5u9v6ci249.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "WMAnd1wwstPfgEDqR1M9F3JU";

	private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

	private static Drive service = null;

	public static void getService() throws IOException {
		if (service == null) {
			final HttpTransport httpTransport = new NetHttpTransport();
			final JsonFactory jsonFactory = new JacksonFactory();

			final GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
					Arrays.asList(DriveScopes.DRIVE)).setAccessType("online")
					.setApprovalPrompt("auto").build();

			String url = flow.newAuthorizationUrl()
					.setRedirectUri(REDIRECT_URI).build();
			System.out
					.println("Please open the following URL in your browser then type the authorization code:");
			System.out.println("  " + url);

			final JFrame frame = new JFrame();
			JTextField addresse = new JTextField();
			//addresse.setEnabled(false);
			addresse.setText(url);

			frame.add(addresse, BorderLayout.NORTH);

			JPanel jPanel = new JPanel(new BorderLayout());
			frame.add(jPanel, BorderLayout.CENTER);

			final JTextField accessAdress = new JTextField();
			JButton button = new JButton("Send");

			jPanel.add(accessAdress, BorderLayout.NORTH);
			jPanel.add(button, BorderLayout.EAST);

			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					GoogleTokenResponse response;
					try {
						response = flow.newTokenRequest(accessAdress.getText())
								.setRedirectUri(REDIRECT_URI).execute();
						GoogleCredential credential = new GoogleCredential()
								.setFromTokenResponse(response);

						// Create a new authorized API client
						service = new Drive.Builder(httpTransport, jsonFactory,
								credential).build();
						
						JOptionPane.showMessageDialog(null,
							    "Google Drive configuré",
							    "félicitations",
							    JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,
							    "Erreur Google Drive",
							    "Erreur",
							    JOptionPane.WARNING_MESSAGE);
					}

				}
			});
			
			frame.setSize(400, 100);
			frame.setVisible(true);
			
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public static String uplodeGoogleDrive(String path) throws IOException {

		getService();

		// Insert a file
		File body = new File();
		body.setTitle("TFT-"+new Date().getTime() + "");
		// body.setDescription("A test document");
		body.setMimeType("application/pdf");

		java.io.File fileContent = new java.io.File(path);
		FileContent mediaContent = new FileContent("application/pdf", fileContent);

		File file = service.files().insert(body, mediaContent).execute();
		System.out.println("File ID: " + file.getId());
		return file.getId();

	}

	// id = 0B7GNL_KltwuPRzdJdUd1bzlMMm8

	private static File printFile(String id) {

		try {

			File file = service.files().get(id).execute();

			System.out.println("Title: " + file.getTitle());
			System.out.println("Description: " + file.getDescription());
			System.out.println("MIME type: " + file.getMimeType());
			return file;
		} catch (IOException e) {
			System.out.println("An error occured: " + e);
			return null;
		}
	}

	/**
	 * Download a file's content.
	 * 
	 * @param service
	 *            Drive API service instance.
	 * @param file
	 *            Drive File instance.
	 * @return InputStream containing the file's content if successful,
	 *         {@code null} otherwise.
	 * @throws IOException
	 */
	public static InputStream downloadFile(String id) throws IOException {

		getService();

		File file = printFile(id);

		if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
			try {
				HttpResponse resp = service.getRequestFactory()
						.buildGetRequest(new GenericUrl(file.getDownloadUrl()))
						.execute();
				return resp.getContent();
			} catch (IOException e) {
				// An error occurred.
				e.printStackTrace();
				return null;
			}
		} else {
			// The file doesn't have any content stored on Drive.
			return null;
		}
	}

}

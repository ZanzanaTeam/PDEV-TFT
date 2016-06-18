/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28222277/how-can-i-generate-a-pdf-ua-compatible-pdf-with-itext
 */
package gui.javafx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;

import domain.MatchReport;

/**
 * Creates an accessible PDF with images and text.
 */
@WrapToTest
public class Pdf {

	/** The resulting PDF. */

	public static String DEST = "pdftest.pdf";
	/** An image resource. */
	public static String FOX = "pdf.png";
	/** An image resource. */
	public static String DOG = "pdf.png";
	/** A font that will be embedded. */
	public static String FONT = "FreeSans.ttf";

	/**
	 * Creates an accessible PDF with images and text.
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws IOException
	 * @throws DocumentException
	 * @throws URISyntaxException
	 */
	static public void main(String args[]) throws IOException, DocumentException, URISyntaxException {
		// DEST =
		// MainFrame.class.getClassLoader().getResource("icon/pdf.pdf").toURI().getPath();
		FOX = RefereeController.class.getClassLoader().getResource("icon/pdf.png").toURI().getPath();
		DOG = RefereeController.class.getClassLoader().getResource("icon/pdf.png").toURI().getPath();
		FONT = RefereeController.class.getClassLoader().getResource("icon/FreeSans.ttf").toURI().getPath();
		File file = new File(DEST);
		// file.getParentFile().mkdirs();

		new Pdf().createPdf(DEST,null);
	}

	/**
	 * Creates an accessible PDF with images and text.
	 * 
	 * @param dest
	 *            the path to the resulting PDF
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void createPdf(String dest,MatchReport matchReport) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
		writer.setPdfVersion(PdfWriter.VERSION_1_7);
		// TAGGED PDF
		// Make document tagged
		writer.setTagged();
		// ===============
		// PDF/UA
		// Set document metadata
		writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
		document.addLanguage("en-US");
		document.addTitle("Match Report");
		writer.createXmpMetadata();
		// =====================
		document.open();

		Paragraph p = new Paragraph();
		// PDF/UA
		// Embed font
		Font font = FontFactory.getFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED, 20);
		p.setFont(font);
		// ==================
		Chunk c = new Chunk(" TFT ");
		p.add(c);
		Image i = Image.getInstance(FOX);
		c = new Chunk(i, 0, -24);
		// PDF/UA
		// Set alt text
		c.setAccessibleAttribute(PdfName.ALT, new PdfString("Fox"));
		// ==============
		p.add(c);
		p.add(new Chunk(" Match Report "));
		i = Image.getInstance(DOG);
		c = new Chunk(i, 0, -24);
		// PDF/UA
		// Set alt text
		c.setAccessibleAttribute(PdfName.ALT, new PdfString("Dog"));
		// ==================
		p.add(c);
		document.add(p);

		float[] columnWidths = { 1, 5};
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
		PdfPCell cell = new PdfPCell(new Phrase("Players", f));
		cell.setBackgroundColor(GrayColor.GRAYBLACK);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(5);
		table.addCell(cell);

		table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
		for (int i1 = 0; i1 < 2; i1++) {
			table.addCell("#");
			table.addCell("Player Name");
//			table.addCell("Competition");
//			table.addCell("Court Review");
//			table.addCell("Players Review");
		}
		table.setHeaderRows(3);
		table.setFooterRows(1);
		table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
			table.addCell("1");
			table.addCell(matchReport.getPlayer1().getFullName());
//			table.addCell(matchReport.getCompetition().getName());
//			table.addCell(matchReport.getReviewCourt());
//			table.addCell(matchReport.getReviewPlayers());
			
			table.addCell("2");
			table.addCell(matchReport.getPlayer2().getFullName());

			
			System.out.println("in PDF => "+matchReport.getReviewPlayers());
			
			
		document.add(table);
		
		p = new Paragraph(matchReport.getReviewPlayers(), font);
		document.add(p);
		p = new Paragraph("\n\n", font);
		document.add(p);
		p = new Paragraph(matchReport.getReviewCourt(), font);
		document.add(p);
		p = new Paragraph("\n\n", font);
		document.add(p);
		
		document.close();
		
	}

}
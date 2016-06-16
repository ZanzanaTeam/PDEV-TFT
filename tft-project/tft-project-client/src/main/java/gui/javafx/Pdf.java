/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28222277/how-can-i-generate-a-pdf-ua-compatible-pdf-with-itext
 */
package gui.javafx;
 
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;

import gui.MainFrame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
 
/**
 * Creates an accessible PDF with images and text.
 */
@WrapToTest
public class Pdf {
 
    /** The resulting PDF. */
	
    public static String DEST = "pdf.pdf";
    /** An image resource. */
    public static String FOX = "/src/main/resources/icon/pdf.png";
    /** An image resource. */
    public static String DOG = "/src/main/resources/icon/pdf.png";
    /** A font that will be embedded. */
    public static String FONT = "/src/main/resources/icon/FreeSans.ttf";
 
    /**
     * Creates an accessible PDF with images and text.
     * @param args no arguments needed
     * @throws IOException
     * @throws DocumentException 
     * @throws URISyntaxException 
     */
    static public void main(String args[]) throws IOException, DocumentException, URISyntaxException {
    	//DEST = MainFrame.class.getClassLoader().getResource("icon/pdf.pdf").toURI().getPath();
    	FOX = MainFrame.class.getClassLoader().getResource("icon/pdf.png").toURI().getPath();
    	DOG = MainFrame.class.getClassLoader().getResource("icon/pdf.png").toURI().getPath();
    	FONT = MainFrame.class.getClassLoader().getResource("icon/FreeSans.ttf").toURI().getPath();
        File file = new File(DEST);
        //file.getParentFile().mkdirs();

        new Pdf().createPdf(DEST);
    }
 
    /**
     * Creates an accessible PDF with images and text.
     * @param dest  the path to the resulting PDF
     * @throws IOException
     * @throws DocumentException 
     */
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        //TAGGED PDF
        //Make document tagged
        writer.setTagged();
        //===============
        //PDF/UA
        //Set document metadata
        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        document.addLanguage("en-US");
        document.addTitle("English pangram");
        writer.createXmpMetadata();
        //=====================
        document.open();
 
        Paragraph p = new Paragraph();
        //PDF/UA
        //Embed font
        Font font = FontFactory.getFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED, 20);
        p.setFont(font);
        //==================
        Chunk c = new Chunk("The quick brown ");
        p.add(c);
        Image i = Image.getInstance(FOX);
        c = new Chunk(i, 0, -24);
        //PDF/UA
        //Set alt text
        c.setAccessibleAttribute(PdfName.ALT, new PdfString("Fox"));
        //==============
        p.add(c);
        p.add(new Chunk(" Sami7 9adri "));
        i = Image.getInstance(DOG);
        c = new Chunk(i, 0, -24);
        //PDF/UA
        //Set alt text
        c.setAccessibleAttribute(PdfName.ALT, new PdfString("Dog"));
        //==================
        p.add(c);
        document.add(p);
 
        p = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n", font);
        document.add(p);
        List list = new List(true);
        list.add(new ListItem("quick", font));
        list.add(new ListItem("brown", font));
        list.add(new ListItem("fox", font));
        list.add(new ListItem("jumps", font));
        list.add(new ListItem("over", font));
        list.add(new ListItem("the", font));
        list.add(new ListItem("lazy", font));
        list.add(new ListItem("dog", font));
        document.add(list);
        document.close();
    }
 
}
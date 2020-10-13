package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bll.FilmManager;
import bo.Film;
import bo.Seance;
import event.PDFEvent;

/**
 * Servlet implementation class ServletGenererPDF
 */
@WebServlet("/genererPDF")
public class ServletGenererPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGenererPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		FilmManager fm = context.getBean("fm", FilmManager.class);
		
		List<Film> films = fm.selectAll();
		
		// Création de notre objet Document, racine de notre représentation PDF
		Document document = new Document();
		
		//Ouverture d'un flux pour écrire dans le fichier "helloWorld.pdf"
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
        	// On associe le document et le flux précédemment créés
            PdfWriter writer = PdfWriter.getInstance(document, out);
            
            String relativeWebPath = "/images/epsi.png";
            String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
            
            writer.setPageEvent(new PDFEvent(absoluteDiskPath));
            
            // L'écriture du PDF commence à cette ligne :
            // document.open signale que nous commençons à écrire dans notre document
            document.open();
            
            for (int i=0; i<films.size(); i++) {
            	Film film = films.get(i);
            	
            	// Creation d'un titre de chapitre avec une police taille 24
				Font titleFont = new Font(BaseFont.createFont(), 24);
				Paragraph titleText = new Paragraph(film.getNom(), titleFont);
				Chapter chapter = new Chapter(titleText, i+1);
				
				Paragraph description = new Paragraph(film.getDescription());
				Paragraph duree = new Paragraph(String.valueOf(film.getDuree()));
				
				// Creation de mon tableau avec 1 colonne
	            PdfPTable table = new PdfPTable(2);
	            table.setWidthPercentage(30); // Mon tableau prendra 80% de l'ecran
	            table.setWidths(new int[]{1, 1});

	            // Creation des cellules servant d'intitulés de colonnes
	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Salle"));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Heure"));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            for (Seance seance : film.getSeances()) {
	            	// Pour chaque client, on crée 4 cellules, une pour chaque attribut
	                PdfPCell cell;

	                cell = new PdfPCell(new Phrase(String.valueOf(seance.getSalle())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(String.valueOf(seance.getHeure())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);
	            }
            	
				document.add(chapter);
				document.add(description);
				document.add(duree);
				document.add(table);
            }
            
            // Une fois l'écriture terminée, on ferme le document
            document.close();
        } catch (DocumentException ex) {
        	ex.printStackTrace();
        }
        response.getOutputStream().write(out.toByteArray());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}

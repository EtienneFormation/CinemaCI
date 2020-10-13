package event;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFEvent implements PdfPageEvent {
	private String logoPath;
	private Image logo;
	
	public PDFEvent(String logoPath) {
		this.logoPath = logoPath;
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		try {
			logo = Image.getInstance(logoPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Rectangle pageSize = writer.getPageSize();
		int imgWidth = 100;
		int imgHeight = 50;
		
		logo.setAbsolutePosition(pageSize.getWidth() - imgWidth, pageSize.getHeight() - (imgHeight + 20));
        logo.scaleToFit(imgWidth, imgHeight);
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			document.add(logo);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		int pageNumber = writer.getPageNumber();
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		cb.beginText();
		try {
			cb.setFontAndSize(BaseFont.createFont(), 10);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cb.setTextMatrix(writer.getPageSize().getWidth()/2, 10);
		cb.showText(String.valueOf(pageNumber));
		cb.endText();
		cb.restoreState();
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {}

	@Override
	public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChapterEnd(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSection(PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSectionEnd(PdfWriter writer, Document document, float paragraphPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {
		// TODO Auto-generated method stub
		
	}

}

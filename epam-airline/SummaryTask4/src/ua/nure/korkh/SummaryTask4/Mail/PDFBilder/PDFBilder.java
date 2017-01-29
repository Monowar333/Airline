package ua.nure.korkh.SummaryTask4.Mail.PDFBilder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import ua.nure.korkh.SummaryTask4.DAO.DAOFactory;
import ua.nure.korkh.SummaryTask4.DAO.RoleDAO;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.DAOFactoryMySQL;
import ua.nure.korkh.SummaryTask4.bean.BrigadeBean;
import ua.nure.korkh.SummaryTask4.bean.FlightsBean;
import ua.nure.korkh.SummaryTask4.entity.Role;
import ua.nure.korkh.SummaryTask4.functional.GenerateNum;
import ua.nure.korkh.SummaryTask4.functional.MD5Util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Create pdf file
 * 
 * @author Korkh
 * 
 */
public class PDFBilder {

	public File buildPdfDocumentFlight(List<BrigadeBean> bregadeList,
			FlightsBean flight) throws Exception {
		// get data model which is passed by the Spring container
		Document doc = new Document();
		
		File filepdf = new File("Flight" +MD5Util.md5Custom(GenerateNum.getNumber())+".pdf");
		filepdf.createNewFile();
		PdfWriter.getInstance(doc, new FileOutputStream(filepdf));
		doc.open();
		doc.add(new Paragraph("Number Flight: " + flight.getNumber()));
		doc.add(new Paragraph("Departure DAte: " + flight.getDeparturedate()));
		doc.add(new Paragraph("From: " + flight.getFromwhenceCountry() + " "
				+ flight.getFromwhenceCity() + " " + flight.getFromwhenceName()));
		doc.add(new Paragraph("Where: " + flight.getWhereCountry() + " "
				+ flight.getWhereCity() + " " + flight.getWhereName()));
		doc.add(new Paragraph("Aircraft: " + flight.getIdplains()));
		doc.add(new Paragraph("Status Flight: " + flight.getStatus()));

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 2.0f, 2.0f });
		table.setSpacingBefore(10);

		DAOFactory factory = new DAOFactoryMySQL();
		RoleDAO manager = factory.getRoleDAO();
		List<Role> roleList = manager.findAll();
		// define font for table header row
		Font font = FontFactory.getFont("c:\\Windows\\Fonts\\tahoma.ttf",
				"cp1251", true);

		font.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(3);

		// write table header
		cell.setPhrase(new Phrase("¹", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name Surname", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Role", font));
		table.addCell(cell);
		cell.setBackgroundColor(BaseColor.WHITE);
		font.setColor(BaseColor.BLACK);
		// /write table row data
		int i = 0;
		for (BrigadeBean bregade : bregadeList) {
			cell.setPhrase(new Phrase(" " + i++, font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(bregade.getName() + " "
					+ bregade.getSuname(), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(roleList.get(bregade.getRole() - 1)
					.getName(), font));
			table.addCell(cell);
		}

		doc.add(table);
		doc.close();
		return filepdf;

	}
}

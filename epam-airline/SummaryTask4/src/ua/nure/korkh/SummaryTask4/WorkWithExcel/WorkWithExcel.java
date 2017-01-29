package ua.nure.korkh.SummaryTask4.WorkWithExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import ua.nure.korkh.SummaryTask4.DAO.StatusAirport;
import ua.nure.korkh.SummaryTask4.DAO.MySQLDAO.PlaneDAOMySQL;
import ua.nure.korkh.SummaryTask4.Mail.CredentialsBundle;
import ua.nure.korkh.SummaryTask4.entity.Airport;
import ua.nure.korkh.SummaryTask4.exception.DBException;

/**
 * Class parse excel file with airports and add its to DB
 * 
 * @author Korkh
 * 
 */
public class WorkWithExcel {

	private static final Logger LOG = Logger.getLogger(PlaneDAOMySQL.class);

	public List<Airport> FromExelToDBmedications() throws IOException, DBException {

		List<Airport> listAirport = new ArrayList<>();
		InputStream inputStream = null;
		HSSFWorkbook workBook = null;
		String filen = CredentialsBundle.resolveCredentials("fileexel");
		//String filen = ("D:\\airport.xls"); <---- file 
		try {
			inputStream = new FileInputStream(filen);
			workBook = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = workBook.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		while (it.hasNext()) {
			Row row = it.next();
			// Iterator<Cell> cells = row.iterator();
			Airport airport = new Airport();
			airport.setIatacode(row.getCell(0).getStringCellValue());
			airport.setName(row.getCell(1).getStringCellValue());
			airport.setCity(row.getCell(2).getStringCellValue());
			airport.setCountry(row.getCell(3).getStringCellValue());
			airport.setStatus(StatusAirport.OPEN.getName());
			listAirport.add(airport);
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			LOG.error("problem with file", e);
		}
		return listAirport;

	}
}

package ua.nure.korkh.SummaryTask4.DAO;

/**
 * Status enum for Airport Table
 * 
 * @author Korkh
 */
public enum StatusAirport {
	OPEN, CLOSE;

	public String getName() {
		return name().toLowerCase();
	}
}

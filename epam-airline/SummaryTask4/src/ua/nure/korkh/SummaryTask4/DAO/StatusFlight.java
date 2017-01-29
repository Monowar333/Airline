package ua.nure.korkh.SummaryTask4.DAO;

/**
 * Status enum for Flight Table
 * 
 * @author Korkh
 */
public enum StatusFlight {
	NO_BRIGADE, WAITING, CHECK_IN, DEPARTED, DELAYED, ARRIVED;

	public String getName() {
		return name().toLowerCase();
	}
}

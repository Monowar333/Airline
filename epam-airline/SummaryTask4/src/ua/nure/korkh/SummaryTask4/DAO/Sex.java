package ua.nure.korkh.SummaryTask4.DAO;

/**
 * Sex enum for User Table
 * 
 * @author Korkh
 */
public enum Sex {
	MALE, FEMALE;

	public String getName() {
		return name().toLowerCase();
	}
}

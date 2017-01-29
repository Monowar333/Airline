package ua.nure.korkh.SummaryTask4.DAO;

/**
 * Status enum for Claim Table
 * 
 * @author Korkh
 */
public enum StatusClaim {
	NEW, REJECTED, EXECUTED;

	public String getName() {
		return name().toLowerCase();
	}
}

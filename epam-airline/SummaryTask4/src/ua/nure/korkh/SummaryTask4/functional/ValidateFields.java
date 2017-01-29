package ua.nure.korkh.SummaryTask4.functional;

import ua.nure.korkh.SummaryTask4.DAO.Sex;
import ua.nure.korkh.SummaryTask4.DAO.StatusFlight;

public class ValidateFields {
	
	public  static boolean statusFlight(String status) {
		return (status.equals(StatusFlight.ARRIVED.getName()) || 
				status.equals(StatusFlight.CHECK_IN.getName()) 
				|| status.equals(StatusFlight.DELAYED.getName()) 
				|| status.equals(StatusFlight.DEPARTED.getName()) 
				||  status.equals(StatusFlight.WAITING.getName()) 
				|| status.equals(StatusFlight.NO_BRIGADE.getName()));
	}
	
	public  static boolean sexUser(String sex) {
		return (sex.equals(Sex.FEMALE.getName()) || sex.equals(Sex.MALE.getName()));
	}
	
}

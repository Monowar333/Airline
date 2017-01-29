package ua.nure.korkh.SummaryTask4.web.ValidateForms;

public class ErrorMessege {

	private ErrorMessege() {
		// no op
	}

	// users
	public static final String ERR_LOGIN = "logim must be include only A-Z,a-z,1-9";
	//exsist 
	
	public static final String ERR_LOGIN_EXSIST = "this login already exist";
	
	public static final String ERR_EMAIL_EXSIST = "this email already exist";

	public static final String ERR_PASSWORD = "password must be include only A-Z,a-z,1-9";

	public static final String ERR_EMAIL = "invalid email";

	public static final String ERR_NAME = "name must be include only A-Z,a-z,A-Z,a-z";

	public static final String ERR_SURNAME = "surname must be include only A-Z,a-z,A-Z,a-z";

	public static final String ERR_DATE = "Date must have format YYYY-MM-DD";

	public static final String ERR_CHOOSE_SEX = "you must choose sex";

	public static final String ERR_CHOOSE_ROLE = "you must choose role";

	public static final String ERR_TELEPHONE = "telephone must have format +38_________";

	// flight
	public static final String ERR_NUMBER = "enter the number";

	public static final String ERR_AIRPORT = "you must choose airport";

	public static final String ERR_PLAINS = "you must choose Aircrafts";
	
	public static final String ERR_PLAINS_EXSIST = "this Aircraft already a used in this date";

	public static final String ERR_CHOOSE_STATUS_AIRPORT = "you must choose status";
	
	public static final String ERR_AIRPORT_EQUALS = "From and Whence must be Different";

	// BRIGADE
	public static final String ERR_ADD_BRIGADE = "you must choose all brigade, or send claim";

	// Claim
	public static final String ERR_ADMINISTRATOR = "you must choose administrator";

	public static final String ERR_DISCRIPTION = "enter the discription";
	
	//Plane
	public static final String ERR_PLANE_MODEL = "invalid value for model";
	
	public static final String ERR_PLANE_CREW = "crew must be include only numbers";
	
	public static final String ERR_PLANE_NUMBER_OF_SEATS = "number of seats must be include only numbers";

	
}

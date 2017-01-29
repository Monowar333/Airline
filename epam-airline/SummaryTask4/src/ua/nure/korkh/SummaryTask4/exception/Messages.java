package ua.nure.korkh.SummaryTask4.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author Korkh
 * 
 */
public class Messages {

	private Messages() {
		// no op
	}

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String EXCEPTION_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
	
	public static final String EXCEPTION_CANNOT_ROLLBACK = "Cannot close a rollback";

	public static final String EXCEPTION_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String EXCEPTION_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String EXCEPTION_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

	// Airport
	public static final String EXCEPTION_CAN_NOT_FIND_AIRPORT_BY_ID = "Cannot obtain an airport by its id";

	public static final String EXCEPTION_CAN_NOT_FIND_AIRPORT = "Cannot obtain the airports";

	public static final String EXCEPTION_CAN_NOT_INSERT_AIRPORT = "Cannot insert an airport";
	
	public static final String EXCEPTION_CAN_NOT_CHANGE_STATUS_AIRPORT = "Cannot change  airport status";

	// BrigadeBean
	public static final String EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_ID = "Cannot obtain a brigadebean by its id";

	// Brigade
	public static final String EXCEPTION_CAN_NOT_FIND_BRIGADE_BY_ID = "Cannot obtain a brigade by its id";

	public static final String EXCEPTION_CAN_NOT_FIND_BRIGADE = "Cannot obtain a brigade";

	public static final String EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_IDFLIGHT = "Cannot obtain a brigade by its id Flight";

	public static final String EXCEPTION_CAN_NOT_FIND_BRIGADEBEAN_BY_IDUSERS = "Cannot obtain a brigade by its id user";

	public static final String EXCEPTION_CAN_NOT_INSERT_NEW_BRIGADE = "Cannot insert a brigade";

	public static final String EXCEPTION_CAN_NOT_DELETE_DRIGADE = "Cannot delete a brigade";
	
	// ClaimBean
	public static final String EXCEPTION_CAN_NOT_FIND_CLAIMBEAN_BY_ID_DISPATCHER = "Cannot obtain a calimbean by its id dispatcher";

	public static final String EXCEPTION_CAN_NOT_FIND_CLAIMBEAN_BY_ID_ADMINISTRATOR = "Cannot obtain a calimbean by its id administrator";

	// Claim
	public static final String EXCEPTION_CAN_NOT_FIND_CLAIM_BY_ID = "Cannot obtain a calim by its id";

	public static final String EXCEPTION_CAN_NOT_FIND_CLAIMS = "Cannot obtain a calims";

	public static final String EXCEPTION_CAN_NOT_INSERT_CLAIM = "Cannot insert a calim";

	public static final String EXCEPTION_CAN_NOT_CHANGE_STATUS_CLAIM = "The status of the claim cannot be changed";

	// Flight
	public static final String EXCEPTION_CAN_NOT_FIND_FLIGHT_BY_ID = "Cannot obtain a flight by its id";

	public static final String EXCEPTION_CAN_NOT_FIND_FLIGHT = "Cannot obtain the flights";

	public static final String EXCEPTION_CAN_NOT_UPDATE_FLIGHT = "Cannot update a flight";

	public static final String EXCEPTION_CAN_NOT_DELETE_FLIGTS = "Cannot delete a flights";

	public static final String EXCEPTION_CAN_NOT_CHANGE_STATUS_FLIGHT = "Cannot change status a flight";

	// FlightBean
	public static final String EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS = "Cannot obtain a flightbeans";

	public static final String EXCEPTION_CAN_NOT_FIND_FLIGHT_BEANS_BY_COLUM_AND_VALUE = "Cannot obtain a flightbeans by colum name and value";

	public static final String EXCEPTION_CAN_NOT_FIND_FLIGHTS_BEANS_BY_ID = "Cannot obtain a flightbeans by id flight";
	// Plain
	public static final String EXCEPTION_CAN_NOT_FIND_PLAIN_BY_ID = "Cannot obtain a plain by its id";

	public static final String EXCEPTION_CAN_NOT_FIND_PLAINS = "Cannot obtain the plains";
	
	public static final String EXCEPTION_CAN_NOT_INSERT_PLANE = "Cannot insert a plane";
	
	public static final String EXCEPTION_CAN_NOT_UPDATE_PLANE = "Cannot update a plane";

	public static final String EXCEPTION_CAN_NOT_DELETE_PLANE = "Cannot delete a plane";
	
	public static final String EXCEPTION_CAN_NOT_FIND_PLAIN_BY_ID_ROLE = "Cannot obtain a plain by its id and departure date";
	// Role
	public static final String EXCEPTION_CAN_NOT_FIND_ROLE_BY_ID = "Cannot obtain a role by its id";

	public static final String EXCEPTION_CAN_NOT_FIND_ROLES = "Cannot obtain the roles";

	// User
	public static final String EXCEPTION_CAN_NOT_FIND_USER_BY_ID = "Cannot obtain a user by its id";
	
	public static final String EXCEPTION_CAN_NOT_FIND_USER_BY_LOGIN = "Cannot obtain a user by its login";
	
	public static final String EXCEPTION_CAN_NOT_FIND_USER_BY_EMAIL = "Cannot obtain a user by its email";

	
	public static final String EXCEPTION_CAN_NOT_FIND_USER_BY_LINK = "Cannot obtain a user by its link";
	
	public static final String EXCEPTION_CAN_NOT_UPDATE_USER = "Cannot update a user";
	
	public static final String EXCEPTION_CAN_NOT_CHANGE_STATUS = "Cannot change the user account status";

	public static final String EXCEPTION_CAN_NOT_DELETE_USERS = "Cannot delete the user";

	public static final String EXCEPTION_CAN_NOT_FIND_USERS_BY_ID_ROLE = "Cannot obtain the user by its id role";

	;

}
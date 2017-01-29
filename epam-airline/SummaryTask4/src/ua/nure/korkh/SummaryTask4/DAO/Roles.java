package ua.nure.korkh.SummaryTask4.DAO;

import ua.nure.korkh.SummaryTask4.entity.User;

/**
 * Role enum .
 * 
 * @author Korkh
 * 
 */

public enum Roles {
	ADMINISTRATOR, DISPATCHER, NAVIGATOR, PILOT, RADIOMAN, STEWARDESS;

	public static Roles getRole(User user) {
		int roleId = user.getRoleId();
		return Roles.values()[roleId - 1];
	}

	public String getName() {
		return name().toLowerCase();
	}

}

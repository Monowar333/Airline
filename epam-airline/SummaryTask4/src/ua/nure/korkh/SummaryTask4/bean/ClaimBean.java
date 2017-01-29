package ua.nure.korkh.SummaryTask4.bean;

import java.sql.Date;

import ua.nure.korkh.SummaryTask4.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * claim.id|claim.date|claim.descripticlaimn|user.name|user.suname,user.name|user.suname|claim.status|flight.number
 * </pre>
 * 
 * @author Korkh
 * 
 */
public class ClaimBean extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String description;

	private String nameadministrator;

	private String sunameadministrator;

	private String namedispatcher;

	private String sunamedispatcher;

	private String number;

	private String status;

	private Date date;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameadministrator() {
		return nameadministrator;
	}

	public void setNameadministrator(String nameadministrator) {
		this.nameadministrator = nameadministrator;
	}

	public String getSunameadministrator() {
		return sunameadministrator;
	}

	public void setSunameadministrator(String sunameadministrator) {
		this.sunameadministrator = sunameadministrator;
	}

	public String getNamedispatcher() {
		return namedispatcher;
	}

	public void setNamedispatcher(String namedispatcher) {
		this.namedispatcher = namedispatcher;
	}

	public String getSunamedispatcher() {
		return sunamedispatcher;
	}

	public void setSunamedispatcher(String sunamedispatcher) {
		this.sunamedispatcher = sunamedispatcher;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ClaimBean [description=" + description + ", nameadministrator="
				+ nameadministrator + ", sunameadministrator="
				+ sunameadministrator + ", namedispatcher=" + namedispatcher
				+ ", sunamedispatcher=" + sunamedispatcher + ", number="
				+ number + ", status=" + status + ", date=" + date + "]";
	}

}

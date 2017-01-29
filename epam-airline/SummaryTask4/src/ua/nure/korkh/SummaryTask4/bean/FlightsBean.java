package ua.nure.korkh.SummaryTask4.bean;

import java.sql.Date;

import ua.nure.korkh.SummaryTask4.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * flight.id|flight.number|airport.city|airport.country|airport.name|airport.city|airport.country|airport.name|flight.departuredate|plains.model,|flight.status"
 * </pre>
 * 
 * @author Korkh
 * 
 */
public class FlightsBean extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fromwhenceCity;

	private String fromwhenceCountry;

	private String fromwhenceName;

	private String whereCity;

	private String whereCountry;

	private String whereName;

	private Date departuredate;

	private String number;

	private String idplains;

	private String status;

	public String getFromwhenceCity() {
		return fromwhenceCity;
	}

	public void setFromwhenceCity(String fromwhenceCity) {
		this.fromwhenceCity = fromwhenceCity;
	}

	public String getFromwhenceCountry() {
		return fromwhenceCountry;
	}

	public void setFromwhenceCountry(String fromwhenceCountry) {
		this.fromwhenceCountry = fromwhenceCountry;
	}

	public String getFromwhenceName() {
		return fromwhenceName;
	}

	public void setFromwhenceName(String fromwhenceName) {
		this.fromwhenceName = fromwhenceName;
	}

	public String getWhereCity() {
		return whereCity;
	}

	public void setWhereCity(String whereCity) {
		this.whereCity = whereCity;
	}

	public String getWhereCountry() {
		return whereCountry;
	}

	public void setWhereCountry(String whereCountry) {
		this.whereCountry = whereCountry;
	}

	public String getWhereName() {
		return whereName;
	}

	public void setWhereName(String whereName) {
		this.whereName = whereName;
	}

	public Date getDeparturedate() {
		return departuredate;
	}

	public void setDeparturedate(Date departuredate) {
		this.departuredate = departuredate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIdplains() {
		return idplains;
	}

	public void setIdplains(String idplains) {
		this.idplains = idplains;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FlightsBean [fromwhenceCity=" + fromwhenceCity
				+ ", fromwhenceCountry=" + fromwhenceCountry
				+ ", fromwhenceName=" + fromwhenceName + ", whereCity="
				+ whereCity + ", whereCountry=" + whereCountry + ", whereName="
				+ whereName + ", departuredate=" + departuredate + ", number="
				+ number + ", idplains=" + idplains + ", status=" + status
				+ "]";
	}

}

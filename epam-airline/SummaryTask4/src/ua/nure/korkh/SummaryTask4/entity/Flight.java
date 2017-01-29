package ua.nure.korkh.SummaryTask4.entity;

import java.sql.Date;

/**
 * Flight entity.
 * 
 * @author Korkh
 * 
 */
public class Flight extends Entity {

	private static final long serialVersionUID = 1L;

	private Integer fromwhence;

	private Integer where;

	private Date departuredate;

	private String number;

	private Integer idplains;

	private String status;

	
	public Integer getFromwhence() {
		return fromwhence;
	}

	public void setFromwhence(Integer fromwhence) {
		this.fromwhence = fromwhence;
	}

	public Integer getWhere() {
		return where;
	}

	public void setWhere(Integer where) {
		this.where = where;
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

	public Integer getIdplains() {
		return idplains;
	}

	public void setIdplains(Integer idplains) {
		this.idplains = idplains;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departuredate == null) ? 0 : departuredate.hashCode());
		result = prime * result
				+ ((fromwhence == null) ? 0 : fromwhence.hashCode());
		result = prime * result
				+ ((idplains == null) ? 0 : idplains.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (departuredate == null) {
			if (other.departuredate != null)
				return false;
		} else if (!departuredate.equals(other.departuredate))
			return false;
		if (fromwhence == null) {
			if (other.fromwhence != null)
				return false;
		} else if (!fromwhence.equals(other.fromwhence))
			return false;
		if (idplains == null) {
			if (other.idplains != null)
				return false;
		} else if (!idplains.equals(other.idplains))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (where == null) {
			if (other.where != null)
				return false;
		} else if (!where.equals(other.where))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [fromwhence=" + fromwhence + ", where=" + where
				+ ", departuredate=" + departuredate + ", number=" + number
				+ ", idplains=" + idplains + ", status=" + status + "]";
	}

}

package ua.nure.korkh.SummaryTask4.web.ValidateForms;

import ua.nure.korkh.SummaryTask4.entity.Flight;

public class FlightFormValidate extends Flight {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fromwhenceError;

	private String whereError;

	private String departuredateError;

	private String numberError;

	private String idplainsError;

	private String statusError;

	public FlightFormValidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightFormValidate(Flight flight) {
		this.setFromwhence(flight.getFromwhence());
		this.setDeparturedate(flight.getDeparturedate());
		this.setId(flight.getId());
		this.setNumber(flight.getNumber());
		this.setIdplains(flight.getIdplains());
		this.setStatus(flight.getStatus());
		this.setWhere(flight.getWhere());
	}

	public String getFromwhenceError() {
		return fromwhenceError;
	}

	public void setFromwhenceError(String fromwhenceError) {
		this.fromwhenceError = fromwhenceError;
	}

	public String getWhereError() {
		return whereError;
	}

	public void setWhereError(String whereError) {
		this.whereError = whereError;
	}

	public String getDeparturedateError() {
		return departuredateError;
	}

	public void setDeparturedateError(String departuredateError) {
		this.departuredateError = departuredateError;
	}

	public String getNumberError() {
		return numberError;
	}

	public void setNumberError(String numberError) {
		this.numberError = numberError;
	}

	public String getIdplainsError() {
		return idplainsError;
	}

	public void setIdplainsError(String idplainsError) {
		this.idplainsError = idplainsError;
	}

	public String getStatusError() {
		return statusError;
	}

	public void setStatusError(String statusError) {
		this.statusError = statusError;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((departuredateError == null) ? 0 : departuredateError
						.hashCode());
		result = prime * result
				+ ((fromwhenceError == null) ? 0 : fromwhenceError.hashCode());
		result = prime * result
				+ ((idplainsError == null) ? 0 : idplainsError.hashCode());
		result = prime * result
				+ ((numberError == null) ? 0 : numberError.hashCode());
		result = prime * result
				+ ((statusError == null) ? 0 : statusError.hashCode());
		result = prime * result
				+ ((whereError == null) ? 0 : whereError.hashCode());
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
		FlightFormValidate other = (FlightFormValidate) obj;
		if (departuredateError == null) {
			if (other.departuredateError != null)
				return false;
		} else if (!departuredateError.equals(other.departuredateError))
			return false;
		if (fromwhenceError == null) {
			if (other.fromwhenceError != null)
				return false;
		} else if (!fromwhenceError.equals(other.fromwhenceError))
			return false;
		if (idplainsError == null) {
			if (other.idplainsError != null)
				return false;
		} else if (!idplainsError.equals(other.idplainsError))
			return false;
		if (numberError == null) {
			if (other.numberError != null)
				return false;
		} else if (!numberError.equals(other.numberError))
			return false;
		if (statusError == null) {
			if (other.statusError != null)
				return false;
		} else if (!statusError.equals(other.statusError))
			return false;
		if (whereError == null) {
			if (other.whereError != null)
				return false;
		} else if (!whereError.equals(other.whereError))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "FlightFormValidate [fromwhenceError="
				+ fromwhenceError + ", whereError=" + whereError
				+ ", departuredateError=" + departuredateError
				+ ", numberError=" + numberError + ", idplainsError="
				+ idplainsError + ", statusError=" + statusError + "]";
	}

}

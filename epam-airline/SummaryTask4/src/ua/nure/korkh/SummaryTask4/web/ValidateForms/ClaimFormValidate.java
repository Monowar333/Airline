package ua.nure.korkh.SummaryTask4.web.ValidateForms;

import java.sql.Date;

import ua.nure.korkh.SummaryTask4.entity.Claim;

public class ClaimFormValidate extends Claim {

	private static final long serialVersionUID = 1L;

	private String descriptionError;

	private String idadministratorError;

	private String idflightError;

	private String iddispatcherError;

	private String statusError;

	private Date dateError;

	public String getDescriptionError() {
		return descriptionError;
	}

	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}

	public String getIdadministratorError() {
		return idadministratorError;
	}

	public void setIdadministratorError(String idadministratorError) {
		this.idadministratorError = idadministratorError;
	}

	public String getIdflightError() {
		return idflightError;
	}

	public void setIdflightError(String idflightError) {
		this.idflightError = idflightError;
	}

	public String getIddispatcherError() {
		return iddispatcherError;
	}

	public void setIddispatcherError(String iddispatcherError) {
		this.iddispatcherError = iddispatcherError;
	}

	public String getStatusError() {
		return statusError;
	}

	public void setStatusError(String statusError) {
		this.statusError = statusError;
	}

	public Date getDateError() {
		return dateError;
	}

	public void setDateError(Date dateError) {
		this.dateError = dateError;
	}

	@Override
	public String toString() {
		return super.toString() + "ClaimFormValidate [descriptionError="
				+ descriptionError + ", idadministratorError="
				+ idadministratorError + ", idflightError=" + idflightError
				+ ", iddispatcherError=" + iddispatcherError + ", statusError="
				+ statusError + ", dateError=" + dateError + "]";
	}

}

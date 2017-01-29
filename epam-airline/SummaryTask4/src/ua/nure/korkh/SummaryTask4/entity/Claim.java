package ua.nure.korkh.SummaryTask4.entity;

import java.sql.Date;

/**
 * Application entity.
 * 
 * @author Korkh
 * 
 */
public class Claim extends Entity {

	private static final long serialVersionUID = 1L;

	private String description;

	private Integer idadministrator;

	private Integer idflight;

	private Integer iddispatcher;

	private String status;

	private Date date;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdadministrator() {
		return idadministrator;
	}

	public void setIdadministrator(Integer idadministrator) {
		this.idadministrator = idadministrator;
	}

	public Integer getIdflight() {
		return idflight;
	}

	public void setIdflight(Integer idflight) {
		this.idflight = idflight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIddispatcher() {
		return iddispatcher;
	}

	public void setIddispatcher(Integer iddispatcher) {
		this.iddispatcher = iddispatcher;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Claim [description=" + description + ", idadministrator="
				+ idadministrator + ", idflight=" + idflight
				+ ", iddispatcher=" + iddispatcher + ", status=" + status
				+ ", date=" + date + "]";
	}

}

package ua.nure.korkh.SummaryTask4.bean;

import ua.nure.korkh.SummaryTask4.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * |brigade.id|flight.id|user.name|user.suname|user.roleid|
 * </pre>
 * 
 * @author Korh
 * 
 */
public class BrigadeBean extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idFligts;

	private String name;

	private String suname;

	private Integer role;

	public Integer getIdFligts() {
		return idFligts;
	}

	public void setIdFligts(Integer idFligts) {
		this.idFligts = idFligts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuname() {
		return suname;
	}

	public void setSuname(String suname) {
		this.suname = suname;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BrigadeBean [idFligts=" + idFligts + ", name=" + name
				+ ", suname=" + suname + ", role=" + role + "]";
	}

}

package ua.nure.korkh.SummaryTask4.entity;

/**
 * Plains entity.
 * 
 * @author Korkh
 * 
 */
public class Plane extends Entity {

	private static final long serialVersionUID = 1L;

	private String model;

	private Integer numberofseats;

	private Integer crew;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getNumberofseats() {
		return numberofseats;
	}

	public void setNumberofseats(Integer numberofseats) {
		this.numberofseats = numberofseats;
	}

	public Integer getCrew() {
		return crew;
	}

	public void setCrew(Integer crew) {
		this.crew = crew;
	}

	@Override
	public String toString() {
		return "Plains [model=" + model + ", numberofseats=" + numberofseats
				+ ", crew=" + crew + "]";
	}

}

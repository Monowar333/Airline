package ua.nure.korkh.SummaryTask4.web.ValidateForms;

import ua.nure.korkh.SummaryTask4.entity.Plane;

public class PlaneFormValidate extends Plane {
	
	private static final long serialVersionUID = 1L;

	private String modelError;

	private String numberofseatsError;

	private String crewError;
	
	public PlaneFormValidate() {
		super();
	}

	public PlaneFormValidate(Plane plane) {
		this.setId(plane.getId());
		this.setCrew(plane.getCrew());
		this.setModel(plane.getModel());
		this.setNumberofseats(plane.getNumberofseats());
	}

	public String getModelError() {
		return modelError;
	}

	public void setModelError(String modelError) {
		this.modelError = modelError;
	}

	public String getNumberofseatsError() {
		return numberofseatsError;
	}

	public void setNumberofseatsError(String numberofseatsError) {
		this.numberofseatsError = numberofseatsError;
	}

	public String getCrewError() {
		return crewError;
	}

	public void setCrewError(String crewError) {
		this.crewError = crewError;
	}

	@Override
	public String toString() {
		return super.toString() + "PalneValidate [modelError=" + modelError
				+ ", numberofseatsError=" + numberofseatsError + ", crewError="
				+ crewError + "]";
	}
	
	
	
}

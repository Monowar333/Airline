package ua.nure.korkh.SummaryTask4.entity;

/**
 * Brigade entity.
 * 
 * @author Korkh
 * 
 */
public class Brigade extends Entity {

	private static final long serialVersionUID = 1L;

	private Integer idusers;

	private Integer idflight;

	public Integer getIdusers() {
		return idusers;
	}

	public void setIdusers(Integer idusers) {
		this.idusers = idusers;
	}

	public Integer getIdflight() {
		return idflight;
	}

	public void setIdflight(Integer idflight) {
		this.idflight = idflight;
	}

	@Override
	public String toString() {
		return "Brigade [ idusers=" + idusers + ", idflight=" + idflight + "]";
	}

	
}

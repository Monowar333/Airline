package ua.nure.korkh.SummaryTask4.entity;

/**
 * Airport entity.
 * 
 * @author Korkh
 * 
 */
public class Airport extends Entity {
	private static final long serialVersionUID = 1L;

	private String name;

	private String country;

	private String city;

	private String status;

	private String iatacode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIatacode() {
		return iatacode;
	}

	public void setIatacode(String iatacode) {
		this.iatacode = iatacode;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + ", country=" + country + ", city="
				+ city + ", status=" + status + ", iatacode=" + iatacode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((iatacode == null) ? 0 : iatacode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Airport other = (Airport) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (iatacode == null) {
			if (other.iatacode != null)
				return false;
		} else if (!iatacode.equals(other.iatacode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
}

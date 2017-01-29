package ua.nure.korkh.SummaryTask4.entity;

import java.sql.Date;

/**
 * User entity.
 * 
 * @author Korkh
 * 
 */
public class User extends Entity {

	
	private static final long serialVersionUID = 1L;

	private String login;

	private String password;

	private String sex;

	private Date dateofbirth;

	private String email;

	private String telephone;

	private String name;

	private String suname;

	private int roleId;
	
	private Boolean registrstatus;
	
	private String linckaccept;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Boolean getRegistrstatus() {
		return registrstatus;
	}

	public void setRegistrstatus(Boolean registrstatus) {
		this.registrstatus = registrstatus;
	}

	public String getLinckaccept() {
		return linckaccept;
	}

	public void setLinckaccept(String linckaccept) {
		this.linckaccept = linckaccept;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateofbirth == null) ? 0 : dateofbirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((linckaccept == null) ? 0 : linckaccept.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((registrstatus == null) ? 0 : registrstatus.hashCode());
		result = prime * result + roleId;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((suname == null) ? 0 : suname.hashCode());
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
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
		User other = (User) obj;
		if (dateofbirth == null) {
			if (other.dateofbirth != null)
				return false;
		} else if (!dateofbirth.equals(other.dateofbirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (linckaccept == null) {
			if (other.linckaccept != null)
				return false;
		} else if (!linckaccept.equals(other.linckaccept))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registrstatus == null) {
			if (other.registrstatus != null)
				return false;
		} else if (!registrstatus.equals(other.registrstatus))
			return false;
		if (roleId != other.roleId)
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (suname == null) {
			if (other.suname != null)
				return false;
		} else if (!suname.equals(other.suname))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}
	


}

package ua.nure.korkh.SummaryTask4.web.ValidateForms;

import ua.nure.korkh.SummaryTask4.entity.User;

public class UserFormValidate extends User {

	private static final long serialVersionUID = 1L;

	private String loginError;

	private String passwordError;

	private String sexError;

	private String dateofbirthError;

	private String emailError;

	private String telephoneError;

	private String nameError;

	private String sunameError;

	private String roleIdError;

	public UserFormValidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserFormValidate(User user) {
		this.setDateofbirth(user.getDateofbirth());
		this.setLogin(user.getLogin());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setRoleId(user.getRoleId());
		this.setSex(user.getSex());
		this.setSuname(user.getSuname());
		this.setTelephone(user.getTelephone());
		this.setId(user.getId());
	}

	public String getloginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getSexError() {
		return sexError;
	}

	public void setSexError(String sexError) {
		this.sexError = sexError;
	}

	public String getDateofbirthError() {
		return dateofbirthError;
	}

	public void setDateofbirthError(String dateofbirthError) {
		this.dateofbirthError = dateofbirthError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

	public String getTelephoneError() {
		return telephoneError;
	}

	public void setTelephoneError(String telephoneError) {
		this.telephoneError = telephoneError;
	}

	public String getNameError() {
		return nameError;
	}

	public void setNameError(String nameError) {
		this.nameError = nameError;
	}

	public String getSunameError() {
		return sunameError;
	}

	public void setSunameError(String sunameError) {
		this.sunameError = sunameError;
	}

	public String getRoleIdError() {
		return roleIdError;
	}

	public void setRoleIdError(String roleIdError) {
		this.roleIdError = roleIdError;
	}

	@Override
	public String toString() {
		return super.toString() + "UserFormValidate [loginError=" + loginError
				+ ", passwordError=" + passwordError + ", sexError=" + sexError
				+ ", dateofbirthError=" + dateofbirthError + ", emailError="
				+ emailError + ", telephoneError=" + telephoneError
				+ ", nameError=" + nameError + ", sunameError=" + sunameError
				+ ", roleIdError=" + roleIdError + "]";
	}

}

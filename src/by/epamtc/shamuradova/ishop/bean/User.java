package by.epamtc.shamuradova.ishop.bean;

import java.util.Arrays;

public class User extends AbstractEntity<Integer> {

	private static final long serialVersionUID = -8615684563693732069L;


	private String name;
	private String surname;
	private String login;
	private String email;
	private String status;
	private String role;

	public User() {
	}
	

	public User(String name, String surname, String login, String email, String status, String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.status = status;
		this.role = role;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}


	@Override
	public String toString() {
		super.toString();
		return "User [ id=" + getId() + ", name=" + name + ", surname=" + surname + ", login=" + login  + ", email=" + email + ", status=" + status + ", role=" + role + "]";
	}

	

}

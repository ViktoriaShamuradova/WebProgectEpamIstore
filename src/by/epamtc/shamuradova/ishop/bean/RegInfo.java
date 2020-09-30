package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;
import java.util.Arrays;

public class RegInfo implements Serializable{
	
	private static final long serialVersionUID = -1027252463562606394L;
	
	private String name;
	private String surname;
	private String login;
	private String email;
	private char[] password;
	private int idUserStatus;
	private int idUserRole;
	

	public RegInfo(String name, String surname, String login, String email, char[] password, int idUserStatus,
			int idUserRole) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
		this.idUserStatus = idUserStatus;
		this.idUserRole = idUserRole;
	}

	public RegInfo() {}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public int getIdUserStatus() {
		return idUserStatus;
	}
	public void setIdUserStatus(int idUserStatus) {
		this.idUserStatus = idUserStatus;
	}
	public int getIdUserRole() {
		return idUserRole;
	}
	public void setIdUserRole(int idUserRole) {
		this.idUserRole = idUserRole;
	}
	public void deletePassword() {
		Arrays.fill(password, ' ');
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idUserRole;
		result = prime * result + idUserStatus;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		RegInfo other = (RegInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUserRole != other.idUserRole)
			return false;
		if (idUserStatus != other.idUserStatus)
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
		if (!Arrays.equals(password, other.password))
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
		return "RegInfo [name=" + name + ", surname=" + surname + ", login=" + login + ", email=" + email
				+ ", password=" + Arrays.toString(password) + ", idUserStatus=" + idUserStatus + ", idUserRole="
				+ idUserRole + "]";
	}
	
}

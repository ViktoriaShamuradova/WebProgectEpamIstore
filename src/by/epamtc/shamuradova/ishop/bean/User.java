package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Бегенч
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8615684563693732069L;

	private int id;
	private String name;
	private String surname;
	private String login;
	private char[] password;
	private String email;
	private int userStatus;
	private int userRole;

	public User() {
	}
	


	public User(int id, String name, String surname, String login, char[] password, String email, int userStatus,
			int userRole) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.userStatus = userStatus;
		this.userRole = userRole;
	}



	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + userRole;
		result = prime * result + userStatus;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
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
		if (userRole != other.userRole)
			return false;
		if (userStatus != other.userStatus)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ Arrays.toString(password) + ", email=" + email + ", userStatus=" + userStatus + ", userRole="
				+ userRole + "]";
	}






	
}

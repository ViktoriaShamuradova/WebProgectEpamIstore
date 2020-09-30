package by.epamtc.shamuradova.ishop.bean;

import java.io.Serializable;
import java.util.Arrays;

public class AuthData implements Serializable{

	private static final long serialVersionUID = -3103845176703225446L;
	
	private String login;
	private char[] password;
	
	public AuthData(String login, char[] password) {
		super();
		this.login = login;
		this.password = password;
	}
	public AuthData() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public void deletePassword() {
		Arrays.fill(password, ' ');
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + Arrays.hashCode(password);
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
		AuthData other = (AuthData) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (!Arrays.equals(password, other.password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AuthData [login=" + login + ", password=" + Arrays.toString(password) + "]";
	}

	

	
}

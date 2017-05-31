package main.accounts;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Accounts")
public class AccountModel implements Serializable {
	
	private static final long serialVersionUID = 3105543119135236787L;

	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "bunk_number")
	private String bankNumber;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private char[] password;
	
	@Column(name = "user_type")
	@Enumerated(EnumType.ORDINAL)
	private UserType userType;
	
	@Column(name = "email")
	private String email;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankNumber() {
		return this.bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public char[] getPassword() {
		return this.password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public enum UserType {
		USER("Користувач"),
		ADMIN("Адмiнiстратор");

		private final String name;

		UserType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
		result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AccountModel))
			return false;
		AccountModel other = (AccountModel) obj;
		if (this.id != other.id)
			return false;
		if (this.login == null) {
			if (other.login != null)
				return false;
		} else if (!this.login.equals(other.login))
			return false;
		if (this.password == null) {
			if (other.password != null)
				return false;
		} else if (!Arrays.equals(this.password, other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountModel [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", middleName="
				+ this.middleName + ", phone=" + this.phone + ", bankNumber=" + this.bankNumber + ", login=" + this.login + ", password="
				+ Arrays.toString(this.password) + ", userType=" + this.userType + ", email=" + this.email + "]";
	}	
	
}

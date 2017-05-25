package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Forms")
public class IndividualModel implements Serializable {
	
	private static final long serialVersionUID = 136446134592004371L;

	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "TIN")
	private String TIN;
	
	private int age;
	
	@Column(name = "maritial_status")
	@Enumerated(EnumType.ORDINAL)
	private MaritialStatus maritialStatus;
	
	@Column(name = "children_number")
	private int childrenNum;
	
	@Column(name = "field_of_activity")
	@Enumerated(EnumType.ORDINAL)
	private FieldOfActivity fieldOfActivity;
	
	@Enumerated(EnumType.ORDINAL)
	private Qualification qualification;
	
	@Column(name = "work_experience")
	private int yearsOfWorkExperience;
	
	@Column(name = "credit_history")
	@Enumerated(EnumType.ORDINAL)
	private CreditHistory creditHistory;
	
	@Column(name = "monthly_income")
	private double monthlyIncome;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CreditModel> credits = new ArrayList<>();
	
	@Column(name = "rate")
	private double rate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTIN() {
		return TIN;
	}

	public void setTIN(String tIN) {
		TIN = tIN;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MaritialStatus getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(MaritialStatus maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public int getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(int childrenNum) {
		this.childrenNum = childrenNum;
	}

	public FieldOfActivity getFieldOfActivity() {
		return fieldOfActivity;
	}

	public void setFieldOfActivity(FieldOfActivity fieldOfActivity) {
		this.fieldOfActivity = fieldOfActivity;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public int getYearsOfWorkExperience() {
		return yearsOfWorkExperience;
	}

	public void setYearsOfWorkExperience(int yearsOfWorkExperience) {
		this.yearsOfWorkExperience = yearsOfWorkExperience;
	}

	public CreditHistory getCreditHistory() {
		return creditHistory;
	}

	public void setCreditHistory(CreditHistory creditHistory) {
		this.creditHistory = creditHistory;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	
	public List<CreditModel> getCredits() {
		return credits;
	}

	public void setCredits(List<CreditModel> credits) {
		this.credits = credits;
	}
	
	public CreditModel getLastCredit() {
		return credits.get(credits.size()-1);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public enum MaritialStatus {
		SINGLE("Неодружений / Не заміжня"),
		MARRIED("Одружений / Одружена"),
		MARRIED_BUT_LIVING_SEPARATELY("Одружений, але живе окремо"),
		DIVORCED("Розлучений / Розлучена"),
		WIDOWER("Вдівець / Вдова");

		private final String name;

		MaritialStatus(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum FieldOfActivity {
		CIVIL_SERVICE("Держслужба"), PRIVATE_SECTOR("Приватний сектор"), STUDENT("Учень"), PENSIONER("Пенсіонер");

		private final String name;

		FieldOfActivity(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum Qualification {
		NO("Ні"),
		SUPPORT_STAFF("Допоміжний персонал"),
		SPECIALIST("Спеціаліст"),
		SERVANT("Службовець"), 
		SUPERVISOR("Керівний працівник");

		private final String name;

		Qualification(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	public enum CreditHistory {
		NO("\u041D\u0456"), //нет
		WAS_REPAID("\u0411\u0443\u043B\u0438, \u043F\u043E\u0433\u0430\u0448\u0435\u043D\u0456"), // погашены
		IS_REPAID_REGULARLY("\u0411\u0443\u043B\u0438, \u0441\u043F\u0440\u0430\u0432\u043D\u043E \u0432\u0438\u043F\u043B\u0430\u0447\u0443\u044E\u0442\u044C\u0441\u044F"), // есть кредиты
		BLACK_LIST("\u041F\u043E\u0437\u0438\u0447\u0430\u043B\u044C\u043D\u0438\u043A \u0443 \u0447\u043E\u0440\u043D\u043E\u043C\u0443 \u0441\u043F\u0438\u0441\u043A\u0443"); // черынй список
		
		private final String name;

		CreditHistory(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IndividualModel))
			return false;
		IndividualModel other = (IndividualModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
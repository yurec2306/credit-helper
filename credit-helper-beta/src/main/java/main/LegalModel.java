package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import main.IndividualModel.CreditHistory;

@Entity(name = "Legal_Forms")
public class LegalModel implements Serializable {

	private static final long serialVersionUID = -7671423371207919309L;

	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "organization_name")
	private String organizationName;
	
	@Column(name = "organization_TIN")
	private String organizationTIN;
	
	@Column(name = "entrance_reporting_form")
	private String entranceReportingForm;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "director")
	private String director; 
	
	@Column(name = "director_phone")
	private String directorPhone;
	
	@Column(name = "accountant")
	private String accountant;
	
	@Column(name = "accountant_phone")
	private String accountantPhone;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
	@Column(name = "contact_person_phone")
	private String contactPersonPhone;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "NACE")
	private String NACE;
	
	@Column(name = "USREOU")
	private String USREOU;
	
	@Column(name = "KOATUU")
	private String KOATUU;
	
	@Column(name = "credit_history")
	@Enumerated(EnumType.ORDINAL)
	private CreditHistory creditHistory;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<LegalCreditModel> credits = new ArrayList<>();
	
	@Column(name = "rate")
	private double rate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationTIN() {
		return organizationTIN;
	}

	public void setOrganizationTIN(String organizationTIN) {
		this.organizationTIN = organizationTIN;
	}

	public String getEntranceReportingForm() {
		return entranceReportingForm;
	}

	public void setEntranceReportingForm(String entranceReportingForm) {
		this.entranceReportingForm = entranceReportingForm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public String getAccountant() {
		return accountant;
	}

	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	public String getAccountantPhone() {
		return accountantPhone;
	}

	public void setAccountantPhone(String accountantPhone) {
		this.accountantPhone = accountantPhone;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNACE() {
		return NACE;
	}

	public void setNACE(String nACE) {
		NACE = nACE;
	}

	public String getUSREOU() {
		return USREOU;
	}

	public void setUSREOU(String uSREOU) {
		USREOU = uSREOU;
	}

	public String getKOATUU() {
		return KOATUU;
	}

	public void setKOATUU(String kOATUU) {
		KOATUU = kOATUU;
	}

	public CreditHistory getCreditHistory() {
		return creditHistory;
	}

	public void setCreditHistory(CreditHistory creditHistory) {
		this.creditHistory = creditHistory;
	}
	
	public List<LegalCreditModel> getCredits() {
		return credits;
	}

	public void setCredits(List<LegalCreditModel> credits) {
		this.credits = credits;
	}
	
	public LegalCreditModel getLastCredit() {
		return credits.get(credits.size()-1);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LegalModel))
			return false;
		LegalModel other = (LegalModel) obj;
		if (id != other.id)
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		return true;
	}
	
}

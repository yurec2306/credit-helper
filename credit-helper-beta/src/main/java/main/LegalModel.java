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
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationTIN() {
		return this.organizationTIN;
	}

	public void setOrganizationTIN(String organizationTIN) {
		this.organizationTIN = organizationTIN;
	}

	public String getEntranceReportingForm() {
		return this.entranceReportingForm;
	}

	public void setEntranceReportingForm(String entranceReportingForm) {
		this.entranceReportingForm = entranceReportingForm;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDirectorPhone() {
		return this.directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public String getAccountant() {
		return this.accountant;
	}

	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	public String getAccountantPhone() {
		return this.accountantPhone;
	}

	public void setAccountantPhone(String accountantPhone) {
		this.accountantPhone = accountantPhone;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonPhone() {
		return this.contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNACE() {
		return this.NACE;
	}

	public void setNACE(String nACE) {
		this.NACE = nACE;
	}

	public String getUSREOU() {
		return this.USREOU;
	}

	public void setUSREOU(String uSREOU) {
		this.USREOU = uSREOU;
	}

	public String getKOATUU() {
		return this.KOATUU;
	}

	public void setKOATUU(String kOATUU) {
		this.KOATUU = kOATUU;
	}

	public CreditHistory getCreditHistory() {
		return this.creditHistory;
	}

	public void setCreditHistory(CreditHistory creditHistory) {
		this.creditHistory = creditHistory;
	}
	
	public List<LegalCreditModel> getCredits() {
		return this.credits;
	}

	public void setCredits(List<LegalCreditModel> credits) {
		this.credits = credits;
	}
	
	public LegalCreditModel getLastCredit() {
		return this.credits.get(this.credits.size()-1);
	}

	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		result = prime * result + ((this.organizationName == null) ? 0 : this.organizationName.hashCode());
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
		if (this.id != other.id)
			return false;
		if (this.organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!this.organizationName.equals(other.organizationName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LegalModel [id=" + this.id + ", organizationName=" + this.organizationName + ", organizationTIN="
				+ this.organizationTIN + ", entranceReportingForm=" + this.entranceReportingForm + ", address=" + this.address
				+ ", branch=" + this.branch + ", director=" + this.director + ", directorPhone=" + this.directorPhone + ", accountant="
				+ this.accountant + ", accountantPhone=" + this.accountantPhone + ", contactPerson=" + this.contactPerson
				+ ", contactPersonPhone=" + this.contactPersonPhone + ", fax=" + this.fax + ", email=" + this.email + ", NACE=" + this.NACE
				+ ", USREOU=" + this.USREOU + ", KOATUU=" + this.KOATUU + ", creditHistory=" + this.creditHistory + ", credits="
				+ this.credits + ", rate=" + this.rate + "]";
	}
	
}

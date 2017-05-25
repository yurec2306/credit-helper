package main;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "Credits")
public class CreditModel implements Serializable {
	
	private static final long serialVersionUID = -6447795451875037688L;

	@GeneratedValue
	@Id
	private int id;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "credit_type")
	private CreditType creditType;
	
	@Column(name = "credit_rate")
	private double creditRate;
	
	@Column(name = "credit_size")
	private double creditSize;
	
	@Column(name = "credit_length")
	private double creditLength;
	
	private double provision; // обеспечение
	
	@Column(name = "down_payment")
	private double downPayment; // первый взнос
	
	@Column(name = "net_income")
	private double netIncome; // чистый доход
	
	@Column(name = "percent_cost")
	private double percentCost;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "credit_status")
	private CreditStatus creditStatus = CreditStatus.UNKNOWN;
	
	public enum CreditType {
		SHORT_TERM("Короткостроковий"),
		LONG_TERM_CAR("Довгостроковий на машину"),
		LONG_TERM_HOME("Довгостроковий на квартиру");
		
		private final String name;

		CreditType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	public enum CreditStatus {
		UNKNOWN("Не визначено"),
		RETURNED("Кредит виплачено"),
		NOT_RETURNED("Кредит не виплачено");
		
		private final String name;

		CreditStatus(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CreditType getCreditType() {
		return creditType;
	}

	public void setCreditType(CreditType creditType) {
		this.creditType = creditType;
	}

	public double getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(double creditRate) {
		this.creditRate = creditRate;
	}

	public double getCreditSize() {
		return creditSize;
	}

	public void setCreditSize(double creditSize) {
		this.creditSize = creditSize;
	}

	public double getCreditLength() {
		return creditLength;
	}

	public void setCreditLength(double creditLength) {
		this.creditLength = creditLength;
	}

	public double getProvision() {
		return provision;
	}

	public void setProvision(double provision) {
		this.provision = provision;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}

	public double getPercentCost() {
		return percentCost;
	}

	public void setPercentCost(double percentCost) {
		this.percentCost = percentCost;
	}
	
	public CreditStatus getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(CreditStatus creditStatus) {
		this.creditStatus = creditStatus;
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
		if (!(obj instanceof CreditModel))
			return false;
		CreditModel other = (CreditModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

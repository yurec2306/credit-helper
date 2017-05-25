package main;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import main.CreditModel.CreditStatus;
import main.CreditModel.CreditType;

@Entity(name = "Legal_Credits")
public class LegalCreditModel implements Serializable {
	
	private static final long serialVersionUID = 3417488611074766551L;

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
	
	@Column(name = "income")
	private double income; // доходы
	
	@Column(name = "costs")
	private double costs; // расходы
	
	@Column(name = "percent_cost")
	private double percentCost;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "credit_status")
	private CreditStatus creditStatus = CreditStatus.UNKNOWN;
	
	@Column(name = "cash")
	private double cash;
	
	@Column(name = "current_liabilities")
	private double currentLiabilities;
	
	@Column(name = "deferred_income")
	private double deferredIncome;
	
	@Column(name = "short_term_investments")
	private double shortTermInvestments;
	
	@Column(name = "payable_for_12_months")
	private double payableFor12Months;
	
	@Column(name = "current_assets")
	private double currentAssets;
	
	@Column(name = "reserves_for_future_payments")
	private double reservesForFuturePayments;
	
	@Column(name = "capital_and_reserves_all")
	private double capitalAndReservesAll;
	
	@Column(name = "long_term_commitment")
	private double longTermCommitment;

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
		return income;
	}

	public void setNetIncome(double netIncome) {
		this.income = netIncome;
	}

	public double getCosts() {
		return costs;
	}

	public void setCosts(double costs) {
		this.costs = costs;
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

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCurrentLiabilities() {
		return currentLiabilities;
	}

	public void setCurrentLiabilities(double currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}

	public double getDeferredIncome() {
		return deferredIncome;
	}

	public void setDeferredIncome(double deferredIncome) {
		this.deferredIncome = deferredIncome;
	}

	public double getShortTermInvestments() {
		return shortTermInvestments;
	}

	public void setShortTermInvestments(double shortTermInvestments) {
		this.shortTermInvestments = shortTermInvestments;
	}

	public double getPayableFor12Months() {
		return payableFor12Months;
	}

	public void setPayableFor12Months(double payableFor12Months) {
		this.payableFor12Months = payableFor12Months;
	}

	public double getCurrentAssets() {
		return currentAssets;
	}

	public void setCurrentAssets(double currentAssets) {
		this.currentAssets = currentAssets;
	}

	public double getReservesForFuturePayments() {
		return reservesForFuturePayments;
	}

	public void setReservesForFuturePayments(double reservesForFuturePayments) {
		this.reservesForFuturePayments = reservesForFuturePayments;
	}

	public double getCapitalAndReservesAll() {
		return capitalAndReservesAll;
	}

	public void setCapitalAndReservesAll(double capitalAndReservesAll) {
		this.capitalAndReservesAll = capitalAndReservesAll;
	}

	public double getLongTermCommitment() {
		return longTermCommitment;
	}

	public void setLongTermCommitment(double longTermCommitment) {
		this.longTermCommitment = longTermCommitment;
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
		if (!(obj instanceof LegalCreditModel))
			return false;
		LegalCreditModel other = (LegalCreditModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

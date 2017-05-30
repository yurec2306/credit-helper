package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.ErrorWindow;
import main.LegalCreditModel;
import main.LegalModel;

public class Step6LegalController extends AbstractLegalStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step6LegalController.class);

	private Step6LegalWindow window;
	private int status = 0;
	
	public Step6LegalController(LegalModel model) {
		super(model);
		
		logger.trace("Creating Step6LegalController");
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Step6LegalController.this.window = new Step6LegalWindow();
					calcCategory(Step6LegalController.this.model.getLastCredit());
					Step6LegalController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Step6LegalController.this.window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (isCreditReady(Step6LegalController.this.model.getLastCredit(), Step6LegalController.this.status)) {
							Step6LegalController.this.window.setVisible(false);
							Step7LegalController step7 = new Step7LegalController(Step6LegalController.this.model);
							step7.init();
							Step6LegalController.this.window.dispose();
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

	private void calcCategory(LegalCreditModel credit) {
		logger.trace("Calling calcCategory(LegalCreditModel credit)");
		
		double cash = credit.getCash();
		double currentLiabilities = credit.getCurrentLiabilities();
		double defferedIncome = credit.getDeferredIncome();
		double reservesForFuturePayments = credit.getReservesForFuturePayments();
		double shortTermInvest = credit.getShortTermInvestments();
		double payableFor12Months = credit.getPayableFor12Months();
		double currentAssets = credit.getCurrentAssets();
		double capitalAndReservesAll = credit.getCapitalAndReservesAll();
		double longTermCommitment = credit.getLongTermCommitment();
		double netIncome = credit.getNetIncome();
		double costs = credit.getCosts();
		
		double k1 = cash / (currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k1: ", k1);
		
		double k2 = (cash +	shortTermInvest + payableFor12Months) / (currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k2: ", k2);
		
		double k3 = currentAssets / (currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k3: ", k3);
		
		double k4 = capitalAndReservesAll / (longTermCommitment - currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k4: ", k4);
		
		double k5 =  netIncome / costs;
		logger.debug("k5: ", k5);
		
		setToWindow(k1, k2, k3, k4, k5);
		
		double sum = 0.11 * k1 + 0.05 * k2 + 0.42 * k3 + 0.21 * k4 + 0.21 * k5;
		logger.debug("sum: ", sum);
		
		calcStatus(sum);
		logger.trace("Returning from calcCategory(...)");
	}
	
	private void setToWindow(double k1, double k2, double k3, double k4, double k5) {
		logger.trace("Calling setToWindow(double k1, double k2, double k3, double k4, double k5)");
		
		this.window.tfAbsoluteLiquidityRatio.setText(String.valueOf(k1));
		this.window.tfCriticalEvaluationFactor.setText(String.valueOf(k2));
		this.window.tfCurrentRatio.setText(String.valueOf(k3));
		this.window.tfTheRatioOfFunds.setText(String.valueOf(k4));
		this.window.tfProfitability.setText(String.valueOf(k5));
		
		logger.trace("Returning from setToWindow(...)");
	}

	private void calcStatus(double s) {
		logger.trace("Calling calcStatus(double s)");
		
		if(1 < s && s < 1.05) {
			this.status = 1;	
		} else if (s < 2.42) {
			this.status = 2;
		} else {
			this.status = 3;
		}
		logger.debug("status:", this.status);
		
		logger.trace("Returning from calcStatus(...)");
	}
	
	private boolean isCreditReady(LegalCreditModel credit, int status) {
		logger.trace("Creating isCreditReady(LegalCreditModel credit, int status)");
		
		double creditSize = credit.getCreditSize();
		double creditRate = credit.getCreditRate();
		double creditLength = credit.getCreditLength();
		double creditProvision = credit.getProvision();
		
		boolean result = false;
		double percentCost = calcPercentCost(creditSize, creditRate, creditLength);		
		double sum = calcSum(creditSize, percentCost);
		double allNetIncome = calcAllNetIncome(creditProvision, this.model.getLastCredit().getCosts(), creditLength);
		
		logger.debug("status: ", status);
		switch (status) {
		case 1:
			if(creditSize < allNetIncome) {	
				logger.debug("creditSize({}) < allNetIncome({})", creditSize, allNetIncome);
				
				result =  true;
			} else {	
				logger.debug("creditSize({}) > allNetIncome({})", creditSize, allNetIncome);
				
				ErrorWindow error = new ErrorWindow("�� " + creditLength + " ������ �볺�� ���� ��������� ���� " + allNetIncome + " ���. � " + sum + " ���.");
				error.setVisible(true);
			}
			break;
		case 2:
			if(creditSize < (allNetIncome * 2.0)) {	
				logger.debug("creditSize({}) < 2*allNetIncome({})", creditSize, 2.0*allNetIncome);
				
				result =  true;
			} else {
				logger.debug("creditSize({}) > 2*allNetIncome({})", creditSize, 2.0*allNetIncome);
				
				ErrorWindow error = new ErrorWindow("����� ������������ (" + creditProvision + ") ������� ���� ����� ��������� ������� ������ (" + allNetIncome * 2.0 + " ���.)");
				error.setVisible(true);
			}
		}
		
		logger.trace("Returning from isCreditReady(...)");
		logger.debug("Returning: ", result);
		return result;
	}

	private double calcSum(double creditSize, double percentCost) {
		logger.trace("Creating calcSum(double creditSize, double percentCost)");
		
		double result = creditSize + percentCost;
		
		logger.trace("Returning from calcSum(...)");
		logger.debug("Returning: ", result);
		return result;
	}

	private double calcPercentCost(double creditSize, double creditRate, double creditLength) {
		logger.trace("Creating calcPercentCost(double creditSize, double creditRate, double creditLength)");
		
		double result = creditSize * creditRate * (creditLength + 1.0) / 24.0;
		
		logger.trace("Returning from calcPercentCost(...)");
		logger.debug("Returning: ", result);
		return result;
	}
	
	private double calcAllNetIncome(double provision, double costs, double creditLength) {
		logger.trace("Creating calcAllNetIncome(double provision, double costs, double creditLength)");
		
		double netIncome = (provision - costs) * 0.8 * 0.8; // ������ �����
		logger.debug("netIncome: ", netIncome);
		
		double allNetIncome = netIncome * Math.ceil(creditLength / 12.0); // ������ ����� �� ���� ������
		
		logger.trace("Returning from calcAllNetIncome(...)");
		logger.debug("Returning: ", allNetIncome);
		return allNetIncome;
	}

}

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
		logger.trace("Calling Step6LegalController({})", model);
		logger.trace("Returning from Step6LegalController({})", model);
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new Step6LegalWindow();
					calcCategory(model.getLastCredit());
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (isCreditReady(model.getLastCredit(), status)) {
							window.setVisible(false);
							Step7LegalController step7 = new Step7LegalController(model);
							step7.init();
							window.dispose();
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

	private void calcCategory(LegalCreditModel credit) {
		logger.trace("Calling calcCategory({})", credit);
		
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
		logger.debug("k1: {}", k1);
		
		double k2 = (cash +	shortTermInvest + payableFor12Months) / (currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k2: {}", k2);
		
		double k3 = currentAssets / (currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k3: {}", k3);
		
		double k4 = capitalAndReservesAll / (longTermCommitment - currentLiabilities - defferedIncome - reservesForFuturePayments);
		logger.debug("k4: {}", k4);
		
		double k5 =  netIncome / costs;
		logger.debug("k5: {}", k5);
		
		setToWindow(k1, k2, k3, k4, k5);
		
		double sum = 0.11 * k1 + 0.05 * k2 + 0.42 * k3 + 0.21 * k4 + 0.21 * k5;
		logger.debug("sum: {}", sum);
		
		calcStatus(sum);
		logger.trace("Returning from calcCategory({})", credit);
	}
	
	private void setToWindow(double k1, double k2, double k3, double k4, double k5) {
		logger.trace("Calling setToWindow({}, {}, {}, {}, {})", k1, k2, k3, k4, k5);
		
		this.window.tfAbsoluteLiquidityRatio.setText(String.valueOf(k1));
		this.window.tfCriticalEvaluationFactor.setText(String.valueOf(k2));
		this.window.tfCurrentRatio.setText(String.valueOf(k3));
		this.window.tfTheRatioOfFunds.setText(String.valueOf(k4));
		this.window.tfProfitability.setText(String.valueOf(k5));
		
		logger.trace("Returning from setToWindow({}, {}, {}, {}, {})", k1, k2, k3, k4, k5);
	}

	private void calcStatus(double s) {
		logger.trace("Calling calcStatus({})", s);
		
		if(1 < s && s < 1.05) {
			this.status = 1;	
		} else if (s < 2.42) {
			this.status = 2;
		} else {
			this.status = 3;
		}
		logger.debug("status:", this.status);
		
		logger.trace("Returning from calcStatus({})", s);
	}
	
	private boolean isCreditReady(LegalCreditModel credit, int status) {
		logger.trace("Calling isCreditReady({}, {})", credit, status);
		
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
				
				ErrorWindow error = new ErrorWindow("За " + creditLength + " місяців клієнт може выплатити лише " + allNetIncome + " грн. з " + sum + " грн.");
				error.setVisible(true);
			}
			break;
		case 2:
			if(creditSize < (allNetIncome * 2.0)) {	
				logger.debug("creditSize({}) < 2*allNetIncome({})", creditSize, 2.0*allNetIncome);
				
				result =  true;
			} else {
				logger.debug("creditSize({}) > 2*allNetIncome({})", creditSize, 2.0*allNetIncome);
				
				ErrorWindow error = new ErrorWindow("Розмір забезпечення (" + creditProvision + ") повинен бути більше подвійного чистого доходу (" + allNetIncome * 2.0 + " грн.)");
				error.setVisible(true);
			}
			break;
		}
		
		logger.debug("result: {}", result);	
		logger.trace("Returning from isCreditReady({}, {})", credit, status);
		return result;
	}

	private static double calcSum(double creditSize, double percentCost) {
		logger.trace("Calling calcSum({}, {})", creditSize, percentCost);
		
		double result = creditSize + percentCost;
		
		logger.debug("result: {}", result);
		logger.trace("Returning from calcSum({}, {})", creditSize, percentCost);
		return result;
	}

	private static double calcPercentCost(double creditSize, double creditRate, double creditLength) {
		logger.trace("Calling calcPercentCost({}, {}, {})", creditSize, creditRate, creditLength);
		
		double result = creditSize * creditRate * (creditLength + 1.0) / 24.0;
		
		logger.debug("result: {}", result);
		logger.trace("Returning from calcPercentCost({}, {}, {})", creditSize, creditRate, creditLength);
		return result;
	}
	
	private static double calcAllNetIncome(double provision, double costs, double creditLength) {
		logger.trace("Calling calcAllNetIncome({}, {}, {})", provision, costs, creditLength);
		
		double netIncome = (provision - costs) * 0.8 * 0.8; // чистый доход
		logger.debug("netIncome: {}", netIncome);
		
		double allNetIncome = netIncome * Math.ceil(creditLength / 12.0); // чистый доход за весь период
		logger.debug("allNetIncome: {}", allNetIncome);
		
		logger.trace("Returning from calcAllNetIncome({}, {}, {})", provision, costs, creditLength);
		return allNetIncome;
	}

}

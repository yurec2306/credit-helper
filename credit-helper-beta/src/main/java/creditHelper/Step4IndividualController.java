package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.CreditModel;
import main.CreditModel.CreditType;
import main.IndividualModel.CreditHistory;
import main.ErrorWindow;
import main.IndividualModel;

public class Step4IndividualController extends AbstractStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step4IndividualController.class);

	private final static double SMALL_CREDIT_PERCENT = 0.20;
	private final static double CAR_CREDIT_PERCENT = 0.10;
	private final static double HOME_CREDIT_PERCENT = 0.20;
	
	private Step4IndividualWindow window;

	public Step4IndividualController(IndividualModel model) {
		super(model);
		logger.trace("Creating Step4IndividualController(%s)", model);
		logger.trace("Returning from Step4IndividualController(%s)", model);
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new Step4IndividualWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setToModel(window);
						if(checkValidness(model)) {
							window.setVisible(false);
							Step5IndividualController step4 = new Step5IndividualController(model);
							step4.init();
							window.dispose();
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

	public void setToModel(Step4IndividualWindow window) {
		logger.trace("Calling setToModel()");
		
		if (window.cbCreditType.getSelectedItem() == CreditType.SHORT_TERM) {
			
			logger.trace("CreditType == SHORT_TERM");
			
			this.model.getLastCredit().setProvision(Double.parseDouble(window.tfProvision.getText()));
			this.model.getLastCredit().setCreditRate(SMALL_CREDIT_PERCENT);
		} else {			
			this.model.getLastCredit().setDownPayment(Double.parseDouble(window.tfDownPayment.getText()));
			if (this.model.getLastCredit().getCreditType() == CreditType.LONG_TERM_CAR) {
				
				logger.trace("CreditType == LONG_TERM_CAR");
				
				this.model.getLastCredit().setCreditRate(CAR_CREDIT_PERCENT);
			} else {
				
				logger.trace("CreditType == LONG_TERM_HOME");
				
				this.model.getLastCredit().setCreditRate(HOME_CREDIT_PERCENT);
			}
		}
		this.model.getLastCredit().setCreditType((CreditType) window.cbCreditType.getSelectedItem());
		this.model.getLastCredit().setCreditSize(Double.parseDouble(window.tfCreditSize.getText()));
		this.model.getLastCredit().setCreditLength(Double.parseDouble(window.tfCreditLength.getText()));
		
		logger.trace("Returning from setToModel()");
	}
	
	public boolean checkValidness(IndividualModel model) {
		logger.trace("Calling checkValidness()");
		
		double creditSize = model.getLastCredit().getCreditSize();
		double creditRate = model.getLastCredit().getCreditRate();
		double creditLength = model.getLastCredit().getCreditLength();
		double provision = model.getLastCredit().getProvision();
		double monthlyIncome = model.getMonthlyIncome();
		CreditHistory creditHistory = model.getCreditHistory();
		CreditType creditType = model.getLastCredit().getCreditType();
		
		boolean result = true;
		
		if (this.model.getLastCredit().getCreditType() == CreditType.SHORT_TERM) {
			logger.trace("CreditType == SHORT_TERM");
			
			double percentCost = creditSize * creditRate * (creditLength + 1.0) / 24.0;
			logger.debug("percentCost: ", percentCost);
			
			double sum = creditSize + percentCost;
			logger.debug("sum: ", sum);
			
			double netIncome = monthlyIncome * 0.4; // чистый доход
			logger.debug("netIncome: ", netIncome);
			
			double allNetIncome = netIncome * creditLength; // чистый доход за весь период
			logger.debug("allNetIncome: ", allNetIncome);
			
			if (allNetIncome < sum) {
				
				logger.debug("allNetIncome({}) < sum({})", allNetIncome, sum);
				
				ErrorWindow error = new ErrorWindow("За " + creditLength + " місяців клієнт може выплатити лише " + allNetIncome + " грн. з " + sum + "грн.");
				error.setVisible(true);
				result = false;
			}
			if (creditHistory == CreditHistory.IS_REPAID_REGULARLY && provision < (allNetIncome * 2.0)) {
				
				logger.trace("CreditHistory == IS_REPAID_REGULARLY");
				logger.debug("provision({}) < 2*allNetIncome({})", provision, (allNetIncome * 2.0));
				
				ErrorWindow error = new ErrorWindow("Розмір забезпечення (" + provision + "грн.) повинен бути більше подвійного чистого доходу (" + allNetIncome * 2.0 + " грн.)");
				error.setVisible(true);
				result = false;
			}
		} else {
			if (creditType == CreditType.LONG_TERM_CAR) { //машина
				
				logger.trace("CreditType == LONG_TERM_CAR");
				
				if(!calc(model.getLastCredit(), CAR_CREDIT_PERCENT)) {
					result = false;
				}
			} else { // квартира
				
				logger.trace("CreditType == LONG_TERM_HOME");
				
				if(!calc(model.getLastCredit(), HOME_CREDIT_PERCENT)) {
					result = false;
				}
			}
		}
		logger.trace("Returning from checkValidness()");
		return result;
	}
	
	private static boolean calc(CreditModel credit, double downPaymentMinPercent) {
		logger.trace("Calling from calc({}, {})", credit, downPaymentMinPercent);
		
		boolean result = true;
		if(credit.getDownPayment() < credit.getCreditSize() * downPaymentMinPercent) {
			
			logger.debug("downPayment({}) < creditSize * downPaymentMinPercent({})", credit.getDownPayment(), (credit.getCreditSize() * downPaymentMinPercent));
			
			result = false;
			ErrorWindow error = new ErrorWindow("Розмір першого взносу (" + credit.getDownPayment() + " грн.) повинен складати 10% від розміру кредиту (" + credit.getCreditSize() + " грн.)");
			error.setVisible(true);
		}
		double percentCost = (credit.getCreditSize() - credit.getDownPayment()) * credit.getCreditRate() * (credit.getCreditLength() + 1.0) / 24.0;
		logger.debug("percentCost: ", percentCost);
		
		double sum = credit.getCreditSize() + percentCost;
		logger.debug("sum: ", sum);
		
		if (sum < credit.getNetIncome()) {
			
			logger.debug("sum < netIncome");
			
			result = false;
			ErrorWindow error = new ErrorWindow("Розмір чистого доходу на місяць (" + credit.getDownPayment() + " грн.) повинен дорівнювати або бути більше суми повернення (" + sum + " грн.)");
			error.setVisible(true);
		}
		
		logger.trace("Returning from calc(...)");
		logger.debug("Returning: ", result);		
		return result;
	}
}

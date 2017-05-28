package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
					Step4IndividualController.this.window = new Step4IndividualWindow();
					Step4IndividualController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Step4IndividualController.this.window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setToModel();
						checkValidness();
						Step4IndividualController.this.window.setVisible(false);
						Step5IndividualController step4 = new Step5IndividualController(Step4IndividualController.this.model);
						step4.init();
						Step4IndividualController.this.window.dispose();
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

	private void setToModel() {
		logger.trace("Calling setToModel()");
		
		if (this.window.cbCreditType.getSelectedItem() == CreditType.SHORT_TERM) {
			
			logger.trace("CreditType == SHORT_TERM");
			
			this.model.getLastCredit().setProvision(Double.parseDouble(this.window.tfProvision.getText()));
			this.model.getLastCredit().setCreditRate(SMALL_CREDIT_PERCENT);
		} else {			
			this.model.getLastCredit().setDownPayment(Double.parseDouble(this.window.tfDownPayment.getText()));
			if (this.model.getLastCredit().getCreditType() == CreditType.LONG_TERM_CAR) {
				
				logger.trace("CreditType == LONG_TERM_CAR");
				
				this.model.getLastCredit().setCreditRate(CAR_CREDIT_PERCENT);
			} else {
				
				logger.trace("CreditType == LONG_TERM_HOME");
				
				this.model.getLastCredit().setCreditRate(HOME_CREDIT_PERCENT);
			}
		}
		this.model.getLastCredit().setCreditType((CreditType) this.window.cbCreditType.getSelectedItem());
		this.model.getLastCredit().setCreditSize(Double.parseDouble(this.window.tfCreditSize.getText()));
		this.model.getLastCredit().setCreditLength(Double.parseDouble(this.window.tfCreditLength.getText()));
		
		logger.trace("Returning from setToModel()");
	}
	
	private void checkValidness() {
		logger.trace("Calling checkValidness()");
		
		if (this.model.getLastCredit().getCreditType() == CreditType.SHORT_TERM) {
			logger.trace("CreditType == SHORT_TERM");
			
			double percentCost = this.model.getLastCredit().getCreditSize() * this.model.getLastCredit().getCreditRate() * (this.model.getLastCredit().getCreditLength() + 1.0) / 24.0;
			logger.debug("percentCost: ", percentCost);
			
			double sum = this.model.getLastCredit().getCreditSize() + percentCost;
			logger.debug("sum: ", sum);
			
			double netIncome = this.model.getMonthlyIncome() * 0.4; // чистый доход
			logger.debug("netIncome: ", netIncome);
			
			double allNetIncome = netIncome * this.model.getLastCredit().getCreditLength(); // чистый доход за весь период
			logger.debug("allNetIncome: ", allNetIncome);
			
			if (allNetIncome < sum) {
				
				logger.debug("allNetIncome({}) < sum({})", allNetIncome, sum);
				
				ErrorWindow error = new ErrorWindow("За " + this.model.getLastCredit().getCreditLength() + " місяців клієнт може выплатити лише " + allNetIncome + " грн. з " + sum + "грн.");
				error.setVisible(true);
				
				logger.trace("Returning from checkValidness()");
				return;
			}
			if (this.model.getCreditHistory() == CreditHistory.IS_REPAID_REGULARLY && this.model.getLastCredit().getProvision() < allNetIncome * 2.0) {
				
				logger.trace("CreditHistory == IS_REPAID_REGULARLY");
				logger.debug("provision({}) < 2*allNetIncome({})", this.model.getLastCredit().getProvision(), (allNetIncome * 2.0));
				
				ErrorWindow error = new ErrorWindow("Розмір забезпечення (" + this.model.getLastCredit().getProvision() + "грн.) повинен бути більше подвійного чистого доходу (" + allNetIncome * 2.0 + " грн.)");
				error.setVisible(true);
				
				logger.trace("Returning from checkValidness()");
				return;
			}
		} else {
			if (this.model.getLastCredit().getCreditType() == CreditType.LONG_TERM_CAR) { //машина
				
				logger.trace("CreditType == LONG_TERM_CAR");
				
				if(!calc(this.model, CAR_CREDIT_PERCENT)) {
					
					logger.trace("Returning from checkValidness()");
					return;
				}
			} else { // квартира
				
				logger.trace("CreditType == LONG_TERM_HOME");
				
				if(!calc(this.model, HOME_CREDIT_PERCENT)) {
					
					logger.trace("Returning from checkValidness()");
					return;
				}
			}
		}
		logger.trace("Returning from checkValidness()");
	}
	
	private static boolean calc(IndividualModel model, double downPaymentMinPercent) {
		logger.trace("Calling from calc({}, {})", model, downPaymentMinPercent);
		
		boolean result = true;
		if(model.getLastCredit().getDownPayment() < model.getLastCredit().getCreditSize() * downPaymentMinPercent) {
			
			logger.debug("downPayment({}) < creditSize * downPaymentMinPercent({})", model.getLastCredit().getDownPayment(), (model.getLastCredit().getCreditSize()*downPaymentMinPercent));
			
			result = false;
			ErrorWindow error = new ErrorWindow("Розмір першого взносу (" + model.getLastCredit().getDownPayment() + " грн.) повинен складати 10% від розміру кредиту (" + model.getLastCredit().getCreditSize() + " грн.)");
			error.setVisible(true);
		}
		double percentCost = (model.getLastCredit().getCreditSize() - model.getLastCredit().getDownPayment()) * model.getLastCredit().getCreditRate() * (model.getLastCredit().getCreditLength() + 1.0) / 24.0;
		logger.debug("percentCost: ", percentCost);
		
		double sum = model.getLastCredit().getCreditSize() + percentCost;
		logger.debug("sum: ", sum);
		
		if (sum < model.getLastCredit().getNetIncome()) {
			
			logger.debug("sum < netIncome");
			
			result = false;
			ErrorWindow error = new ErrorWindow("Розмір чистого доходу на місяць (" + model.getLastCredit().getDownPayment() + " грн.) повинен дорівнювати або бути більше суми повернення (" + sum + " грн.)");
			error.setVisible(true);
		}
		
		logger.trace("Returning from calc(...)");
		logger.debug("Returning: ", result);		
		return result;
	}
}

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
		logger.trace("Calling Step4IndividualController({})", model);
		logger.trace("Returning from Step4IndividualController({})", model);
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
						try {
							model = calcParams(model);
							if (checkValidness(model)) {
								window.setVisible(false);
								Step5IndividualController step5 = new Step5IndividualController(model);
								step5.init();
								window.dispose();
							}
						} catch (IllegalArgumentException e) {
							logger.warn(e.getMessage());
							ErrorWindow error = new ErrorWindow(e.getMessage());
							error.setVisible(true);
						}
					}
				});
			}
		});

		logger.trace("Returning from init()");
	}
	
	public IndividualModel calcParams(IndividualModel model) {
		if (model.getLastCredit().getCreditType() == CreditType.SHORT_TERM) {
			model.getLastCredit().setPercentCost(calcPercentCost(model.getLastCredit().getCreditSize(), model.getLastCredit().getCreditRate(), model.getLastCredit().getCreditLength()));
		} else {
			model.getLastCredit().setPercentCost(calcPercentCost(model.getLastCredit().getCreditSize(), model.getLastCredit().getDownPayment(), model.getLastCredit().getCreditRate(), model.getLastCredit().getCreditLength()));	
		}
		model.getLastCredit().setNetIncome(calcNetIncome(model.getMonthlyIncome()));
		return model;
	}

	public void setToModel(Step4IndividualWindow window) {
		logger.trace("Calling setToModel({})", window);

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

		logger.trace("Returning from setToModel({})", window);
	}

	public boolean checkValidness(IndividualModel model) throws IllegalArgumentException {
		logger.trace("Calling checkValidness({})", model);

		double creditSize = model.getLastCredit().getCreditSize();
		double creditLength = model.getLastCredit().getCreditLength();
		double provision = model.getLastCredit().getProvision();
		CreditHistory creditHistory = model.getCreditHistory();
		CreditType creditType = model.getLastCredit().getCreditType();
		double percentCost = model.getLastCredit().getPercentCost();
		double netIncome = model.getLastCredit().getNetIncome();
		
		boolean result = true;

		if (creditType == CreditType.SHORT_TERM) {
			logger.trace("CreditType == SHORT_TERM");

			checkValidnessShortTermCredit(creditSize, creditLength, provision, creditHistory, percentCost, netIncome);
		} else {
			if (creditType == CreditType.LONG_TERM_CAR) { // машина

				logger.trace("CreditType == LONG_TERM_CAR");

				if (!checkValidnessLongTermCredit(model.getLastCredit(), CAR_CREDIT_PERCENT)) {
					result = false;
				}
			} else { // квартира

				logger.trace("CreditType == LONG_TERM_HOME");

				if (!checkValidnessLongTermCredit(model.getLastCredit(), HOME_CREDIT_PERCENT)) {
					result = false;
				}
			}
		}
		logger.trace("Returning from checkValidness({})", model);
		return result;
	}

	public void checkValidnessShortTermCredit(double creditSize, double creditLength, double provision, CreditHistory creditHistory, double percentCost, double netIncome) {
		double sum = calcSum(creditSize, percentCost);
		double allNetIncome = calcAllNetIncome(creditLength, netIncome);

		if (allNetIncome < sum) {

			logger.debug("allNetIncome({}) < sum({})", allNetIncome, sum);

			throw new IllegalArgumentException("За " + creditLength + " місяців клієнт може выплатити лише "
					+ allNetIncome + " грн. з " + sum + "грн.");
		}
		if (creditHistory == CreditHistory.IS_REPAID_REGULARLY && provision < (allNetIncome * 2.0)) {

			logger.trace("CreditHistory == IS_REPAID_REGULARLY");
			logger.debug("provision({}) < 2*allNetIncome({})", provision, (allNetIncome * 2.0));

			throw new IllegalArgumentException("Розмір забезпечення (" + provision
					+ "грн.) повинен бути більше подвійного чистого доходу (" + allNetIncome * 2.0 + " грн.)");
		}
	}

	public double calcAllNetIncome(double creditLength, double netIncome) {
		double allNetIncome = netIncome * creditLength; // чистый доход за весь период
		logger.debug("allNetIncome: {}", allNetIncome);
		return allNetIncome;
	}

	public double calcNetIncome(double monthlyIncome) {
		double netIncome = monthlyIncome * 0.4; // чистый доход
		logger.debug("netIncome: {}", netIncome);
		return netIncome;
	}

	public double calcSum(double creditSize, double percentCost) {
		double sum = creditSize + percentCost;
		logger.debug("sum: {}", sum);
		return sum;
	}

	public double calcPercentCost(double creditSize, double creditRate, double creditLength) {
		double percentCost = creditSize * creditRate * (creditLength + 1.0) / 24.0;
		logger.debug("percentCost: {}", percentCost);
		return percentCost;
	}
	
	public double calcPercentCost(double creditSize, double downPayment, double creditRate, double creditLength) {
		double percentCost = (creditSize - downPayment) * creditRate * (creditLength + 1.0) / 24.0;
		logger.debug("percentCost: {}", percentCost);
		return percentCost;
	}

	public boolean checkValidnessLongTermCredit(CreditModel credit, double downPaymentMinPercent) throws IllegalArgumentException {
		logger.trace("Calling from calc({}, {})", credit, downPaymentMinPercent);

		boolean result = true;
		if (credit.getDownPayment() < credit.getCreditSize() * downPaymentMinPercent) {

			logger.debug("downPayment({}) < (creditSize * downPaymentMinPercent)({})", credit.getDownPayment(),
					(credit.getCreditSize() * downPaymentMinPercent));

			throw new IllegalArgumentException("Розмір першого взносу (" + credit.getDownPayment() + " грн.)"
					+ " повинен складати 10% від розміру кредиту (" + credit.getCreditSize() + " грн.)");
		}

		double sum = calcSum(credit.getCreditSize(), credit.getPercentCost());
		logger.debug("sum: {}", sum);
		
		double sumNetIncome = sum / credit.getCreditLength();

		if (sumNetIncome < credit.getNetIncome()) {

			logger.debug("netIncome < netIncome");

			throw new IllegalArgumentException("Розмір чистого доходу на місяць (" + credit.getNetIncome() + " грн.)"
					+ " повинен дорівнювати або бути більше щомісячної суми повернення (" +  sumNetIncome + " грн.)");
		}
		
		logger.debug("result: {}", result);

		logger.trace("Returning from calc({}, {})", credit, downPaymentMinPercent);
		return result;
	}

}

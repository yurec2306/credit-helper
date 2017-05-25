package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.ErrorWindow;
import main.LegalModel;

public class Step6LegalController extends AbstractLegalStepController {

	private Step6LegalWindow window;
	private int status = 0;
	
	public Step6LegalController(LegalModel model) {
		super(model);
	}

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step6LegalWindow();
					calc();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (isCreditReady()) {
							window.setVisible(false);
							Step7LegalController step7 = new Step7LegalController(model);
							step7.init();
							window.dispose();
						}
					}
				});
			}
		});
	}

	private void calc() {
		
		double k1 = model.getLastCredit().getCash() / 
					(model.getLastCredit().getCurrentLiabilities() - 
							model.getLastCredit().getDeferredIncome() - 
							model.getLastCredit().getReservesForFuturePayments());
		
		double k2 = (model.getLastCredit().getCash() +
						model.getLastCredit().getShortTermInvestments() + 
						model.getLastCredit().getPayableFor12Months()) / 
					(model.getLastCredit().getCurrentLiabilities() -
							model.getLastCredit().getDeferredIncome() -
							model.getLastCredit().getReservesForFuturePayments());
		
		double k3 = model.getLastCredit().getCurrentAssets() / 
					(model.getLastCredit().getCurrentLiabilities() - 
							model.getLastCredit().getDeferredIncome() - 
							model.getLastCredit().getReservesForFuturePayments());
		
		double k4 = model.getLastCredit().getCapitalAndReservesAll() / 
					(model.getLastCredit().getLongTermCommitment() -
							model.getLastCredit().getCurrentLiabilities() -
							model.getLastCredit().getDeferredIncome() - 
							model.getLastCredit().getReservesForFuturePayments());
		
		double k5 =  model.getLastCredit().getNetIncome() / model.getLastCredit().getCosts();
		
		setToModel(k1, k2, k3, k4, k5);
		
		double s = 0.11 * k1 + 0.05 * k2 + 0.42 * k3 + 0.21 * k4 + 0.21 * k5;
		
		calcStatus(s);
		
	}
	
	private void setToModel(double k1, double k2, double k3, double k4, double k5) {
		window.tfAbsoluteLiquidityRatio.setText(String.valueOf(k1));
		window.tfCriticalEvaluationFactor.setText(String.valueOf(k2));
		window.tfCurrentRatio.setText(String.valueOf(k3));
		window.tfTheRatioOfFunds.setText(String.valueOf(k4));
		window.tfProfitability.setText(String.valueOf(k5));
	}

	private void calcStatus(double s) {
		if(1 < s && s < 1.05) {
			status = 1;
		} else if (s < 2.42) {
			status = 2;
		} else {
			status = 3;
		}
	}
	
	private boolean isCreditReady() {
		boolean result = false;
		double percentCost = calcPercentCost();
		double sum = calcSum(percentCost);
		double allNetIncome = calcAllNetIncome();
		switch (status) {
		case 1:
			if(model.getLastCredit().getCreditSize() < allNetIncome) {
				result =  true;
			} else {
				ErrorWindow error = new ErrorWindow("За " + model.getLastCredit().getCreditLength() + " місяців клієнт може выплатити лише " + allNetIncome + " грн. з " + sum + " грн.");
				error.setVisible(true);
			}
		case 2:
			if(model.getLastCredit().getCreditSize() < (allNetIncome * 2)) {
				result =  true;
			} else {
				ErrorWindow error = new ErrorWindow("Розмір забезпечення (" + model.getLastCredit().getProvision() + ") повинен бути більше подвійного чистого доходу (" + allNetIncome * 2.0 + " грн.)");
				error.setVisible(true);
			}
		}
		return result;
	}

	private double calcSum(double percentCost) {
		return model.getLastCredit().getCreditSize() + percentCost;
	}

	private double calcPercentCost() {
		return model.getLastCredit().getCreditSize() * model.getLastCredit().getCreditRate() * (model.getLastCredit().getCreditLength() + 1.0) / 24.0;
	}
	
	private double calcAllNetIncome() {
		double netIncome = (model.getLastCredit().getProvision() - model.getLastCredit().getCosts()) * 0.8 * 0.8; // чистый доход
		double allNetIncome = netIncome * Math.ceil(model.getLastCredit().getCreditLength() / 12.0); // чистый доход за весь период
		return allNetIncome;
	}

}

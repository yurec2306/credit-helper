package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.CreditModel.CreditType;
import main.IndividualModel.CreditHistory;
import main.ErrorWindow;
import main.IndividualModel;

public class Step4IndividualController extends AbstractStepController {

	final static double SMALL_CREDIT_PERCENT = 0.20;
	final static double CAR_CREDIT_PERCENT = 0.10;
	final static double HOME_CREDIT_PERCENT = 0.20;
	
	private Step4IndividualWindow window;

	public Step4IndividualController(IndividualModel model) {
		super(model);
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step4IndividualWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setToModel();
						checkValidness();
						window.setVisible(false);
						Step5IndividualController step4 = new Step5IndividualController(model);
						step4.init();
						window.dispose();
					}
				});
			}
		});
	}

	private void setToModel() {
		if (window.cbCreditType.getSelectedItem() == CreditType.SHORT_TERM) {
			model.getLastCredit().setProvision(Double.parseDouble(window.tfProvision.getText()));
			model.getLastCredit().setCreditRate(SMALL_CREDIT_PERCENT);
		} else {
			model.getLastCredit().setDownPayment(Double.parseDouble(window.tfDownPayment.getText()));
			if (model.getLastCredit().getCreditType() == CreditType.LONG_TERM_CAR) {
				model.getLastCredit().setCreditRate(CAR_CREDIT_PERCENT);
			} else {
				model.getLastCredit().setCreditRate(HOME_CREDIT_PERCENT);
			}
		}
		model.getLastCredit().setCreditType((CreditType) window.cbCreditType.getSelectedItem());
		model.getLastCredit().setCreditSize(Double.parseDouble(window.tfCreditSize.getText()));
		model.getLastCredit().setCreditLength(Double.parseDouble(window.tfCreditLength.getText()));
	}
	
	private void checkValidness() {
		if (model.getLastCredit().getCreditType() == CreditType.SHORT_TERM) {
			double percentCost = model.getLastCredit().getCreditSize() * model.getLastCredit().getCreditRate() * (model.getLastCredit().getCreditLength() + 1.0) / 24.0;
			double sum = model.getLastCredit().getCreditSize() + percentCost;
			double netIncome = model.getMonthlyIncome() * 0.4; // ������ �����
			double allNetIncome = netIncome * model.getLastCredit().getCreditLength(); // ������ ����� �� ���� ������
			if (allNetIncome < sum) {
				ErrorWindow error = new ErrorWindow("�� " + model.getLastCredit().getCreditLength() + " ������ �볺�� ���� ��������� ���� " + allNetIncome + " ���. � " + sum + "���.");
				error.setVisible(true);
				return;
			}
			if (model.getCreditHistory() == CreditHistory.IS_REPAID_REGULARLY && model.getLastCredit().getProvision() < allNetIncome * 2) {
				ErrorWindow error = new ErrorWindow("����� ������������ (" + model.getLastCredit().getProvision() + "���.) ������� ���� ����� ��������� ������� ������ (" + allNetIncome * 2.0 + " ���.)");
				error.setVisible(true);
				return;
			}
		} else {
			if (model.getLastCredit().getCreditType() == CreditType.LONG_TERM_CAR) { //������
				if(!calc(model, CAR_CREDIT_PERCENT)) {
					return;
				}
			} else { // ��������
				if(!calc(model, HOME_CREDIT_PERCENT)) {
					return;
				}
			}
		}
	}
	
	private boolean calc(IndividualModel model, double downPaymentMinPercent) {
		boolean result = true;
		if(model.getLastCredit().getDownPayment() < model.getLastCredit().getCreditSize() * downPaymentMinPercent) {
			result = false;
			ErrorWindow error = new ErrorWindow("����� ������� ������ (" + model.getLastCredit().getDownPayment() + " ���.) ������� �������� 10% �� ������ ������� (" + model.getLastCredit().getCreditSize() + " ���.)");
			error.setVisible(true);
		}
		double percentCost = (model.getLastCredit().getCreditSize() - model.getLastCredit().getDownPayment()) * model.getLastCredit().getCreditRate() * (model.getLastCredit().getCreditLength() + 1.0) / 24.0;
		double sum = model.getLastCredit().getCreditSize() + percentCost;
		if (sum < model.getLastCredit().getNetIncome()) {
			result = false;
			ErrorWindow error = new ErrorWindow("����� ������� ������ �� ����� (" + model.getLastCredit().getDownPayment() + " ���.) ������� ���������� ��� ���� ����� ���� ���������� (" + sum + " ���.)");
			error.setVisible(true);
		}
		return result;
	}
}

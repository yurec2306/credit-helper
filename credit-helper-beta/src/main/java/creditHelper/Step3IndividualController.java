package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.ErrorWindow;
import main.IndividualModel;
import main.IndividualModel.CreditHistory;
import main.IndividualModel.FieldOfActivity;
import main.IndividualModel.MaritialStatus;
import main.IndividualModel.Qualification;

public class Step3IndividualController extends AbstractStepController {

	private Step3IndividualWindow window;

	public Step3IndividualController(IndividualModel model) {
		super(model);
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step3IndividualWindow();
					setToWindow(model);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (window.cbCreditHistory.getSelectedItem() == CreditHistory.BLACK_LIST) {
							new ErrorWindow("Клієнт знаходиться у чорному списку").setVisible(true);
						} else if(Integer.parseInt(window.tfAge.getText()) < 20) {
							ErrorWindow error = new ErrorWindow("Клiенту має бути 20 років!");
							error.setVisible(true);
						} else if (setToModel()) {
							
							double[] rate = count();
							
							System.out.println(rate[0] + " " + rate[1]); //!!!!
							
							if (rate[0] > rate[1] ? true : false) { // 680 / 1010
								model.setRate(rate[0]);
								window.setVisible(false);
								window.dispose();
								Step4IndividualController step4 = new Step4IndividualController(model);
								step4.init();
							} else {
								ErrorWindow error = new ErrorWindow(
										"За результатом скорингового аналізу клієнт не може отримати кредит!");
								error.setVisible(true);
							}
						}
					}
				});
			}
		});
	}

	protected void setToWindow(IndividualModel model) {
		if (model.getPhoneNumber() != null) {
			window.tfPhone.setText(model.getPhoneNumber());
			window.tfTIN.setText(model.getTIN());
			window.tfAge.setText(String.valueOf(model.getAge()));
			window.cbMaritialStatus.setSelectedItem(model.getMaritialStatus());
			window.tfChildrenNum.setText(String.valueOf(model.getChildrenNum()));
			window.cbFieldOfActivity.setSelectedItem(model.getFieldOfActivity());
			window.cbQualification.setSelectedItem(model.getQualification());
			window.tfWorkExperience.setText(String.valueOf(model.getYearsOfWorkExperience()));
			window.cbCreditHistory.setSelectedItem(model.getCreditHistory());
		}
	}

	private boolean setToModel() {
		boolean result = true;
		if (!(window.tfPhone.getText().isEmpty()        || 
			window.tfTIN.getText().isEmpty()            || 
			window.tfAge.getText().isEmpty()            || 
			window.tfChildrenNum.getText().isEmpty()    || 
			window.tfWorkExperience.getText().isEmpty())) {
			
			model.setPhoneNumber(window.tfPhone.getText());
			model.setTIN(window.tfTIN.getText());
			model.setAge(Integer.parseInt(window.tfAge.getText()));
			model.setMaritialStatus((MaritialStatus) window.cbMaritialStatus.getSelectedItem());
			model.setChildrenNum(Integer.parseInt(window.tfChildrenNum.getText()));
			model.setFieldOfActivity((FieldOfActivity) window.cbFieldOfActivity.getSelectedItem());
			model.setQualification((Qualification) window.cbQualification.getSelectedItem());
			model.setYearsOfWorkExperience(Integer.parseInt(window.tfWorkExperience.getText()));
			model.setCreditHistory((CreditHistory) window.cbCreditHistory.getSelectedItem());
			model.setMonthlyIncome(Double.parseDouble(window.tfAvgIncome.getText()));
		} else {
			result = false;
		}
		return result;
	}

	private double[] count() {
		//Neural Network work
		return new double[] {1.0, 0.0};
	}

}

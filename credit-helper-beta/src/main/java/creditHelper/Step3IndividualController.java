package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.ErrorWindow;
import main.IndividualModel;
import main.IndividualModel.CreditHistory;
import main.IndividualModel.FieldOfActivity;
import main.IndividualModel.MaritialStatus;
import main.IndividualModel.Qualification;
import neuralNetwork.NetworkHelper;
import neuralNetwork.NeuralNetwork;
import neuralNetwork.NeuralNetworkImpl;

public class Step3IndividualController extends AbstractStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step3IndividualController.class);

	private Step3IndividualWindow window;

	public Step3IndividualController(IndividualModel model) {
		super(model);
		logger.trace("Calling Step3IndividualController()");
		logger.trace("Returning from Step3IndividualController()");
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Step3IndividualController.this.window = new Step3IndividualWindow();
					setToWindow(Step3IndividualController.this.model);
					Step3IndividualController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Step3IndividualController.this.window.btnNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (Step3IndividualController.this.window.cbCreditHistory.getSelectedItem() == CreditHistory.BLACK_LIST) {
							new ErrorWindow("Клієнт знаходиться у чорному списку").setVisible(true);
						} else if(Integer.parseInt(Step3IndividualController.this.window.tfAge.getText()) < 20) {
							ErrorWindow error = new ErrorWindow("Клiенту має бути 20 років!");
							error.setVisible(true);
						} else if (setToModel()) {
							float[] rate = count();
							if (rate[0] > rate[1]) {
								
								logger.debug("rate[0] > rate[1]");
								
								Step3IndividualController.this.model.setRate(rate[0]);
								
								Step3IndividualController.this.window.setVisible(false);
								Step3IndividualController.this.window.dispose();
								Step4IndividualController step4 = new Step4IndividualController(Step3IndividualController.this.model);
								step4.init();
							} else {
								
								logger.debug("rate[0] < rate[1]");
								
								ErrorWindow error = new ErrorWindow(
										"За результатом скорингового аналізу клієнт не може отримати кредит!");
								error.setVisible(true);
							}
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

	private void setToWindow(IndividualModel model) {
		logger.trace(String.format("Calling setToWindow(%s)", model));
		
		if (model.getPhoneNumber() != null) {
			this.window.tfPhone.setText(model.getPhoneNumber());
			this.window.tfTIN.setText(model.getTIN());
			this.window.tfAge.setText(String.valueOf(model.getAge()));
			this.window.cbMaritialStatus.setSelectedItem(model.getMaritialStatus());
			this.window.tfChildrenNum.setText(String.valueOf(model.getChildrenNum()));
			this.window.cbFieldOfActivity.setSelectedItem(model.getFieldOfActivity());
			this.window.cbQualification.setSelectedItem(model.getQualification());
			this.window.tfWorkExperience.setText(String.valueOf(model.getYearsOfWorkExperience()));
			this.window.cbCreditHistory.setSelectedItem(model.getCreditHistory());
		}
		
		logger.trace(String.format("Returning from setToWindow(%s)", model));
	}

	private boolean setToModel() {
		logger.trace("Calling setToModel()");
		
		boolean result = true;
		if (!(this.window.tfPhone.getText().isEmpty()        || 
			this.window.tfTIN.getText().isEmpty()            || 
			this.window.tfAge.getText().isEmpty()            || 
			this.window.tfChildrenNum.getText().isEmpty()    || 
			this.window.tfWorkExperience.getText().isEmpty())) {
			
			logger.info("OK. All textFields aren't empty");
			
			this.model.setPhoneNumber(this.window.tfPhone.getText());
			this.model.setTIN(this.window.tfTIN.getText());
			this.model.setAge(Integer.parseInt(this.window.tfAge.getText()));
			this.model.setMaritialStatus((MaritialStatus) this.window.cbMaritialStatus.getSelectedItem());
			this.model.setChildrenNum(Integer.parseInt(this.window.tfChildrenNum.getText()));
			this.model.setFieldOfActivity((FieldOfActivity) this.window.cbFieldOfActivity.getSelectedItem());
			this.model.setQualification((Qualification) this.window.cbQualification.getSelectedItem());
			this.model.setYearsOfWorkExperience(Integer.parseInt(this.window.tfWorkExperience.getText()));
			this.model.setCreditHistory((CreditHistory) this.window.cbCreditHistory.getSelectedItem());
			this.model.setMonthlyIncome(Double.parseDouble(this.window.tfAvgIncome.getText()));
		} else {
			logger.info("Some textFields are empty");
			result = false;
		}
		logger.debug("result: ", result);
		
		logger.trace("Returning from setToModel()");
		return result;
	}

	private float[] count() {
		float[] result = null;
		NeuralNetwork nn = new NeuralNetworkImpl();
		NetworkHelper nh = new NetworkHelper();
		try {
			result = nn.run(nh.formToNeuron(model));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}

}

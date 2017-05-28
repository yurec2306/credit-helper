package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.IndividualModelDAO;
import main.CreditModel;
import main.IndividualModel;

public class Step2IndividualController extends AbstractStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step2IndividualController.class);

	private Step2IndividualWindow window;
	
	public Step2IndividualController() {
		super(new IndividualModel());
		logger.trace("Calling Step2IndividualController()");
		super.model.getCredits().add(new CreditModel());
		logger.trace("Returning from Step2IndividualController()");
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Step2IndividualController.this.window = new Step2IndividualWindow();
					Step2IndividualController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Step2IndividualController.this.window.btnNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(setToModel(Step2IndividualController.this.model)) {
							Step2IndividualController.this.window.setVisible(false);
							Step2IndividualController.this.window.dispose();
							Step3IndividualController step3 = new Step3IndividualController(Step2IndividualController.this.model);
						step3.init();
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}
	
	private boolean setToModel(IndividualModel model) {
		logger.trace(String.format("Calling setToModel(%1$s)", model));
		
		boolean result = true;
		if (
				!(
						this.window.tfFirstName.getText().isEmpty()     ||
						this.window.tfMiddleName.getText().isEmpty()    ||
						this.window.tfLastName.getText().isEmpty()
				)
				
			) {
			IndividualModel tempModel = checkClientStatus(this.window.tfLastName.getText(), this.window.tfFirstName.getText(), this.window.tfMiddleName.getText());
			if(tempModel == null) {
				model.setFirstName(this.window.tfFirstName.getText());
				model.setMiddleName(this.window.tfMiddleName.getText());
				model.setLastName(this.window.tfLastName.getText());
			} else {
				model = tempModel;
			}
		} else {
			result = false;
		}
		
		this.model = model;

		logger.trace(String.format("Returning from setToModel(%1$s)", model));
		return result;
	}

	private static IndividualModel checkClientStatus(String lastName, String firstName, String middleName) {
		
		logger.trace(String.format("Calling checkClientStatus(%1$s, %1$s, %1$s)", lastName, firstName, middleName));
		
		IndividualModel model = null;
		try(IndividualModelDAO dao = new IndividualModelDAO()) {
			model = dao.getModel(lastName, firstName, middleName);
		}
		
		logger.trace(String.format("Returning from checkClientStatus(%1$s, %1$s, %1$s)", lastName, firstName, middleName));
		return model;
	}

}

package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataBase.IndividualModelDAO;
import main.CreditModel;
import main.IndividualModel;

public class Step2IndividualController extends AbstractStepController {

	private Step2IndividualWindow window;
	
	public Step2IndividualController() {
		super(new IndividualModel());
		super.model.getCredits().add(new CreditModel());
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step2IndividualWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(setToModel()) {
							window.setVisible(false);
							window.dispose();
							Step3IndividualController step3 = new Step3IndividualController(model);
						step3.init();
						}
					}
				});
			}
		});
	}
	
	private boolean setToModel() {
		boolean result = true;
		if (
				!(
						window.tfFirstName.getText().isEmpty()     ||
						window.tfMiddleName.getText().isEmpty()    ||
						window.tfLastName.getText().isEmpty()
				)
				
			) {
			IndividualModel tempModel = checkClientStatus(window.tfLastName.getText(), window.tfFirstName.getText(), window.tfMiddleName.getText());
			if(tempModel == null) {
				model.setFirstName(window.tfFirstName.getText());
				model.setMiddleName(window.tfMiddleName.getText());
				model.setLastName(window.tfLastName.getText());
			} else {
				model = tempModel;
			}
		} else {
			result = false;
		}
		return result;
	}

	private IndividualModel checkClientStatus(String lastName, String firstName, String middleName) {
		IndividualModelDAO dao = new IndividualModelDAO();
		IndividualModel model = dao.getModel(lastName, firstName, middleName);
		dao.close();
		return model;
	}

}

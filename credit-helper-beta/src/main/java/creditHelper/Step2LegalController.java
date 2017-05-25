package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataBase.LegalModelDAO;
import main.LegalCreditModel;
import main.LegalModel;

public class Step2LegalController extends AbstractLegalStepController {

private Step2LegalWindow window;
	
	public Step2LegalController() {
		super(new LegalModel());
		super.model.getCredits().add(new LegalCreditModel());
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step2LegalWindow();
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
							Step3LegalController step3 = new Step3LegalController(model);
						step3.init();
						}
					}
				});
			}
		});
	}
	
	private boolean setToModel() {
		boolean result = true;
		if (!window.tfCompanyName.getText().isEmpty()) {
			LegalModel tempModel = checkClientStatus(window.tfCompanyName.getText());
			if(tempModel == null) {
				model.setOrganizationName(window.tfCompanyName.getText());
			} else {
				model = tempModel;
			}
		} else {
			result = false;
		}
		return result;
	}

	private LegalModel checkClientStatus(String name) {
		LegalModelDAO dao = new LegalModelDAO();
		LegalModel model = dao.getModel(name);
		dao.close();
		return model;
	}

}

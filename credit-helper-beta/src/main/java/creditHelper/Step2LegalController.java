package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.LegalModelDAO;
import main.LegalCreditModel;
import main.LegalModel;

public class Step2LegalController extends AbstractLegalStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step2LegalController.class);

	private Step2LegalWindow window;
	
	public Step2LegalController() {
		super(new LegalModel());
		logger.trace("Calling Step2LegalController()");
		super.model.getCredits().add(new LegalCreditModel());
		logger.trace("Returning from Step2LegalController()");
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
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
		
		logger.trace("Returning from init()");
	}
	
	private boolean setToModel() {
		logger.trace("Calling setToModel()");
		
		boolean result = true;
		if (!window.tfCompanyName.getText().isEmpty()) {
			LegalModel tempModel = checkClientStatus(window.tfCompanyName.getText());
			if(tempModel == null) {
				
				logger.debug("tempModel == NULL");
				
				model.setOrganizationName(window.tfCompanyName.getText());
			} else {
				model = tempModel;				
			}
			
			logger.debug("model: ", model);
			
		} else {
			result = false;
		}		
		logger.trace("Returning from setToModel()");
		return result;
	}

	private static LegalModel checkClientStatus(String name) {
		logger.trace(String.format("Calling checkClientStatus(%1$s)", name));
		
		LegalModel model = null;
		try(LegalModelDAO dao = new LegalModelDAO()) {
			model = dao.getModel(name);
		}
		
		logger.trace(String.format("Returning from checkClientStatus(%1$s)", name));
		return model;
	}

}

package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.ErrorWindow;
import main.LegalModel;
import main.IndividualModel.CreditHistory;

public class Step3LegalController extends AbstractLegalStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step3LegalController.class);

	private Step3LegalWindow window;

	public Step3LegalController(LegalModel model) {
		super(model);
		logger.trace("Creating Step3LegalController({})", model);
		logger.trace("Returning from Step3LegalController({})", model);
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Step3LegalController.this.window = new Step3LegalWindow();
					setToWindow(Step3LegalController.this.model);
					Step3LegalController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Step3LegalController.this.window.btnNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (Step3LegalController.this.window.cbCreditHistory.getSelectedItem() == CreditHistory.BLACK_LIST) {
							new ErrorWindow("Клієнт знаходиться у чорному списку").setVisible(true);
						} else if (setToModel()) {
							Step3LegalController.this.window.setVisible(false);
							Step3LegalController.this.window.dispose();
							Step4LegalController step4 = new Step4LegalController(Step3LegalController.this.model);
							step4.init();
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

	protected void setToWindow(LegalModel model) {
		logger.trace("Calling setToWindow({})", model);
		
		if (model.getOrganizationTIN() != null) {
			logger.trace("Setting to TextFields");
			
			this.window.tfOrganizationTIN.setText(model.getOrganizationTIN());
			this.window.tfEntranceReportingForm.setText(model.getEntranceReportingForm());
			this.window.tfAddress.setText(model.getAddress());
			this.window.tfBranch.setText(model.getBranch());
			this.window.tfDirector.setText(model.getDirector());
			this.window.tfDirectorPhone.setText(model.getDirectorPhone());
			this.window.tfAccountant.setText(model.getAccountant());
			this.window.tfAccountantPhone.setText(model.getAccountantPhone());
			this.window.tfContactPerson.setText(model.getContactPerson());
			this.window.tfContactPersonPhone.setText(model.getContactPersonPhone());
			this.window.tfFax.setText(model.getFax());
			this.window.tfEmail.setText(model.getEmail());
			this.window.tfNACE.setText(model.getNACE());
			this.window.tfUSREOU.setText(model.getUSREOU());
			this.window.tfKOATUU.setText(model.getKOATUU());
			model.setCreditHistory((CreditHistory) this.window.cbCreditHistory.getSelectedItem());
		}
		
		logger.trace("Returning from setToWindow({})", model);
	}

	private boolean setToModel() {
		logger.trace("Calling setToModel()");
		
		boolean result = true;
		if (!(this.window.tfOrganizationTIN.getText().isEmpty()     || 
			this.window.tfEntranceReportingForm.getText().isEmpty() || 
			this.window.tfAddress.getText().isEmpty()               || 
			this.window.tfBranch.getText().isEmpty()                ||
			this.window.tfDirector.getText().isEmpty()              ||
			this.window.tfDirectorPhone.getText().isEmpty()         ||
			this.window.tfAccountant.getText().isEmpty()            ||
			this.window.tfAccountantPhone.getText().isEmpty()       ||
			this.window.tfContactPerson.getText().isEmpty()         ||
			this.window.tfContactPersonPhone.getText().isEmpty()    ||
			this.window.tfFax.getText().isEmpty()                   ||
			this.window.tfEmail.getText().isEmpty()                 ||
			this.window.tfNACE.getText().isEmpty()                  ||
			this.window.tfUSREOU.getText().isEmpty()                ||
			this.window.tfKOATUU.getText().isEmpty())               ) {
			
			logger.trace("Setting data to model");
			
			this.model.setOrganizationTIN(this.window.tfOrganizationTIN.getText());
			this.model.setEntranceReportingForm(this.window.tfEntranceReportingForm.getText());
			this.model.setAddress(this.window.tfAddress.getText());
			this.model.setBranch(this.window.tfBranch.getText());
			this.model.setDirector(this.window.tfDirector.getText());
			this.model.setDirectorPhone(this.window.tfDirectorPhone.getText());
			this.model.setAccountant(this.window.tfAccountant.getText());
			this.model.setAccountantPhone(this.window.tfAccountantPhone.getText());
			this.model.setContactPerson(this.window.tfContactPerson.getText());
			this.model.setContactPersonPhone(this.window.tfContactPersonPhone.getText());
			this.model.setFax(this.window.tfFax.getText());
			this.model.setEmail(this.window.tfEmail.getText());
			this.model.setNACE(this.window.tfNACE.getText());
			this.model.setUSREOU(this.window.tfUSREOU.getText());
			this.model.setKOATUU(this.window.tfKOATUU.getText());
			this.model.setCreditHistory((CreditHistory) this.window.cbCreditHistory.getSelectedItem());
		} else {
			
			logger.debug("Some TextFields are empty");
			
			result = false;
		}
		logger.debug("result: {}", result);
		
		logger.trace("Returning from setToModel()");
		return result;
	}

}

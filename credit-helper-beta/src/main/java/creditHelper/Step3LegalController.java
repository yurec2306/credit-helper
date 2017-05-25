package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.ErrorWindow;
import main.LegalModel;
import main.IndividualModel.CreditHistory;


public class Step3LegalController extends AbstractLegalStepController {

	private Step3LegalWindow window;

	public Step3LegalController(LegalModel model) {
		super(model);
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step3LegalWindow();
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
						} else if (setToModel()) {
							window.setVisible(false);
							window.dispose();
							Step4LegalController step4 = new Step4LegalController(model);
							step4.init();
						}
					}
				});
			}
		});
	}

	protected void setToWindow(LegalModel model) {
		if (model.getOrganizationTIN() != null) {
			window.tfOrganizationTIN.setText(model.getOrganizationTIN());
			window.tfEntranceReportingForm.setText(model.getEntranceReportingForm());
			window.tfAddress.setText(model.getAddress());
			window.tfBranch.setText(model.getBranch());
			window.tfDirector.setText(model.getDirector());
			window.tfDirectorPhone.setText(model.getDirectorPhone());
			window.tfAccountant.setText(model.getAccountant());
			window.tfAccountantPhone.setText(model.getAccountantPhone());
			window.tfContactPerson.setText(model.getContactPerson());
			window.tfContactPersonPhone.setText(model.getContactPersonPhone());
			window.tfFax.setText(model.getFax());
			window.tfEmail.setText(model.getEmail());
			window.tfNACE.setText(model.getNACE());
			window.tfUSREOU.setText(model.getUSREOU());
			window.tfKOATUU.setText(model.getKOATUU());
			model.setCreditHistory((CreditHistory) window.cbCreditHistory.getSelectedItem());
		}
	}

	private boolean setToModel() {
		boolean result = true;
		if (!(window.tfOrganizationTIN.getText().isEmpty()     || 
			window.tfEntranceReportingForm.getText().isEmpty() || 
			window.tfAddress.getText().isEmpty()               || 
			window.tfBranch.getText().isEmpty()                ||
			window.tfDirector.getText().isEmpty()              ||
			window.tfDirectorPhone.getText().isEmpty()         ||
			window.tfAccountant.getText().isEmpty()            ||
			window.tfAccountantPhone.getText().isEmpty()       ||
			window.tfContactPerson.getText().isEmpty()         ||
			window.tfContactPersonPhone.getText().isEmpty()    ||
			window.tfFax.getText().isEmpty()                   ||
			window.tfEmail.getText().isEmpty()                 ||
			window.tfNACE.getText().isEmpty()                  ||
			window.tfUSREOU.getText().isEmpty()                ||
			window.tfKOATUU.getText().isEmpty())               ) {
			
			model.setOrganizationTIN(window.tfOrganizationTIN.getText());
			model.setEntranceReportingForm(window.tfEntranceReportingForm.getText());
			model.setAddress(window.tfAddress.getText());
			model.setBranch(window.tfBranch.getText());
			model.setDirector(window.tfDirector.getText());
			model.setDirectorPhone(window.tfDirectorPhone.getText());
			model.setAccountant(window.tfAccountant.getText());
			model.setAccountantPhone(window.tfAccountantPhone.getText());
			model.setContactPerson(window.tfContactPerson.getText());
			model.setContactPersonPhone(window.tfContactPersonPhone.getText());
			model.setFax(window.tfFax.getText());
			model.setEmail(window.tfEmail.getText());
			model.setNACE(window.tfNACE.getText());
			model.setUSREOU(window.tfUSREOU.getText());
			model.setKOATUU(window.tfKOATUU.getText());
			model.setCreditHistory((CreditHistory) window.cbCreditHistory.getSelectedItem());
		} else {
			result = false;
		}
		return result;
	}

}

package main.profileControl;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.LegalModelDAO;
import main.CreditModel.CreditStatus;
import main.LegalModel;

public class LegalProfileController {
	
	private static Logger logger = LoggerFactory.getLogger(LegalProfileController.class);
	
	private LegalModel model;
	private LegalProfileWindow window;
	
	public LegalProfileController(LegalModel model) {
		logger.trace("Calling LegalProfileController({})", model);
		this.model = model;
		logger.trace("Returning from LegalProfileController({})", model);
	}
	
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new LegalProfileWindow();
					modelToWindow(model, window);
					window.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setToModel(window);
						try(LegalModelDAO dao = new LegalModelDAO()) {
							dao.saveOrUpdate(model);
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}
	
	private void modelToWindow(LegalModel model, LegalProfileWindow window) {
		logger.trace("Calling modelToWindow({}, {})", model, window);
		
		window.lblNewLabel.setText(model.getOrganizationName());
		window.lblNewLabel_1.setText(model.getOrganizationTIN());
		window.lblNewLabel_2.setText(model.getEntranceReportingForm());
		window.lblNewLabel_3.setText(model.getAddress());
		window.lblNewLabel_4.setText(model.getBranch());
		window.lblNewLabel_5.setText(model.getDirector());
		window.lblNewLabel_6.setText(model.getDirectorPhone());
		window.lblNewLabel_7.setText(model.getAccountant());
		window.lblNewLabel_8.setText(model.getAccountantPhone());
		window.lblNewLabel_9.setText(model.getContactPerson());
		window.lblNewLabel_10.setText(model.getContactPersonPhone());
		window.lblNewLabel_11.setText(model.getFax());
		window.lblNewLabel_13.setText(model.getEmail());
		window.lblNewLabel_14.setText(model.getNACE());
		window.lblNewLabel_15.setText(model.getUSREOU());
		window.lblNewLabel_16.setText(model.getKOATUU());
		window.lblNewLabel_17.setText(model.getCreditHistory().toString());
		for (int i = 0; i < model.getCredits().size(); i++) {
			window.table.getModel().setValueAt(model.getCredits().get(i).getCreditSize(), i, 0);
			window.table.getModel().setValueAt(model.getCredits().get(i).getCreditLength(), i, 1);
			window.table.getModel().setValueAt(new JCreditStatusComboBox(model.getCredits().get(i).getCreditStatus()), i, 2);
		}
		
		logger.trace("Returning from modelToWindow({}, {})", model, window);
	}
	
	private void setToModel(LegalProfileWindow window) {
		logger.trace("Calling setToModel({})", window);
		
		for (int i = 0; i < model.getCredits().size(); i++) {
			model.getCredits().get(i).setCreditSize((double) window.table.getModel().getValueAt(i, 0));
			model.getCredits().get(i).setCreditLength((double) window.table.getModel().getValueAt(i, 1));
			window.table.getModel().setValueAt(new JCreditStatusComboBox(model.getCredits().get(i).getCreditStatus()), i, 2);
			model.getCredits().get(i).setCreditStatus((CreditStatus) ( (JCreditStatusComboBox) window.table.getModel().getValueAt(i, 2)).getSelectedItem());
		}
		logger.debug("model: ", model);
		
		logger.trace("Returning from setToModel({})", window);
	}

}

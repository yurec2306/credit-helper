package main.profileControl;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.IndividualModelDAO;
import main.CreditModel.CreditStatus;
import main.IndividualModel;

public class IndividualProfileController {
	
	private static Logger logger = LoggerFactory.getLogger(IndividualProfileController.class);
	
	private IndividualModel model;
	private IndividualProfileWindow window;
	
	public IndividualProfileController(IndividualModel model) {
		logger.trace("Calling IndividualProfileController({})", model);
		this.model = model;
		logger.trace("Returning from IndividualProfileController({})", model);
	}
	
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					IndividualProfileController.this.window = new IndividualProfileWindow();
					modelToWindow(IndividualProfileController.this.model, IndividualProfileController.this.window);
					IndividualProfileController.this.window.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				IndividualProfileController.this.window.btnSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setToModel(IndividualProfileController.this.window);
						try(IndividualModelDAO dao = new IndividualModelDAO()) {
							dao.saveOrUpdate(IndividualProfileController.this.model);
						}
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}
	
	private void modelToWindow(IndividualModel model, IndividualProfileWindow window) {
		logger.trace("Calling modelToWindow({}, {})", model, window);
		
		window.lblNewLabel.setText(model.getLastName());
		window.lblNewLabel_1.setText(model.getFirstName());
		window.lblNewLabel_2.setText(model.getMiddleName());
		window.lblNewLabel_3.setText(model.getPhoneNumber());
		window.lblNewLabel_4.setText(model.getTIN());
		window.lblNewLabel_5.setText(String.valueOf(model.getAge()));
		window.lblNewLabel_6.setText(model.getMaritialStatus().toString());
		window.lblNewLabel_7.setText(String.valueOf(model.getChildrenNum()));
		window.lblNewLabel_8.setText(model.getFieldOfActivity().toString());
		window.lblNewLabel_9.setText(model.getQualification().toString());
		window.lblNewLabel_10.setText(String.valueOf(model.getYearsOfWorkExperience()));
		window.lblNewLabel_11.setText(model.getCreditHistory().toString());
		for (int i = 0; i < this.model.getCredits().size(); i++) {
			window.table.getModel().setValueAt(model.getCredits().get(i).getCreditSize(), i, 0);
			window.table.getModel().setValueAt(model.getCredits().get(i).getCreditLength(), i, 1);
			window.table.getModel().setValueAt(new JCreditStatusComboBox(this.model.getCredits().get(i).getCreditStatus()), i, 2);
		}
		
		logger.trace("Returning from modelToWindow({}, {})", model, window);
	}
	
	private void setToModel(IndividualProfileWindow window) {
		logger.trace("Calling setToModel({})", window);
		
		for (int i = 0; i < this.model.getCredits().size(); i++) {
			this.model.getCredits().get(i).setCreditSize((double) window.table.getModel().getValueAt(i, 0));
			this.model.getCredits().get(i).setCreditLength((double) window.table.getModel().getValueAt(i, 1));
			window.table.getModel().setValueAt(new JCreditStatusComboBox(this.model.getCredits().get(i).getCreditStatus()), i, 2);
			this.model.getCredits().get(i).setCreditStatus((CreditStatus) ( (JCreditStatusComboBox) window.table.getModel().getValueAt(i, 2)).getSelectedItem());
		}
		logger.debug("model: ", this.model);
		
		logger.trace("Returning from setToModel({})", window);
	}

}

package main.profileControl;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataBase.IndividualModelDAO;
import main.CreditModel.CreditStatus;
import main.IndividualModel;

public class IndividualProfileController {
	
	private IndividualModel model;
	private IndividualProfileWindow window;
	
	public IndividualProfileController(IndividualModel model) {
		this.model = model;
	}
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new IndividualProfileWindow();
					modelToWindow();
					window.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						toModel();
						try(IndividualModelDAO dao = new IndividualModelDAO()) {
							dao.saveOrUpdate(model);
						} catch (Exception e2) {
						}
					}
				});
			}
		});
	}
	
	private void modelToWindow() {
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
		for (int i = 0; i < model.getCredits().size(); i++) {
			window.table.getModel().setValueAt(model.getCredits().get(i).getCreditSize(), i, 0);
			window.table.getModel().setValueAt(model.getCredits().get(i).getCreditLength(), i, 1);
			window.table.getModel().setValueAt(new JCreditStatusComboBox(model.getCredits().get(i).getCreditStatus()), i, 2);
		}
		
	}
	
	private void toModel() {
		for (int i = 0; i < model.getCredits().size(); i++) {
			model.getCredits().get(i).setCreditSize((double) window.table.getModel().getValueAt(i, 0));
			model.getCredits().get(i).setCreditLength((double) window.table.getModel().getValueAt(i, 1));
			window.table.getModel().setValueAt(new JCreditStatusComboBox(model.getCredits().get(i).getCreditStatus()), i, 2);
			model.getCredits().get(i).setCreditStatus((CreditStatus) ( (JCreditStatusComboBox) window.table.getModel().getValueAt(i, 2)).getSelectedItem());
		}
	}

}

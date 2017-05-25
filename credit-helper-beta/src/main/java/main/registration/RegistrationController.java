package main.registration;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataBase.AccountDAO;
import main.ErrorWindow;
import main.accounts.AccountModel;
import main.accounts.AccountModel.UserType;

public class RegistrationController {
	
	private RegistrationWindow window;
	private AccountModel model;
	
	public RegistrationController() {
		model = new AccountModel();
	}
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new RegistrationWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(fieldsIsEmpty()) {
							setToModel(model);
							try(AccountDAO dao = new AccountDAO()) {
								dao.saveOrUpdate(model);
							} catch (Exception e) {
							}
							window.setVisible(false);
							window.dispose();
							ErrorWindow success = new ErrorWindow("Регістрація пройшла успішно. Вітаємо у системі");
							success.setVisible(true);
						}
					}
				});
			}
		});
	}
	
	private boolean fieldsIsEmpty() {
		if (!(window.tfFirstName.getText().isEmpty()    ||
				window.tfMiddleName.getText().isEmpty() ||
				window.tfLastName.getText().isEmpty()   ||
				window.tfPhone.getText().isEmpty()      ||
				window.tfBunkNumber.getText().isEmpty() ||
				window.tfLogin.getText().isEmpty()      ||
				window.tfPassword.getPassword().length == 0)) {
			return true;
		}
		return false;
	}
	
	private void setToModel(AccountModel model) {
		model.setFirstName(window.tfFirstName.getText());
		model.setLastName(window.tfLastName.getText());
		model.setMiddleName(window.tfMiddleName.getText());
		model.setPhone(window.tfPhone.getText());
		model.setBankNumber(window.tfBunkNumber.getText());
		model.setLogin(window.tfLogin.getText());
		model.setPassword(window.tfPassword.getPassword());
		model.setUserType(UserType.USER);
	}

}

package main.registration;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.AccountDAO;
import main.ErrorWindow;
import main.accounts.AccountModel;
import main.accounts.AccountModel.UserType;

public class RegistrationController {
	
	private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	private RegistrationWindow window;
	private AccountModel model;
	
	public RegistrationController() {
		logger.trace("Calling RegistrationController()");
		this.model = new AccountModel();
		logger.trace("Returning from RegistrationController()");
	}
	
	public void init() {
		logger.trace("Calling init()");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegistrationController.this.window = new RegistrationWindow();
					RegistrationController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				RegistrationController.this.window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(areFieldsEmpty(RegistrationController.this.window)) {
							setToModel(RegistrationController.this.window);
							try(AccountDAO dao = new AccountDAO()) {
								dao.saveOrUpdate(RegistrationController.this.model);
							}
							RegistrationController.this.window.setVisible(false);
							RegistrationController.this.window.dispose();
							ErrorWindow success = new ErrorWindow("Регістрація пройшла успішно. Вітаємо у системі");
							success.setVisible(true);
						}
					}
				});
			}
		});
	}
	
	private boolean areFieldsEmpty(RegistrationWindow window) {
		logger.trace("Calling areFieldsEmpty()");
		
		boolean result = false;
		if (!(window.tfFirstName.getText().isEmpty()    ||
				window.tfMiddleName.getText().isEmpty() ||
				window.tfLastName.getText().isEmpty()   ||
				window.tfPhone.getText().isEmpty()      ||
				window.tfBunkNumber.getText().isEmpty() ||
				window.tfLogin.getText().isEmpty()      ||
				window.tfPassword.getPassword().length == 0)) {
			result = true;
		}
		
		logger.debug("result: ", result);
		
		logger.trace("Returning from areFieldsEmpty()");
		return result;
	}
	
	private void setToModel(RegistrationWindow window) {
		logger.trace("Calling setToModel({})", window);
		
		this.model.setFirstName(window.tfFirstName.getText());
		this.model.setLastName(window.tfLastName.getText());
		this.model.setMiddleName(window.tfMiddleName.getText());
		this.model.setPhone(window.tfPhone.getText());
		this.model.setBankNumber(window.tfBunkNumber.getText());
		this.model.setLogin(window.tfLogin.getText());
		this.model.setPassword(window.tfPassword.getPassword());
		this.model.setUserType(UserType.USER);
		
		logger.trace("Returning from setToModel({})", window);
	}

}

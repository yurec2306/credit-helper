package main.accounts;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.AccountDAO;
import main.accounts.AccountModel.UserType;

public class AccountInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountInfoController.class);
	
	private AccountModel model;
	private AccountInfoWindow window;
	
	public AccountInfoController(AccountModel model) {
		logger.trace("Calling AccountInfoController({})", model);
		this.model = model;
		logger.trace("Returning from AccountInfoController({})", model);
	}

	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new AccountInfoWindow();
					window.btnNext.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							setToModel(model);
							try(AccountDAO dao = new AccountDAO()) {
								dao.saveOrUpdate(model);
							}
							window.setVisible(false);
							window.dispose();
						}
					});
					setToWindow(model);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		logger.trace("Returning from init()");
	}
	
	private void setToWindow(AccountModel model) {
		logger.trace("Calling setToWindow({})", model);
		
		this.window.tfFirstName.setText(model.getFirstName());
		this.window.tfLastName.setText(model.getLastName());
		this.window.tfMiddleName.setText(model.getMiddleName());
		this.window.tfPhone.setText(model.getPhone());
		this.window.tfBunkNumber.setText(model.getBankNumber());
		this.window.tfLogin.setText(model.getLogin());
		//window.tfPassword.setText(model.getPassword()); //пароль можно изменить, но не показать
		this.window.tfEmail.setText(model.getEmail());
		this.window.cbUserType.setSelectedItem(model.getUserType());
		
		logger.trace("Returning from setToWindow({})", model);
	}
	
	private void setToModel(AccountModel model) {
		logger.trace("Calling setToModel({})", model);
		
		model.setFirstName(this.window.tfFirstName.getText());
		model.setLastName(this.window.tfLastName.getText());
		model.setMiddleName(this.window.tfMiddleName.getText());
		model.setPhone(this.window.tfPhone.getText());
		model.setBankNumber(this.window.tfBunkNumber.getText());
		model.setLogin(this.window.tfLogin.getText());
		model.setPassword(this.window.tfPassword.getPassword());
		model.setEmail(this.window.tfEmail.getText());
		model.setUserType((UserType) this.window.cbUserType.getSelectedItem());
		
		this.model = model;
		
		logger.trace("Returning from setToModel({})", model);
	}

}

package main.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.AccountDAO;
import main.MainWindow;
import main.accounts.AccountModel;
import main.registration.RegistrationController;

public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private LoginWindow window;
	private AccountModel model;
	
	public LoginController() {
		logger.trace("Calling LoginController()");
		logger.trace("Returning from LoginController()");
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {					
					LoginController.this.window = new LoginWindow();
					LoginController.this.window.setVisible(true);
				} catch (Exception e) {
					
					logger.error(e.getMessage());
					
				}
				LoginController.this.window.lblRegistration.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {			
						new RegistrationController().init();
					}
				});
				LoginController.this.window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(checkAccountData(LoginController.this.window.tfLogin.getText(), LoginController.this.window.pfPassword.getPassword())) {
							LoginController.this.window.setVisible(false);
							LoginController.this.window.dispose();
							MainWindow mainWindow = new MainWindow(LoginController.this.model);
							mainWindow.setVisible(true);
						} else {
							LoginController.this.window.tfLogin.setText("");
							LoginController.this.window.pfPassword.setText("");
							LoginController.this.window.lblError.setVisible(true);
						}
					}
				});
			}
		});
	}
	
	private boolean checkAccountData(String login, char[] password) {
		logger.trace("Calling checkAccountData({}, {})", login, password);
		
		boolean result = false;
		try(AccountDAO dao = new AccountDAO()) {
			this.model = dao.getAccount(login, password);
		}
		logger.debug("Receiving: {}", this.model);
		if (this.model == null) {
			logger.trace("Returning from checkAccountData({}, {})", login, password);
			return result;
		}
		if(this.model.getLogin().equals(login) && String.valueOf(this.model.getPassword()).equals(String.valueOf(password))) {
			result = true;
		}
		logger.debug("result: {}", result);
		
		logger.trace("Returning from checkAccountData({}, {})", login, password);
		return result;
	}
}

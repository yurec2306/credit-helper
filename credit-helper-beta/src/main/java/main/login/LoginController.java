package main.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

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
					window = new LoginWindow();
					window.setVisible(true);
				} catch (Exception e) {
					
					logger.error(e.getMessage());
					
				}
				window.lblRegistration.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {			
						new RegistrationController().init();
					}
				});
				window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(checkAccountData(window.tfLogin.getText(), window.pfPassword.getPassword())) {
							window.setVisible(false);
							window.dispose();
							MainWindow mainWindow = new MainWindow(model);
							mainWindow.setVisible(true);
						} else {
							window.tfLogin.setText("");
							window.pfPassword.setText("");
							window.lblError.setVisible(true);
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
		if(this.model.getLogin().equals(login) && Arrays.equals(this.model.getPassword(), password)) {
			result = true;
		}
		logger.debug("result: {}", result);
		
		logger.trace("Returning from checkAccountData({}, {})", login, password);
		return result;
	}
}

package main.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dataBase.AccountDAO;
import main.accounts.AccountModel;
import main.registration.RegistrationController;

public class LoginController {
	
	private LoginWindow window;
	private AccountModel model;
	

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.lblRegistration.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						new RegistrationController().init();
					}
				});
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(checkAccountData(window.tfLogin.getText(), window.pfPassword.getPassword())) {
							window.setVisible(false);
							window.dispose();
							//go to main menu
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
		boolean result = false;
		try(AccountDAO dao = new AccountDAO()) {
			model = dao.getAccount(login, password);
		} catch (Exception e) {
		}
		if (model == null) {
			return result;
		}
		if(model.getLogin().equals(login) && String.valueOf(model.getPassword()).equals(String.valueOf(password))) {
			result = true;
		}
		return result;
	}
}

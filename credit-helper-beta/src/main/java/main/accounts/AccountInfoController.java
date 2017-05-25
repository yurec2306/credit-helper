package main.accounts;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

import dataBase.AccountDAO;
import main.accounts.AccountModel.UserType;

public class AccountInfoController {
	
	private AccountModel model;
	private AccountInfoWindow window;
	private boolean isAccountInfoChanged = false;
	
	public AccountInfoController(AccountModel model) {
		this.model = model;
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new AccountInfoWindow();
					window.tfLastName.addFocusListener(new FocusAdapter() {
						private String text;
						@Override
						public void focusGained(FocusEvent e) {
							text = ((JTextField) e.getSource()).getText();
						}
						@Override
						public void focusLost(FocusEvent e) {
							if (!((JTextField) e.getSource()).getText().equals(text)) {
								isAccountInfoChanged = true;
							}
						}
					});
					window.btnNext.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (!isAccountInfoChanged)
								return;
							setToModel();
							try(AccountDAO dao = new AccountDAO()) {
								dao.saveOrUpdate(model);
							} catch (Exception e) {
							}
							window.setVisible(false);
							window.dispose();
						}
					});
					setToWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setToWindow() {
		window.tfFirstName.setText(model.getFirstName());
		window.tfLastName.setText(model.getLastName());
		window.tfMiddleName.setText(model.getMiddleName());
		window.tfPhone.setText(model.getPhone());
		window.tfBunkNumber.setText(model.getBankNumber());
		window.tfLogin.setText(model.getLogin());
		//window.tfPassword.setText(model.getPassword()); //пароль можно изменить, но не показать
		window.tfEmail.setText(model.getEmail());
		window.cbUserType.setSelectedItem(model.getUserType());
	}
	
	private void setToModel() {
		model.setFirstName(window.tfFirstName.getText());
		model.setLastName(window.tfLastName.getText());
		model.setMiddleName(window.tfMiddleName.getText());
		model.setPhone(window.tfPhone.getText());
		model.setBankNumber(window.tfBunkNumber.getText());
		model.setLogin(window.tfLogin.getText());
		model.setPassword(window.tfPassword.getPassword());
		model.setEmail(window.tfEmail.getText());
		model.setUserType((UserType) window.cbUserType.getSelectedItem());
	}

}

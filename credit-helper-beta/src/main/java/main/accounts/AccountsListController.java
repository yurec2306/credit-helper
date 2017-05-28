package main.accounts;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.AccountDAO;
import main.registration.RegistrationController;

public class AccountsListController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountsListController.class);
	
	private final static int TABLE_ROWS_NUM = 5;
	
	private AccountsListWindow window;
	private AccountsListModel model;
	
	public AccountsListController() {
		logger.trace("Calling AccountsListController()");
		this.model = new AccountsListModel();
		logger.trace("Returning from AccountsListController()");
	}

	public void init() {
		logger.trace("Calling init()");
		
		loadAccountsList();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AccountsListController.this.window = new AccountsListWindow();
					
					AccountsListController.this.window.table.setModel(new DefaultTableModel(loadObjects(),
							new String[] {
								"\u2116", "\u041F\u0406\u0411", "\u041B\u043E\u0433\u0456\u043D \u0443 \u0441\u0438\u0441\u0442\u0435\u043C\u0456", "\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438", "\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438"
							}
						) {
							Class[] columnTypes = new Class[] {
								Integer.class, AccountModel.class, String.class, Object.class, Object.class
							};
							@Override
							public Class getColumnClass(int columnIndex) {
								return this.columnTypes[columnIndex];
							}
						});
					AccountsListController.this.window.table.getColumnModel().getColumn(0).setResizable(false);
					AccountsListController.this.window.table.getColumnModel().getColumn(0).setPreferredWidth(40);
					AccountsListController.this.window.table.getColumnModel().getColumn(1).setPreferredWidth(200);
					AccountsListController.this.window.table.getColumnModel().getColumn(2).setPreferredWidth(100);
					
					AccountsListController.this.window.btnUpdate = new ButtonColumn(AccountsListController.this.window.table, new Action() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new AccountInfoController((AccountModel) AccountsListController.this.window.table.getModel().getValueAt(AccountsListController.this.window.table.getSelectedRow(), 1)).init();		
						}
						
						@Override
						public void setEnabled(boolean b) {}
						
						@Override
						public void removePropertyChangeListener(PropertyChangeListener listener) {}
						
						@Override
						public void putValue(String key, Object value) {}
						
						@Override
						public boolean isEnabled() { return false; }
						
						@Override
						public Object getValue(String key) { return null; }
						
						@Override
						public void addPropertyChangeListener(PropertyChangeListener listener) {}
					}, 4);
					
					AccountsListController.this.window.btnDelete = new ButtonColumn(AccountsListController.this.window.table, new Action() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try(AccountDAO dao = new AccountDAO()) {
								dao.delete((AccountModel) AccountsListController.this.window.table.getModel().getValueAt(AccountsListController.this.window.table.getSelectedRow(), 1));
							}
						}
						
						@Override
						public void setEnabled(boolean b) {}
						
						@Override
						public void removePropertyChangeListener(PropertyChangeListener listener) {}
						
						@Override
						public void putValue(String key, Object value) {}
						
						@Override
						public boolean isEnabled() { return false; }
						
						@Override
						public Object getValue(String key) { return null; }
						
						@Override
						public void addPropertyChangeListener(PropertyChangeListener listener) { }
					}, 3);
					
					AccountsListController.this.window.btnCreateNewAccount.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new RegistrationController().init();
							AccountsListController.this.window.setVisible(false);
							AccountsListController.this.window.dispose();
						}
					});
					
					AccountsListController.this.window.btnBack.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							AccountsListController.this.window.setVisible(false);
							AccountsListController.this.window.dispose();
						}
					});
					
					AccountsListController.this.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
		
		logger.trace("Returning from init()");
	}

	private void loadAccountsList() {
		logger.trace("Calling loadAccountsList()");
		
		try(AccountDAO dao = new AccountDAO()) {
			this.model.setAccountsList(dao.getAllAccounts());
		}
		logger.debug("AccountsList: ", this.model.getAccountsList());	
		logger.trace("Returning from loadAccountsList()");
	}
	
	private Object[][] loadObjects() {
		logger.trace("Calling loadObjects()");
		
		ArrayList<AccountModel> accountList = this.model.getAccountsList();
		Object[][] objects = new Object[accountList.size()][TABLE_ROWS_NUM];
		for(int i = 0; i < accountList.size(); i++) {
			objects[i][0] = i;
			objects[i][1] = accountList.get(i);
			//objects[i][1] = (accountList.get(i).getLastName() + " " + accountList.get(i).getFirstName() + " " + accountList.get(i).getMiddleName());
			objects[i][2] = accountList.get(i).getLogin();
		}
		
		logger.trace("Returning from loadObjects()");
		return objects;
	}
	
}

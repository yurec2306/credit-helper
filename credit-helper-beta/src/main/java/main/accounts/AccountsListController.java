package main.accounts;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;

import dataBase.AccountDAO;
import main.registration.RegistrationController;

public class AccountsListController {
	
	private final static int TABLE_ROWS_NUM = 5;
	
	private AccountsListWindow window;
	private AccountsListModel model;
	
	public AccountsListController() {
		model = new AccountsListModel();
	}

	public void init() {
		loadAccountsList();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new AccountsListWindow();
					
					window.table.setModel(new DefaultTableModel(loadObjects(),
							new String[] {
								"\u2116", "\u041F\u0406\u0411", "\u041B\u043E\u0433\u0456\u043D \u0443 \u0441\u0438\u0441\u0442\u0435\u043C\u0456", "\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438", "\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438"
							}
						) {
							Class[] columnTypes = new Class[] {
								Integer.class, AccountModel.class, String.class, Object.class, Object.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
					window.table.getColumnModel().getColumn(0).setResizable(false);
					window.table.getColumnModel().getColumn(0).setPreferredWidth(40);
					window.table.getColumnModel().getColumn(1).setPreferredWidth(200);
					window.table.getColumnModel().getColumn(2).setPreferredWidth(100);
					
					window.btnUpdate = new ButtonColumn(window.table, new Action() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new AccountInfoController((AccountModel) window.table.getModel().getValueAt(window.table.getSelectedRow(), 1)).init();		
						}
						
						@Override
						public void setEnabled(boolean b) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void removePropertyChangeListener(PropertyChangeListener listener) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void putValue(String key, Object value) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public boolean isEnabled() {
							// TODO Auto-generated method stub
							return false;
						}
						
						@Override
						public Object getValue(String key) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public void addPropertyChangeListener(PropertyChangeListener listener) {
							// TODO Auto-generated method stub
							
						}
					}, 4);
					
					window.btnDelete = new ButtonColumn(window.table, new Action() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try(AccountDAO dao = new AccountDAO()) {
								dao.delete((AccountModel) window.table.getModel().getValueAt(window.table.getSelectedRow(), 1));
							} catch (Exception e) {
							}
						}
						
						@Override
						public void setEnabled(boolean b) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void removePropertyChangeListener(PropertyChangeListener listener) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void putValue(String key, Object value) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public boolean isEnabled() {
							// TODO Auto-generated method stub
							return false;
						}
						
						@Override
						public Object getValue(String key) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public void addPropertyChangeListener(PropertyChangeListener listener) {
							// TODO Auto-generated method stub
							
						}
					}, 3);
					
					window.btnCreateNewAccount.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new RegistrationController().init();
							window.setVisible(false);
							window.dispose();
						}
					});
					
					window.btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							window.setVisible(false);
							window.dispose();
						}
					});
					
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	

	}

	private void loadAccountsList() {
		try(AccountDAO dao = new AccountDAO()) {
			model.setAccountsList(dao.getAllAccounts());
		} catch (Exception e) {
		}
	}
	
	private Object[][] loadObjects() {
		ArrayList<AccountModel> accountList = model.getAccountsList();
		Object[][] objects = new Object[accountList.size()][TABLE_ROWS_NUM];
		for(int i = 0; i < accountList.size(); i++) {
			objects[i][0] = i;
			objects[i][1] = accountList.get(i);
			//objects[i][1] = (accountList.get(i).getLastName() + " " + accountList.get(i).getFirstName() + " " + accountList.get(i).getMiddleName());
			objects[i][2] = accountList.get(i).getLogin();
		}
		return objects;
	}
	
}

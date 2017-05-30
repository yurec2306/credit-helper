package main.profileControl;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.LegalModelDAO;
import main.CreditModel.CreditStatus;
import main.CreditModel.CreditType;
import main.accounts.ButtonColumn;
import main.LegalModel;

public class LegalProfileController {
	
	private static Logger logger = LoggerFactory.getLogger(LegalProfileController.class);
	
	private final static int TABLE_ROWS_NUM = 7;
	
	private LegalModel model;
	private LegalProfileWindow window;
	
	public LegalProfileController(LegalModel model) {
		logger.trace("Calling LegalProfileController({})", model);
		this.model = model;
		logger.trace("Returning from LegalProfileController({})", model);
	}
	
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new LegalProfileWindow();
					modelToWindow(model, window);
					window.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try(LegalModelDAO dao = new LegalModelDAO()) {
							dao.saveOrUpdate(model);
						}
					}
				});
				window.table.setModel(new DefaultTableModel(loadObjects(),
						new String[] {
							"\u2116",
							"Тип кредиту",
							"Розмiр кредиту",
							"Термiн кредиту",
							"Статус кредиту",
							"Кредит виплачено",
							"Кредит не виплачено"
						}
					) {
						private static final long serialVersionUID = 1L;
						Class[] columnTypes = new Class[] {
							Integer.class, CreditType.class, Double.class, Double.class, CreditStatus.class, Object.class, Object.class
						};
						@Override
						public Class getColumnClass(int columnIndex) {
							return this.columnTypes[columnIndex];
						}
					});
				window.table.getColumnModel().getColumn(0).setResizable(false);
				window.table.getColumnModel().getColumn(0).setPreferredWidth(70);
				window.table.getColumnModel().getColumn(1).setPreferredWidth(70);
				window.table.getColumnModel().getColumn(2).setPreferredWidth(70);
				window.table.getColumnModel().getColumn(3).setPreferredWidth(70);
				window.table.getColumnModel().getColumn(4).setPreferredWidth(70);
				window.table.getColumnModel().getColumn(5).setPreferredWidth(70);
				window.table.getColumnModel().getColumn(6).setPreferredWidth(70);
				
				window.btnReturn = new ButtonColumn(window.table, new Action() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						model.getCredits().get(window.table.getSelectedRow()).setCreditStatus(CreditStatus.RETURNED);
						window.table.getModel().setValueAt(CreditStatus.RETURNED, window.table.getSelectedRow(), 4);
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
				}, 5);
				
				window.btnNotReturn = new ButtonColumn(window.table, new Action() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						model.getCredits().get(window.table.getSelectedRow()).setCreditStatus(CreditStatus.NOT_RETURNED);
						window.table.getModel().setValueAt(CreditStatus.NOT_RETURNED, window.table.getSelectedRow(), 4);
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
				}, 6);
			}
		});
		
		logger.trace("Returning from init()");
	}
	
	private void modelToWindow(LegalModel model, LegalProfileWindow window) {
		logger.trace("Calling modelToWindow({}, {})", model, window);
		
		window.lblNewLabel.setText(model.getOrganizationName());
		window.lblNewLabel_1.setText(model.getOrganizationTIN());
		window.lblNewLabel_2.setText(model.getEntranceReportingForm());
		window.lblNewLabel_3.setText(model.getAddress());
		window.lblNewLabel_4.setText(model.getBranch());
		window.lblNewLabel_5.setText(model.getDirector());
		window.lblNewLabel_6.setText(model.getDirectorPhone());
		window.lblNewLabel_7.setText(model.getAccountant());
		window.lblNewLabel_8.setText(model.getAccountantPhone());
		window.lblNewLabel_9.setText(model.getContactPerson());
		window.lblNewLabel_10.setText(model.getContactPersonPhone());
		window.lblNewLabel_11.setText(model.getFax());
		window.lblNewLabel_13.setText(model.getEmail());
		window.lblNewLabel_14.setText(model.getNACE());
		window.lblNewLabel_15.setText(model.getUSREOU());
		window.lblNewLabel_16.setText(model.getKOATUU());
		window.lblNewLabel_17.setText(model.getCreditHistory().toString());
		
		logger.trace("Returning from modelToWindow({}, {})", model, window);
	}
	
	private Object[][] loadObjects() {
		logger.trace("Calling loadObjects()");
		
		Object[][] objects = new Object[this.model.getCredits().size()][TABLE_ROWS_NUM];
		for(int i = 0; i < this.model.getCredits().size(); i++) {
			objects[i][0] = i;
			objects[i][1] = this.model.getCredits().get(i).getCreditType();
			objects[i][2] = this.model.getCredits().get(i).getCreditSize();
			objects[i][3] = this.model.getCredits().get(i).getCreditLength();
			objects[i][4] = this.model.getCredits().get(i).getCreditStatus();
			
			logger.debug("objects[{}]: {}", i, objects[i]);
		}
		logger.trace("Returning from loadObjects()");
		return objects;
	}

}

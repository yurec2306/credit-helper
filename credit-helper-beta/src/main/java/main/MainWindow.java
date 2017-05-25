package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import creditHelper.Step1Controller;
import main.accounts.AccountModel;
import main.accounts.AccountModel.UserType;
import main.accounts.AccountsListController;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField tfIndividSearch;
	private JTextField tfLegalSearch;

	public MainWindow(AccountModel model) {		
		setMinimumSize(new Dimension(315, 450));
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 330, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnFile = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(mnFile);
		
		JMenuItem mntmProperties = new JMenuItem("\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u0432\u0430\u043D\u043D\u044F");
		mntmProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open property window
			}
		});
		mnFile.add(mntmProperties);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("\u0412\u0438\u0439\u0442\u0438");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JLabel lblMainMenu = new JLabel("\u041E\u0431\u0435\u0440\u0456\u0442\u044C \u043E\u043F\u0435\u0440\u0430\u0446\u0456\u044E \u0443 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0456");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnNewCredit = new JButton("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		btnNewCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Step1Controller step1 = new Step1Controller();
				step1.init();
			}
		});

		
		JButton btnLegalsScore = new JButton("\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432");
		btnLegalsScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open legal rates
			}
		});
		
		JButton btnIndividualsScore = new JButton("\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u0444\u0456\u0437\u0438\u0447\u043D\u0438\u0445 \u043E\u0441\u0456\u0431");
		btnIndividualsScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open individual rates
			}
		});
		
		tfIndividSearch = new JTextField();
		tfIndividSearch.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// find person in DB
					tfIndividSearch.setText("");
				}
			}
		});
		tfIndividSearch.setToolTipText("\u041F\u043E\u0448\u0443\u043A \u0437\u0430 \u043F\u0440\u0438\u0437\u0432\u0438\u0449\u0435\u043C \u0434\u043B\u044F \u0444\u0456\u0437. \u043E\u0441\u0456\u0431");
		tfIndividSearch.setColumns(10);
		
		tfLegalSearch = new JTextField();
		tfLegalSearch.setToolTipText("\u041F\u043E\u0448\u0443\u043A \u0437\u0430 \u043F\u0440\u0438\u0437\u0432\u0438\u0449\u0435\u043C \u0434\u043B\u044F \u044E\u0440. \u043E\u0441\u0456\u0431");
		tfLegalSearch.setColumns(10);
		
		JButton btnAccounts = new JButton("\u0420\u0430\u0431\u043E\u0442\u0430 \u0437 \u0430\u043A\u0430\u0443\u043D\u0442\u0430\u043C\u0438 \u0443 \u0441\u0438\u0441\u0442\u0435\u043C\u0456");
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountsListController alc = new AccountsListController();
				alc.init();
			}
		});
		
		if(model.getUserType() != UserType.ADMIN) {
			btnAccounts.setEnabled(false);
			btnAccounts.setVisible(false);
			menuBar.setEnabled(false);
			menuBar.setVisible(false);
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMainMenu, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAccounts, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
						.addComponent(tfLegalSearch, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
						.addComponent(tfIndividSearch, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
						.addComponent(btnIndividualsScore, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
						.addComponent(btnLegalsScore, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
						.addComponent(btnNewCredit, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
					.addGap(66))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(lblMainMenu, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewCredit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLegalsScore, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnIndividualsScore, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tfIndividSearch, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(tfLegalSearch, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAccounts, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}

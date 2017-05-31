package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import creditHelper.Step1Controller;
import dataBase.IndividualModelDAO;
import dataBase.LegalModelDAO;
import main.accounts.AccountModel;
import main.accounts.AccountModel.UserType;
import main.accounts.AccountsListController;
import main.profileControl.IndividualProfileController;
import main.profileControl.LegalProfileController;
import main.rates.IndividualRates;
import main.rates.LegalRates;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainWindow extends JFrame {

	private static Logger logger = LoggerFactory.getLogger(MainWindow.class);

	private static final long serialVersionUID = 1L;

	private JTextField tfIndividSearch;
	private JTextField tfLegalSearch;

	public MainWindow(AccountModel model) {
		logger.trace("Calling MainWindow({})", model);

		setMinimumSize(new Dimension(315, 450));
		setTitle(
				"\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 330, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		JMenu mnFile = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(mnFile);

		JMenuItem mntmProperties = new JMenuItem(
				"\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u0432\u0430\u043D\u043D\u044F");
		mntmProperties.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertyWindow pw = new PropertyWindow();
				pw.setVisible(true);
			}
		});
		mnFile.add(mntmProperties);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("\u0412\u0438\u0439\u0442\u0438");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnFile.add(mntmExit);

		JLabel lblMainMenu = new JLabel(
				"\u041E\u0431\u0435\u0440\u0456\u0442\u044C \u043E\u043F\u0435\u0440\u0430\u0446\u0456\u044E \u0443 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0456");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewCredit = new JButton(
				"\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		btnNewCredit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Step1Controller step1 = new Step1Controller();
				step1.init();
			}
		});

		JButton btnLegalsScore = new JButton(
				"\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432");
		btnLegalsScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LegalRates frame = new LegalRates();
				frame.setVisible(true);
			}
		});

		JButton btnIndividualsScore = new JButton(
				"\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u0444\u0456\u0437\u0438\u0447\u043D\u0438\u0445 \u043E\u0441\u0456\u0431");
		btnIndividualsScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IndividualRates frame = new IndividualRates();
				frame.setVisible(true);
			}
		});

		this.tfIndividSearch = new JTextField();
		this.tfIndividSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					IndividualModel model = null;
					try (IndividualModelDAO dao = new IndividualModelDAO()) {
						String[] name = MainWindow.this.tfIndividSearch.getText().split(" ");
						if(name.length == 3)
							model = dao.getModel(name[0], name[1], name[2]);
					}
					if (model != null) {
						new IndividualProfileController(model).init();
					}
					MainWindow.this.tfIndividSearch.setText("");
				}
			}
		});
		this.tfIndividSearch.setToolTipText(
				"\u041F\u043E\u0448\u0443\u043A \u0437\u0430 \u043F\u0440\u0438\u0437\u0432\u0438\u0449\u0435\u043C \u0434\u043B\u044F \u0444\u0456\u0437. \u043E\u0441\u0456\u0431");
		this.tfIndividSearch.setColumns(10);

		this.tfLegalSearch = new JTextField();
		this.tfLegalSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					LegalModel model = null;
					try (LegalModelDAO dao = new LegalModelDAO()) {
						model = dao.getModel(MainWindow.this.tfLegalSearch.getText());
					}
					if (model != null) {
						new LegalProfileController(model).init();
					}
					MainWindow.this.tfLegalSearch.setText("");
				}
			}
		});
		this.tfLegalSearch.setToolTipText(
				"\u041F\u043E\u0448\u0443\u043A \u0437\u0430 \u043F\u0440\u0438\u0437\u0432\u0438\u0449\u0435\u043C \u0434\u043B\u044F \u044E\u0440. \u043E\u0441\u0456\u0431");
		this.tfLegalSearch.setColumns(10);

		JButton btnAccounts = new JButton(
				"\u0420\u043E\u0431\u043E\u0442\u0430 \u0437 \u0430\u043A\u0430\u0443\u043D\u0442\u0430\u043C\u0438 \u0443 \u0441\u0438\u0441\u0442\u0435\u043C\u0456");
		btnAccounts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsListController alc = new AccountsListController();
				alc.init();
			}
		});

		if (model.getUserType() != UserType.ADMIN) {
			btnAccounts.setEnabled(false);
			btnAccounts.setVisible(false);
			menuBar.setEnabled(false);
			menuBar.setVisible(false);
		}

		JLabel lblIndividualSearch = new JLabel(
				"\u041F\u043E\u0448\u0443\u043A \u0444\u0438\u0437. \u043E\u0441i\u0431 \u0437\u0430 \u041FI\u0411:");

		JLabel lblLegalSearch = new JLabel(
				"\u041F\u043E\u0448\u0443\u043A \u044E\u0440. \u043E\u0441i\u0431 \u0437\u0430 \u043D\u0430\u0437\u0432\u043E\u044E:");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblMainMenu, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addGap(50).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAccounts, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(this.tfLegalSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(lblLegalSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(this.tfIndividSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198,
								Short.MAX_VALUE)
						.addComponent(lblIndividualSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198,
								Short.MAX_VALUE)
						.addComponent(btnIndividualsScore, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198,
								Short.MAX_VALUE)
						.addComponent(btnLegalsScore, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addComponent(btnNewCredit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
						.addGap(66)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(51).addComponent(lblMainMenu, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(btnNewCredit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(btnLegalsScore, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(btnIndividualsScore, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblIndividualSearch)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(this.tfIndividSearch, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLegalSearch)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(this.tfLegalSearch, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(btnAccounts, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(70, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

		logger.trace("Returning from MainWindow({})", model);
	}
}

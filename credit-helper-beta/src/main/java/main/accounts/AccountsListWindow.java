package main.accounts;

import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountsListWindow extends JFrame {
	
	private static Logger logger = LoggerFactory.getLogger(AccountsListWindow.class);
	
	private static final long serialVersionUID = 5366977620182672622L;
	
	JTable table;
	ButtonColumn btnUpdate;
	ButtonColumn btnDelete;
	JButton btnCreateNewAccount;
	JButton btnBack;

	public AccountsListWindow() {
		logger.trace("Calling AccountsListWindow()");
		
		setTitle("\u041A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456 \u0443 \u0441\u0438\u0441\u0442\u0435\u043C\u0456");
		setBounds(100, 100, 711, 518);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		this.btnBack = new JButton("\u041D\u0430 \u0433\u043E\u043B\u043E\u0432\u043D\u0443");
		
		this.btnCreateNewAccount = new JButton("\u0421\u0442\u0432\u043E\u0440\u0438\u0442\u0438 \u043D\u043E\u0432\u0438\u0439 \u0430\u043A\u0430\u0443\u043D\u0442");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.btnCreateNewAccount)
					.addPreferredGap(ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
					.addComponent(this.btnBack)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.btnBack)
						.addComponent(this.btnCreateNewAccount))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel label = new JLabel("\u041A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456 \u0443 \u0441\u0438\u0441\u0442\u0435\u043C\u0456");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label, BorderLayout.NORTH);
		
		this.table = new JTable();
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setRowSelectionAllowed(false);
		this.table.setModel(new DefaultTableModel());
		
		JScrollPane scrollPane = new JScrollPane(this.table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		logger.trace("Returning from AccountsListWindow()");
	}
}

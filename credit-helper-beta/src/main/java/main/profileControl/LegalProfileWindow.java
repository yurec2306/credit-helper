package main.profileControl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class LegalProfileWindow extends JFrame {
	
	private static final long serialVersionUID = 5369476482293989580L;
	
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JLabel lblNewLabel_5;
	JLabel lblNewLabel_6;
	JLabel lblNewLabel_7;
	JLabel lblNewLabel_8;
	JLabel lblNewLabel_9;
	JLabel lblNewLabel_10;
	JLabel lblNewLabel_11;
	JLabel lblNewLabel_13;
	JLabel lblNewLabel_14;
	JLabel lblNewLabel_15;
	JLabel lblNewLabel_16;
	JLabel lblNewLabel_17;
	JTable table;
	JButton btnSave;

	public LegalProfileWindow() {
		setBounds(100, 100, 450, 485);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0414\u0430\u043D\u0456 \u043F\u0440\u043E \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(label, BorderLayout.NORTH);

		JPanel pCenter = new JPanel();

		JScrollPane scrollPane = new JScrollPane(pCenter);
		pCenter.setLayout(new GridLayout(18, 2));

		JLabel lblName = new JLabel("\u041D\u0430\u0437\u0432\u0430 \u043E\u0440\u0433\u0430\u043D\u0456\u0437\u0430\u0446\u0456\u0457");
		pCenter.add(lblName);

		lblNewLabel = new JLabel();
		pCenter.add(lblNewLabel);

		JLabel lblIPN = new JLabel("\u0406\u041F\u041D \u043E\u0440\u0433\u0430\u043D\u0456\u0437\u0430\u0446\u0456\u0457");
		pCenter.add(lblIPN);

		lblNewLabel_1 = new JLabel();
		pCenter.add(lblNewLabel_1);

		JLabel lblEntranceReportingForm = new JLabel("\u0412\u0445\u0456\u0434\u043D\u0430 \u0444\u043E\u0440\u043C\u0430 \u0437\u0432\u0456\u0442\u043D\u043E\u0441\u0442\u0456");
		pCenter.add(lblEntranceReportingForm);

		lblNewLabel_2 = new JLabel();
		pCenter.add(lblNewLabel_2);

		JLabel lblAddress = new JLabel(
				"\u0410\u0434\u0440\u0435\u0441\u0430");
		pCenter.add(lblAddress);

		lblNewLabel_3 = new JLabel();
		pCenter.add(lblNewLabel_3);

		JLabel lblBranch = new JLabel("\u0413\u0430\u043B\u0443\u0437\u044C");
		pCenter.add(lblBranch);

		lblNewLabel_4 = new JLabel();
		pCenter.add(lblNewLabel_4);

		JLabel lblDirector = new JLabel("\u0414\u0438\u0440\u0435\u043A\u0442\u043E\u0440");
		pCenter.add(lblDirector);

		lblNewLabel_5 = new JLabel();
		pCenter.add(lblNewLabel_5);

		JLabel lblDirectorPhone = new JLabel(
				"\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430");
		pCenter.add(lblDirectorPhone);

		lblNewLabel_6 = new JLabel();
		pCenter.add(lblNewLabel_6);

		JLabel lblAccountant = new JLabel(
				"\u0411\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440");
		pCenter.add(lblAccountant);

		lblNewLabel_7 = new JLabel();
		pCenter.add(lblNewLabel_7);

		JLabel lblAccountantPhone = new JLabel(
				"\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u0431\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440\u0430");
		pCenter.add(lblAccountantPhone);

		lblNewLabel_8 = new JLabel();
		pCenter.add(lblNewLabel_8);

		JLabel lblContactPerson = new JLabel(
				"\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0430 \u043E\u0441\u043E\u0431\u0430");
		pCenter.add(lblContactPerson);

		lblNewLabel_9 = new JLabel();
		pCenter.add(lblNewLabel_9);

		JLabel lblContactPersonPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D");
		pCenter.add(lblContactPersonPhone);

		lblNewLabel_10 = new JLabel();
		pCenter.add(lblNewLabel_10);

		JLabel lblFax = new JLabel(
				"\u0424\u0430\u043A\u0441");
		pCenter.add(lblFax);

		lblNewLabel_11 = new JLabel();
		pCenter.add(lblNewLabel_11);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, }, new String[] {
				"\u0421\u0443\u043C\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u0443",
				"\u0422\u0435\u0440\u043C\u0456\u043D \u043A\u0440\u0435\u0434\u0438\u0442\u0443",
				"\u0421\u0442\u0430\u043D \u0432\u0438\u043F\u043B\u0430\u0442\u0438 \u043A\u0440\u0435\u0434\u0438\u0442\u0443" }) {
			private static final long serialVersionUID = 5141917052083599332L;
			Class<? extends Object>[] columnTypes = new Class<?>[] { String.class, String.class, JCreditStatusComboBox.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(134);
		
		JLabel lblEmail = new JLabel("\u0415\u043B\u0435\u043A\u0442\u0440\u043E\u043D\u043D\u0430 \u043F\u043E\u0448\u0442\u0430");
		pCenter.add(lblEmail);
		
		lblNewLabel_13 = new JLabel("");
		pCenter.add(lblNewLabel_13);
		
		JLabel lblNACE = new JLabel("\u041A\u0412\u0415\u0414");
		pCenter.add(lblNACE);
		
		lblNewLabel_14 = new JLabel("");
		pCenter.add(lblNewLabel_14);
		
		JLabel lblUSREOU = new JLabel("\u0404\u0414\u0420\u041F\u041E\u0423");
		pCenter.add(lblUSREOU);
		
		lblNewLabel_15 = new JLabel("");
		pCenter.add(lblNewLabel_15);
		
		JLabel lblKOATUU = new JLabel("\u041A\u041E\u0410\u0422\u0423\u0423");
		pCenter.add(lblKOATUU);
		
		lblNewLabel_16 = new JLabel("");
		pCenter.add(lblNewLabel_16);
		
		JLabel lblCreditHistory = new JLabel("\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		pCenter.add(lblCreditHistory);
		
		lblNewLabel_17 = new JLabel("");
		pCenter.add(lblNewLabel_17);
		pCenter.add(table);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel pBottom = new JPanel();
		getContentPane().add(pBottom, BorderLayout.SOUTH);

		btnSave = new JButton("\u0417\u0431\u0435\u0440\u0456\u0433\u0442\u0438");
		pBottom.add(btnSave);

		JButton btnToMain = new JButton("\u041D\u0430 \u0433\u043E\u043B\u043E\u0432\u043D\u0443");
		btnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		pBottom.add(btnToMain);
	}

}
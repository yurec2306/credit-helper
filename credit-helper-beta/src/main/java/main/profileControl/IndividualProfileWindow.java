package main.profileControl;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IndividualProfileWindow extends JFrame {

	private static final long serialVersionUID = 4629428961649721253L;
	
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
	JTable table;
	JButton btnSave;

	public IndividualProfileWindow() {
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0414\u0430\u043D\u0456 \u043F\u0440\u043E \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(label, BorderLayout.NORTH);

		JPanel pCenter = new JPanel();

		JScrollPane scrollPane = new JScrollPane(pCenter);
		pCenter.setLayout(new GridLayout(15, 2));

		JLabel lblLastName = new JLabel("\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
		pCenter.add(lblLastName);

		lblNewLabel = new JLabel();
		pCenter.add(lblNewLabel);

		JLabel lblFirstName = new JLabel("\u0406\u043C'\u044F");
		pCenter.add(lblFirstName);

		lblNewLabel_1 = new JLabel();
		pCenter.add(lblNewLabel_1);

		JLabel lblMiddleName = new JLabel("\u041F\u043E-\u0431\u0430\u0442\u044C\u043A\u043E\u0432\u0456");
		pCenter.add(lblMiddleName);

		lblNewLabel_2 = new JLabel();
		pCenter.add(lblNewLabel_2);

		JLabel lblPhone = new JLabel(
				"\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0438\u0439 \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
		pCenter.add(lblPhone);

		lblNewLabel_3 = new JLabel();
		pCenter.add(lblNewLabel_3);

		JLabel lblTIN = new JLabel("\u0406\u041D\u041D");
		pCenter.add(lblTIN);

		lblNewLabel_4 = new JLabel();
		pCenter.add(lblNewLabel_4);

		JLabel lblAge = new JLabel("\u0412\u0456\u043A");
		pCenter.add(lblAge);

		lblNewLabel_5 = new JLabel();
		pCenter.add(lblNewLabel_5);

		JLabel lblMaritialStatus = new JLabel(
				"\u0421\u0456\u043C\u0435\u0439\u043D\u0438\u0439 \u0441\u0442\u0430\u043D");
		pCenter.add(lblMaritialStatus);

		lblNewLabel_6 = new JLabel();
		pCenter.add(lblNewLabel_6);

		JLabel lblChildrenNum = new JLabel(
				"\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0434\u0456\u0442\u0435\u0439");
		pCenter.add(lblChildrenNum);

		lblNewLabel_7 = new JLabel();
		pCenter.add(lblNewLabel_7);

		JLabel lblFieldOfActivity = new JLabel(
				"\u0421\u0444\u0435\u0440\u0430 \u0434\u0456\u044F\u043B\u044C\u043D\u043E\u0441\u0442\u0456");
		pCenter.add(lblFieldOfActivity);

		lblNewLabel_8 = new JLabel();
		pCenter.add(lblNewLabel_8);

		JLabel lblQualification = new JLabel(
				"\u041A\u0432\u0430\u043B\u0456\u0444\u0456\u043A\u0430\u0446\u0456\u044F");
		pCenter.add(lblQualification);

		lblNewLabel_9 = new JLabel();
		pCenter.add(lblNewLabel_9);

		JLabel lblWorkExperience = new JLabel("\u0421\u0442\u0430\u0436 \u0440\u043E\u0431\u043E\u0442\u0438");
		pCenter.add(lblWorkExperience);

		lblNewLabel_10 = new JLabel();
		pCenter.add(lblNewLabel_10);

		JLabel lblCreditHistory = new JLabel(
				"\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		pCenter.add(lblCreditHistory);

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
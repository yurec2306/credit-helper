package main.rates;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.IndividualModelDAO;
import main.IndividualModel;

public class IndividualRates extends JFrame {

	private static final long serialVersionUID = -7610149567000205970L;
	
	private JTable table;

	public IndividualRates() {
		setBounds(100, 100, 600, 469);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u0444\u0456\u0437\u0438\u0447\u043D\u0438\u0445 \u043E\u0441\u0456\u0431");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label, BorderLayout.NORTH);

		table = new JTable();
		table.setModel(new DefaultTableModel(loadDataToTable(),
				new String[] { "\u2116", "\u041F\u0406\u0411", "\u0420\u0435\u0439\u0442\u0438\u043D\u0433" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		getContentPane().add(table, BorderLayout.CENTER);

	}

	private Object[][] loadDataToTable() {
		ArrayList<Object[]> objects = new ArrayList<>();
		List<IndividualModel> res;
		try (IndividualModelDAO dao = new IndividualModelDAO()) {
			res = dao.getAllModels();
		}
		for (int i = 0; i < res.size(); i++) {
			Object[] obj = new Object[3];
			obj[0] = i;
			obj[1] = res.get(i).getLastName() + res.get(i).getFirstName() + res.get(i).getMiddleName();
			obj[2] = res.get(i).getRate();
			objects.add(obj);
		}
		return objects.toArray(new Object[objects.size()][3]);
	}

}

package main.rates;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dataBase.LegalModelDAO;
import main.LegalModel;

public class LegalRates extends JFrame {

	private static final long serialVersionUID = 5388044395108956018L;
	
	private JTable table;

	public LegalRates() {
		setBounds(100, 100, 600, 469);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432");
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
		List<LegalModel> res;
		try (LegalModelDAO dao = new LegalModelDAO()) {
			res = dao.getAllModels();
		}
		for (int i = 0; i < res.size(); i++) {
			Object[] obj = new Object[3];
			obj[0] = i;
			obj[1] = res.get(i).getOrganizationName();
			obj[2] = res.get(i).getRate();
			objects.add(obj);
		}
		return objects.toArray(new Object[objects.size()][3]);
	}

}

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.LegalModelDAO;
import main.LegalModel;

public class LegalRates extends JFrame {
	
	private static Logger logger = LoggerFactory.getLogger(LegalRates.class);

	private static final long serialVersionUID = 5388044395108956018L;
	
	private JTable table;

	public LegalRates() {
		logger.trace("Calling LegalRates()");
		
		setBounds(100, 100, 600, 469);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label, BorderLayout.NORTH);

		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(loadDataToTable(),
				new String[] { "\u2116", "\u041F\u0406\u0411", "\u0420\u0435\u0439\u0442\u0438\u043D\u0433" }) {
					private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, Double.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return this.columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return this.columnEditables[column];
			}
		});
		this.table.getColumnModel().getColumn(0).setPreferredWidth(40);
		this.table.getColumnModel().getColumn(0).setMinWidth(40);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(250);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(60);
		getContentPane().add(this.table, BorderLayout.CENTER);
		
		logger.trace("Returning from LegalRates()");
	}

	private Object[][] loadDataToTable() {
		logger.trace("Calling loadDataToTable()");
		
		ArrayList<Object[]> objects = new ArrayList<>();
		List<LegalModel> res;
		try (LegalModelDAO dao = new LegalModelDAO()) {
			res = dao.getAllModels();
		}
		
		logger.debug("Receiving: ", res);
		
		for (int i = 0; i < res.size(); i++) {
			Object[] obj = new Object[3];
			obj[0] = i;
			obj[1] = res.get(i).getOrganizationName();
			obj[2] = res.get(i).getRate();
			objects.add(obj);
		}
		
		logger.trace("Returning from loadDataToTable()");
		return objects.toArray(new Object[objects.size()][3]);
	}

}

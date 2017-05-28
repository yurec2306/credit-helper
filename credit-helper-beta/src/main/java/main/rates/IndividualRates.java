package main.rates;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.IndividualModelDAO;
import main.IndividualModel;

public class IndividualRates extends JFrame {
	
	private static Logger logger = LoggerFactory.getLogger(IndividualRates.class);

	private static final long serialVersionUID = -7610149567000205970L;
	
	private JTable table;

	public IndividualRates() {
		logger.trace("Calling IndividualRates()");
		
		setBounds(100, 100, 600, 469);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0420\u0435\u0439\u0442\u0438\u043D\u0433 \u0444\u0456\u0437\u0438\u0447\u043D\u0438\u0445 \u043E\u0441\u0456\u0431");
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

		logger.trace("Returning from IndividualRates()");
	}

	private Object[][] loadDataToTable() {
		logger.trace("Calling loadDataToTable()");
		
		ArrayList<Object[]> objects = new ArrayList<>();
		List<IndividualModel> res;
		try (IndividualModelDAO dao = new IndividualModelDAO()) {
			res = dao.getAllModels();
		}
		
		logger.debug("Receiving: ", res);
		
		for (int i = 0; i < res.size(); i++) {
			Object[] obj = new Object[3];
			obj[0] = i;
			obj[1] = res.get(i).getLastName() + res.get(i).getFirstName() + res.get(i).getMiddleName();
			obj[2] = res.get(i).getRate();
			objects.add(obj);
		}
		
		logger.trace("Returning from loadDataToTable()");
		return objects.toArray(new Object[objects.size()][3]);
	}

}

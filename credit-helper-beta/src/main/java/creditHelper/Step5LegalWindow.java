package creditHelper;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Step5LegalWindow extends AbstractStepWindow {
	
	private static final long serialVersionUID = -4063786097082259804L;
	
	JTextField tfCash;
	JTextField tfCorrentLiabilities;
	JTextField tfDeferredIncome;
	JTextField tfShortTermInvestments;
	JTextField tfPayableFor12Months;
	JTextField tfCurrentAssets;
	JTextField tfReservesForFuturePayments;
	JTextField tfCapitalAndReservesAll;
	JTextField tfLongTermCommitment;

	public Step5LegalWindow() {
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 417);

		JLabel label = new JLabel("\u041E\u0446\u0456\u043D\u043A\u0430 \u0440\u0435\u043D\u0442\u0430\u0431\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u0456 \u0442\u0430 \u043B\u0456\u043A\u0432\u0456\u0434\u043D\u043E\u0441\u0442\u0456 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u0430");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(label);
		pMiddle.setLayout(null);
		
		JLabel lblCash = new JLabel("\u0413\u0440\u043E\u0448\u043E\u0432\u0456 \u043A\u043E\u0448\u0442\u0438");
		lblCash.setBounds(10, 11, 200, 14);
		pMiddle.add(lblCash);
		
		tfCash = new JTextField();
		tfCash.setBounds(220, 8, 254, 20);
		pMiddle.add(tfCash);
		tfCash.setColumns(10);
		
		tfCorrentLiabilities = new JTextField();
		tfCorrentLiabilities.setBounds(220, 39, 254, 20);
		pMiddle.add(tfCorrentLiabilities);
		tfCorrentLiabilities.setColumns(10);
		
		JLabel lblCurrentLiabilities = new JLabel("\u041A\u043E\u0440\u043E\u0442\u043A\u043E\u0441\u0442\u0440\u043E\u043A\u043E\u0432\u0456 \u0437\u0430\u0431\u043E\u0432'\u044F\u0437\u0430\u043D\u043D\u044F");
		lblCurrentLiabilities.setBounds(10, 42, 200, 14);
		pMiddle.add(lblCurrentLiabilities);
		
		tfDeferredIncome = new JTextField();
		tfDeferredIncome.setBounds(220, 70, 254, 20);
		pMiddle.add(tfDeferredIncome);
		tfDeferredIncome.setColumns(10);
		
		JLabel lblDeferredIncome = new JLabel("\u0414\u043E\u0445\u043E\u0434\u0438 \u043C\u0430\u0439\u0431\u0443\u0442\u043D\u0456\u0445 \u043F\u0435\u0440\u0456\u043E\u0434\u0456\u0432");
		lblDeferredIncome.setBounds(10, 73, 200, 14);
		pMiddle.add(lblDeferredIncome);
		
		tfShortTermInvestments = new JTextField();
		tfShortTermInvestments.setBounds(220, 101, 254, 20);
		pMiddle.add(tfShortTermInvestments);
		tfShortTermInvestments.setColumns(10);
		
		JLabel lblShortTermInvestments = new JLabel("\u041A\u043E\u0440\u043E\u0442\u043A\u043E\u0441\u0442\u0440\u043E\u043A\u043E\u0432\u0456 \u0444\u0456\u043D\u0430\u043D\u0441\u043E\u0432\u0456 \u0432\u043A\u043B\u0430\u0434\u0435\u043D\u043D\u044F");
		lblShortTermInvestments.setBounds(10, 104, 200, 14);
		pMiddle.add(lblShortTermInvestments);
		
		tfPayableFor12Months = new JTextField();
		tfPayableFor12Months.setBounds(220, 132, 254, 20);
		pMiddle.add(tfPayableFor12Months);
		tfPayableFor12Months.setColumns(10);
		
		JLabel lblPayableFor12Months = new JLabel("\u0417\u0430\u0431\u043E\u0440\u0433\u043E\u0432\u0430\u043D\u0456\u0441\u0442\u044C \u0437\u0430 12 \u043C\u0456\u0441\u044F\u0446\u0456\u0432");
		lblPayableFor12Months.setBounds(10, 135, 200, 14);
		pMiddle.add(lblPayableFor12Months);
		
		tfCurrentAssets = new JTextField();
		tfCurrentAssets.setBounds(220, 163, 254, 20);
		pMiddle.add(tfCurrentAssets);
		tfCurrentAssets.setColumns(10);
		
		JLabel lblCurrentAssets = new JLabel("\u041E\u0431\u043E\u0440\u043E\u0442\u043D\u0456 \u0430\u043A\u0442\u0438\u0432\u0438");
		lblCurrentAssets.setBounds(10, 166, 200, 14);
		pMiddle.add(lblCurrentAssets);
		
		tfReservesForFuturePayments = new JTextField();
		tfReservesForFuturePayments.setBounds(220, 194, 254, 20);
		pMiddle.add(tfReservesForFuturePayments);
		tfReservesForFuturePayments.setColumns(10);
		
		JLabel lblReservesForFuturePayments = new JLabel("\u0420\u0435\u0437\u0435\u0440\u0432\u0438 \u043C\u0430\u0439\u0431\u0443\u0442\u043D\u0456\u0445 \u043F\u043B\u0430\u0442\u0435\u0436\u0456\u0432");
		lblReservesForFuturePayments.setBounds(10, 197, 200, 14);
		pMiddle.add(lblReservesForFuturePayments);
		
		tfCapitalAndReservesAll = new JTextField();
		tfCapitalAndReservesAll.setBounds(220, 225, 254, 20);
		pMiddle.add(tfCapitalAndReservesAll);
		tfCapitalAndReservesAll.setColumns(10);
		
		JLabel lblCapitalAndReservesAll = new JLabel("\u041A\u0430\u043F\u0456\u0442\u0430\u043B \u0456 \u0440\u0435\u0437\u0435\u0440\u0432\u0438 \u0443\u0441\u044C\u043E\u0433\u043E");
		lblCapitalAndReservesAll.setBounds(10, 228, 200, 14);
		pMiddle.add(lblCapitalAndReservesAll);
		
		tfLongTermCommitment = new JTextField();
		tfLongTermCommitment.setBounds(220, 256, 254, 20);
		pMiddle.add(tfLongTermCommitment);
		tfLongTermCommitment.setColumns(10);
		
		JLabel lblLongTermCommitment = new JLabel("\u0414\u043E\u0432\u0433\u043E\u0441\u0442\u0440\u043E\u043A\u043E\u0432\u0456 \u0437\u0430\u0431\u043E\u0432'\u044F\u0437\u0430\u043D\u043D\u044F");
		lblLongTermCommitment.setBounds(10, 259, 200, 14);
		pMiddle.add(lblLongTermCommitment);
	}

}

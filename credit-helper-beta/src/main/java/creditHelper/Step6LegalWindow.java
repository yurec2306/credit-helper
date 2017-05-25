package creditHelper;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Step6LegalWindow extends AbstractStepWindow {
	
	private static final long serialVersionUID = 8148571308679552278L;
	
	JTextField tfAbsoluteLiquidityRatio;
	JTextField tfCriticalEvaluationFactor;
	JTextField tfCurrentRatio;
	JTextField tfTheRatioOfFunds;
	JTextField tfProfitability;

	public Step6LegalWindow() {
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 417);

		JLabel label = new JLabel("\u041E\u0442\u0440\u0438\u043C\u0430\u043D\u0456 \u043A\u043E\u0435\u0444\u0456\u0446\u0456\u0454\u043D\u0442\u0438");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(label);
		pMiddle.setLayout(null);
		
		JLabel lblAbsoluteLiquidityRatio = new JLabel("\u041A\u043E\u0435\u0444\u0456\u0446\u0456\u0454\u043D\u0442 \u0430\u0431\u0441\u043E\u043B\u044E\u0442\u043D\u043E\u0457 \u043B\u0456\u043A\u0432\u0456\u0434\u043D\u043E\u0441\u0442\u0456");
		lblAbsoluteLiquidityRatio.setBounds(10, 11, 200, 14);
		pMiddle.add(lblAbsoluteLiquidityRatio);
		
		tfAbsoluteLiquidityRatio = new JTextField();
		tfAbsoluteLiquidityRatio.setEditable(false);
		tfAbsoluteLiquidityRatio.setBounds(220, 8, 254, 20);
		pMiddle.add(tfAbsoluteLiquidityRatio);
		tfAbsoluteLiquidityRatio.setColumns(10);
		
		tfCriticalEvaluationFactor = new JTextField();
		tfCriticalEvaluationFactor.setEditable(false);
		tfCriticalEvaluationFactor.setBounds(220, 39, 254, 20);
		pMiddle.add(tfCriticalEvaluationFactor);
		tfCriticalEvaluationFactor.setColumns(10);
		
		JLabel lblCriticalEvaluationFactor = new JLabel("\u041A\u043E\u0435\u0444\u0456\u0446\u0456\u0454\u043D\u0442 \u043A\u0440\u0438\u0442\u0438\u0447\u043D\u043E\u0457 \u043E\u0446\u0456\u043D\u043A\u0438");
		lblCriticalEvaluationFactor.setBounds(10, 42, 200, 14);
		pMiddle.add(lblCriticalEvaluationFactor);
		
		tfCurrentRatio = new JTextField();
		tfCurrentRatio.setEditable(false);
		tfCurrentRatio.setBounds(220, 70, 254, 20);
		pMiddle.add(tfCurrentRatio);
		tfCurrentRatio.setColumns(10);
		
		JLabel lblCurrentRatio = new JLabel("\u041A\u043E\u0435\u0444\u0456\u0446\u0456\u0454\u043D\u0442 \u043F\u043E\u0442\u043E\u0447\u043D\u043E\u0457 \u043B\u0456\u043A\u0432\u0456\u0434\u043D\u043E\u0441\u0442\u0456");
		lblCurrentRatio.setBounds(10, 73, 200, 14);
		pMiddle.add(lblCurrentRatio);
		
		tfTheRatioOfFunds = new JTextField();
		tfTheRatioOfFunds.setEditable(false);
		tfTheRatioOfFunds.setBounds(220, 101, 254, 20);
		pMiddle.add(tfTheRatioOfFunds);
		tfTheRatioOfFunds.setColumns(10);
		
		JLabel lblTheRatioOfFunds = new JLabel("\u041A\u043E\u0435\u0444\u0456\u0446\u0456\u0454\u043D\u0442 \u0432\u0456\u0434\u043D\u043E\u0448\u0435\u043D\u043D\u044F \u043A\u043E\u0448\u0442\u0456\u0432");
		lblTheRatioOfFunds.setBounds(10, 104, 200, 14);
		pMiddle.add(lblTheRatioOfFunds);
		
		tfProfitability = new JTextField();
		tfProfitability.setEditable(false);
		tfProfitability.setBounds(220, 132, 254, 20);
		pMiddle.add(tfProfitability);
		tfProfitability.setColumns(10);
		
		JLabel lblProfitability = new JLabel("\u0420\u0435\u043D\u0442\u0430\u0431\u0435\u043B\u044C\u043D\u0456\u0441\u0442\u044C");
		lblProfitability.setBounds(10, 135, 200, 14);
		pMiddle.add(lblProfitability);
	}
}

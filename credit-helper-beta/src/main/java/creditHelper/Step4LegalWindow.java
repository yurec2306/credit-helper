package creditHelper;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Step4LegalWindow extends AbstractStepWindow {

	private static final long serialVersionUID = -4274804971418535161L;
	
	JTextField tfDownPayment;
	JTextField tfProvision;
	JTextField tfCreditSize;
	JTextField tfCreditLength;
	JTextField tfIncome;
	JTextField tfCosts;

	public Step4LegalWindow() {
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 300);

		JLabel label = new JLabel("\u041E\u0446\u0456\u043D\u043A\u0430 \u0444\u0456\u043D\u0430\u043D\u0441\u043E\u0432\u0438\u0445 \u043C\u043E\u0436\u043B\u0438\u0432\u043E\u0441\u0442\u0435\u0439 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u0430");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(label);
		pMiddle.setLayout(null);
		
		JLabel lblDownPayment = new JLabel("\u041F\u0435\u0440\u0448\u0438\u0439 \u0432\u043D\u0435\u0441\u043E\u043A");
		lblDownPayment.setBounds(10, 11, 200, 14);
		pMiddle.add(lblDownPayment);
		
		tfDownPayment = new JTextField();
		tfDownPayment.setBounds(220, 8, 254, 20);
		pMiddle.add(tfDownPayment);
		tfDownPayment.setColumns(10);
		
		tfProvision = new JTextField();
		tfProvision.setBounds(220, 39, 254, 20);
		pMiddle.add(tfProvision);
		tfProvision.setColumns(10);
		
		JLabel lblProvision = new JLabel("\u0417\u0430\u0431\u0435\u0437\u043F\u0435\u0447\u0435\u043D\u043D\u044F");
		lblProvision.setBounds(10, 42, 200, 14);
		pMiddle.add(lblProvision);
		
		tfCreditSize = new JTextField();
		tfCreditSize.setBounds(220, 70, 254, 20);
		pMiddle.add(tfCreditSize);
		tfCreditSize.setColumns(10);
		
		JLabel lblCreditSize = new JLabel("\u0411\u0430\u0436\u0430\u043D\u0430 \u0441\u0443\u043C\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u0443");
		lblCreditSize.setBounds(10, 73, 200, 14);
		pMiddle.add(lblCreditSize);
		
		tfCreditLength = new JTextField();
		tfCreditLength.setBounds(220, 101, 254, 20);
		pMiddle.add(tfCreditLength);
		tfCreditLength.setColumns(10);
		
		JLabel lblCreditLength = new JLabel("\u0422\u0435\u0440\u043C\u0456\u043D \u043A\u0440\u0435\u0434\u0438\u0442\u0443");
		lblCreditLength.setBounds(10, 104, 200, 14);
		pMiddle.add(lblCreditLength);
		
		tfIncome = new JTextField();
		tfIncome.setBounds(220, 132, 254, 20);
		pMiddle.add(tfIncome);
		tfIncome.setColumns(10);
		
		JLabel lblIncome = new JLabel("\u0414\u043E\u0445\u0456\u0434 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u0430");
		lblIncome.setBounds(10, 135, 200, 14);
		pMiddle.add(lblIncome);
		
		tfCosts = new JTextField();
		tfCosts.setBounds(220, 163, 254, 20);
		pMiddle.add(tfCosts);
		tfCosts.setColumns(10);
		
		JLabel lblCosts = new JLabel("\u0412\u0438\u0442\u0440\u0430\u0442\u0438");
		lblCosts.setBounds(10, 166, 200, 14);
		pMiddle.add(lblCosts);
	}

}

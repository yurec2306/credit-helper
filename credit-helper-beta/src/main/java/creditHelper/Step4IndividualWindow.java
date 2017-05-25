package creditHelper;

import javax.swing.JLabel;
import javax.swing.JTextField;

import main.CreditModel.CreditType;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Step4IndividualWindow extends AbstractStepWindow {
	
	private static final long serialVersionUID = 873143804437400627L;
	
	JTextField tfProvision;
	JTextField tfDownPayment;
	JComboBox<CreditType> cbCreditType;
	JTextField tfCreditSize;
	JTextField tfCreditLength;

	public Step4IndividualWindow() {
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 272);

		JLabel label = new JLabel("\u041E\u0446\u0456\u043D\u043A\u0430 \u0444\u0456\u043D\u0430\u043D\u0441\u043E\u0432\u0438\u0445 \u043C\u043E\u0436\u043B\u0438\u0432\u043E\u0441\u0442\u0435\u0439 \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(label);
		pMiddle.setLayout(null);
		
		JLabel lblCreditType = new JLabel("\u0412\u0438\u0434 \u043A\u0440\u0435\u0434\u0438\u0442\u0443");
		lblCreditType.setBounds(10, 16, 200, 14);
		pMiddle.add(lblCreditType);
		
		JLabel lblProvisionOrDownPayment = new JLabel("\u0417\u0430\u0431\u0435\u0437\u043F\u0435\u0447\u0435\u043D\u043D\u044F");
		lblProvisionOrDownPayment.setBounds(10, 48, 200, 14);
		pMiddle.add(lblProvisionOrDownPayment);
		
		tfProvision = new JTextField();
		tfProvision.setBounds(224, 42, 250, 20);
		pMiddle.add(tfProvision);
		tfProvision.setColumns(10);
		
		tfDownPayment = new JTextField();
		tfDownPayment.setBounds(224, 42, 250, 20);
		pMiddle.add(tfDownPayment);
		tfDownPayment.setColumns(10);
		tfDownPayment.setVisible(false);
		
		cbCreditType = new JComboBox<CreditType>();
		cbCreditType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbCreditType.getSelectedItem() == CreditType.SHORT_TERM) {
					lblProvisionOrDownPayment.setText("\u0417\u0430\u0431\u0435\u0437\u043F\u0435\u0447\u0435\u043D\u043D\u044F");
					tfProvision.setVisible(true);
					tfDownPayment.setVisible(false);
				} else {
					lblProvisionOrDownPayment.setText("\u041F\u0435\u0440\u0448\u0438\u0439 \u0432\u043D\u0435\u0441\u043E\u043A");
					tfDownPayment.setVisible(true);
					tfProvision.setVisible(false);
				}
			}
		});
		cbCreditType.setModel(new DefaultComboBoxModel<CreditType>(CreditType.values()));
		cbCreditType.setBounds(224, 11, 250, 20);
		pMiddle.add(cbCreditType);
		
		JLabel lblCreditSize = new JLabel("\u0420\u043E\u0437\u043C\u0456\u0440 \u043A\u0440\u0435\u0434\u0438\u0442\u0443");
		lblCreditSize.setBounds(10, 76, 200, 14);
		pMiddle.add(lblCreditSize);
		
		tfCreditSize = new JTextField();
		tfCreditSize.setBounds(224, 73, 250, 20);
		pMiddle.add(tfCreditSize);
		tfCreditSize.setColumns(10);
		
		JLabel lblCreditLength = new JLabel("\u0422\u0435\u0440\u043C\u0456\u043D \u043A\u0440\u0435\u0434\u0438\u0442\u0443");
		lblCreditLength.setBounds(10, 107, 200, 14);
		pMiddle.add(lblCreditLength);
		
		tfCreditLength = new JTextField();
		tfCreditLength.setBounds(224, 104, 250, 20);
		pMiddle.add(tfCreditLength);
		tfCreditLength.setColumns(10);

	}

}

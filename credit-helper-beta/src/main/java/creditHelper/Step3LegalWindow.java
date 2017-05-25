package creditHelper;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.IndividualModel.CreditHistory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Step3LegalWindow extends AbstractStepWindow {

	private static final long serialVersionUID = 8807737935893962535L;
	
	JTextField tfOrganizationTIN;
	JTextField tfEntranceReportingForm;
	JTextField tfAddress;
	JTextField tfBranch;
	JTextField tfDirector;
	JTextField tfDirectorPhone;
	JTextField tfAccountant;
	JTextField tfAccountantPhone;
	JTextField tfContactPerson;
	JTextField tfContactPersonPhone;
	JTextField tfFax;
	JTextField tfEmail;
	JTextField tfNACE;
	JTextField tfUSREOU;
	JTextField tfKOATUU;
	JComboBox<CreditHistory> cbCreditHistory;

	public Step3LegalWindow() {

		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 610);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblInfo = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0434\u0430\u043D\u043D\u0456 \u043F\u0440\u043E \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u043E");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(lblInfo);
		pMiddle.setLayout(null);

		JLabel lblOrganizationTIN = new JLabel("\u0406\u041F\u041D \u043E\u0440\u0433\u0430\u043D\u0456\u0437\u0430\u0446\u0456\u0457");
		lblOrganizationTIN.setBounds(10, 11, 200, 14);
		pMiddle.add(lblOrganizationTIN);

		tfOrganizationTIN = new JTextField();
		tfOrganizationTIN.setBounds(220, 8, 254, 20);
		pMiddle.add(tfOrganizationTIN);
		tfOrganizationTIN.setColumns(10);

		JLabel lblEntranceReportingForm = new JLabel("\u0412\u0445\u0456\u0434\u043D\u0430 \u0444\u043E\u0440\u043C\u0430 \u0437\u0432\u0456\u0442\u043D\u043E\u0441\u0442\u0456");
		lblEntranceReportingForm.setBounds(10, 42, 200, 14);
		pMiddle.add(lblEntranceReportingForm);

		tfEntranceReportingForm = new JTextField();
		tfEntranceReportingForm.setBounds(220, 39, 254, 20);
		pMiddle.add(tfEntranceReportingForm);
		tfEntranceReportingForm.setColumns(10);

		tfAddress = new JTextField();
		tfAddress.setBounds(220, 70, 254, 20);
		pMiddle.add(tfAddress);
		tfAddress.setColumns(10);

		JLabel lblAddress = new JLabel("\u0410\u0434\u0440\u0435\u0441\u0430");
		lblAddress.setBounds(10, 73, 200, 14);
		pMiddle.add(lblAddress);

		tfBranch = new JTextField();
		tfBranch.setBounds(220, 101, 254, 20);
		pMiddle.add(tfBranch);
		tfBranch.setColumns(10);

		JLabel lblBranch = new JLabel("\u0413\u0430\u043B\u0443\u0437\u044C");
		lblBranch.setBounds(10, 104, 200, 14);
		pMiddle.add(lblBranch);

		tfDirector = new JTextField();
		tfDirector.setBounds(220, 132, 254, 20);
		pMiddle.add(tfDirector);
		tfDirector.setColumns(10);

		JLabel lblDirector = new JLabel("\u0414\u0438\u0440\u0435\u043A\u0442\u043E\u0440");
		lblDirector.setBounds(10, 135, 200, 14);
		pMiddle.add(lblDirector);

		tfDirectorPhone = new JTextField();
		tfDirectorPhone.setBounds(220, 163, 254, 20);
		pMiddle.add(tfDirectorPhone);
		tfDirectorPhone.setColumns(10);

		JLabel lblDirectorPhone = new JLabel("\u0422\u0438\u043B\u0435\u0444\u043E\u043D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430");
		lblDirectorPhone.setBounds(10, 166, 200, 14);
		pMiddle.add(lblDirectorPhone);

		tfAccountant = new JTextField();
		tfAccountant.setBounds(220, 194, 254, 20);
		pMiddle.add(tfAccountant);
		tfAccountant.setColumns(10);

		JLabel lblAccountant = new JLabel("\u0411\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440");
		lblAccountant.setBounds(10, 197, 200, 14);
		pMiddle.add(lblAccountant);

		tfAccountantPhone = new JTextField();
		tfAccountantPhone.setBounds(220, 225, 254, 20);
		pMiddle.add(tfAccountantPhone);
		tfAccountantPhone.setColumns(10);

		JLabel lblAccountantPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u0431\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440\u0430");
		lblAccountantPhone.setBounds(10, 231, 200, 14);
		pMiddle.add(lblAccountantPhone);

		tfContactPerson = new JTextField();
		tfContactPerson.setBounds(220, 256, 254, 20);
		pMiddle.add(tfContactPerson);
		tfContactPerson.setColumns(10);

		JLabel lblContactPerson = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0430 \u043E\u0441\u043E\u0431\u0430");
		lblContactPerson.setBounds(10, 259, 200, 14);
		pMiddle.add(lblContactPerson);

		tfContactPersonPhone = new JTextField();
		tfContactPersonPhone.setBounds(220, 287, 254, 20);
		pMiddle.add(tfContactPersonPhone);
		tfContactPersonPhone.setColumns(10);

		JLabel lblContactPersonPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D");
		lblContactPersonPhone.setBounds(10, 290, 200, 14);
		pMiddle.add(lblContactPersonPhone);

		tfFax = new JTextField();
		tfFax.setBounds(220, 318, 254, 20);
		pMiddle.add(tfFax);
		tfFax.setColumns(10);

		JLabel lblFax = new JLabel("\u0424\u0430\u043A\u0441");
		lblFax.setBounds(10, 321, 200, 14);
		pMiddle.add(lblFax);

		tfEmail = new JTextField();
		tfEmail.setBounds(220, 349, 254, 20);
		pMiddle.add(tfEmail);
		tfEmail.setColumns(10);

		JLabel lblEmail = new JLabel("\u0404\u043B\u0435\u043A\u0442\u0440\u043E\u043D\u0430 \u043F\u043E\u0448\u0442\u0430");
		lblEmail.setBounds(10, 352, 200, 14);
		pMiddle.add(lblEmail);

		tfNACE = new JTextField();
		tfNACE.setBounds(220, 380, 254, 20);
		pMiddle.add(tfNACE);
		tfNACE.setColumns(10);

		JLabel lblNace = new JLabel("\u041A\u0412\u0415\u0414");
		lblNace.setBounds(10, 383, 200, 14);
		pMiddle.add(lblNace);

		tfUSREOU = new JTextField();
		tfUSREOU.setBounds(220, 411, 254, 20);
		pMiddle.add(tfUSREOU);
		tfUSREOU.setColumns(10);

		JLabel lblUsreou = new JLabel("\u0404\u0414\u0420\u041F\u041E\u0423");
		lblUsreou.setBounds(10, 414, 200, 14);
		pMiddle.add(lblUsreou);

		tfKOATUU = new JTextField();
		tfKOATUU.setBounds(220, 442, 254, 20);
		pMiddle.add(tfKOATUU);
		tfKOATUU.setColumns(10);

		JLabel lblKoatuu = new JLabel("\u041A\u041E\u0410\u0422\u0423\u0423");
		lblKoatuu.setBounds(10, 445, 200, 14);
		pMiddle.add(lblKoatuu);

		cbCreditHistory = new JComboBox<CreditHistory>();
		cbCreditHistory.setModel(new DefaultComboBoxModel<CreditHistory>(CreditHistory.values()));
		cbCreditHistory.setBounds(220, 473, 254, 20);
		pMiddle.add(cbCreditHistory);

		JLabel lblCreditHistory = new JLabel("\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		lblCreditHistory.setBounds(10, 476, 200, 14);
		pMiddle.add(lblCreditHistory);

	}
}

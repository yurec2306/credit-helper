package creditHelper;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.IndividualModel.CreditHistory;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Step3LegalWindow extends AbstractStepWindow {
	
	private static Logger logger = LoggerFactory.getLogger(Step3LegalWindow.class);

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
		logger.trace("Calling Step3LegalWindow()");
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 610);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel lblInfo = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0434\u0430\u043D\u043D\u0456 \u043F\u0440\u043E \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u043E");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.pTop.add(lblInfo);
		this.pMiddle.setLayout(null);

		JLabel lblOrganizationTIN = new JLabel("\u0406\u041F\u041D \u043E\u0440\u0433\u0430\u043D\u0456\u0437\u0430\u0446\u0456\u0457");
		lblOrganizationTIN.setBounds(10, 11, 200, 14);
		this.pMiddle.add(lblOrganizationTIN);

		this.tfOrganizationTIN = new JTextField();
		this.tfOrganizationTIN.setBounds(220, 8, 254, 20);
		this.pMiddle.add(this.tfOrganizationTIN);
		this.tfOrganizationTIN.setColumns(10);

		JLabel lblEntranceReportingForm = new JLabel("\u0412\u0445\u0456\u0434\u043D\u0430 \u0444\u043E\u0440\u043C\u0430 \u0437\u0432\u0456\u0442\u043D\u043E\u0441\u0442\u0456");
		lblEntranceReportingForm.setBounds(10, 42, 200, 14);
		this.pMiddle.add(lblEntranceReportingForm);

		this.tfEntranceReportingForm = new JTextField();
		this.tfEntranceReportingForm.setBounds(220, 39, 254, 20);
		this.pMiddle.add(this.tfEntranceReportingForm);
		this.tfEntranceReportingForm.setColumns(10);

		this.tfAddress = new JTextField();
		this.tfAddress.setBounds(220, 70, 254, 20);
		this.pMiddle.add(this.tfAddress);
		this.tfAddress.setColumns(10);

		JLabel lblAddress = new JLabel("\u0410\u0434\u0440\u0435\u0441\u0430");
		lblAddress.setBounds(10, 73, 200, 14);
		this.pMiddle.add(lblAddress);

		this.tfBranch = new JTextField();
		this.tfBranch.setBounds(220, 101, 254, 20);
		this.pMiddle.add(this.tfBranch);
		this.tfBranch.setColumns(10);

		JLabel lblBranch = new JLabel("\u0413\u0430\u043B\u0443\u0437\u044C");
		lblBranch.setBounds(10, 104, 200, 14);
		this.pMiddle.add(lblBranch);

		this.tfDirector = new JTextField();
		this.tfDirector.setBounds(220, 132, 254, 20);
		this.pMiddle.add(this.tfDirector);
		this.tfDirector.setColumns(10);

		JLabel lblDirector = new JLabel("\u0414\u0438\u0440\u0435\u043A\u0442\u043E\u0440");
		lblDirector.setBounds(10, 135, 200, 14);
		this.pMiddle.add(lblDirector);

		this.tfDirectorPhone = new JTextField();
		this.tfDirectorPhone.setBounds(220, 163, 254, 20);
		this.pMiddle.add(this.tfDirectorPhone);
		this.tfDirectorPhone.setColumns(10);

		JLabel lblDirectorPhone = new JLabel("\u0422\u0438\u043B\u0435\u0444\u043E\u043D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430");
		lblDirectorPhone.setBounds(10, 166, 200, 14);
		this.pMiddle.add(lblDirectorPhone);

		this.tfAccountant = new JTextField();
		this.tfAccountant.setBounds(220, 194, 254, 20);
		this.pMiddle.add(this.tfAccountant);
		this.tfAccountant.setColumns(10);

		JLabel lblAccountant = new JLabel("\u0411\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440");
		lblAccountant.setBounds(10, 197, 200, 14);
		this.pMiddle.add(lblAccountant);

		this.tfAccountantPhone = new JTextField();
		this.tfAccountantPhone.setBounds(220, 225, 254, 20);
		this.pMiddle.add(this.tfAccountantPhone);
		this.tfAccountantPhone.setColumns(10);

		JLabel lblAccountantPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u0431\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440\u0430");
		lblAccountantPhone.setBounds(10, 231, 200, 14);
		this.pMiddle.add(lblAccountantPhone);

		this.tfContactPerson = new JTextField();
		this.tfContactPerson.setBounds(220, 256, 254, 20);
		this.pMiddle.add(this.tfContactPerson);
		this.tfContactPerson.setColumns(10);

		JLabel lblContactPerson = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0430 \u043E\u0441\u043E\u0431\u0430");
		lblContactPerson.setBounds(10, 259, 200, 14);
		this.pMiddle.add(lblContactPerson);

		this.tfContactPersonPhone = new JTextField();
		this.tfContactPersonPhone.setBounds(220, 287, 254, 20);
		this.pMiddle.add(this.tfContactPersonPhone);
		this.tfContactPersonPhone.setColumns(10);

		JLabel lblContactPersonPhone = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D");
		lblContactPersonPhone.setBounds(10, 290, 200, 14);
		this.pMiddle.add(lblContactPersonPhone);

		this.tfFax = new JTextField();
		this.tfFax.setBounds(220, 318, 254, 20);
		this.pMiddle.add(this.tfFax);
		this.tfFax.setColumns(10);

		JLabel lblFax = new JLabel("\u0424\u0430\u043A\u0441");
		lblFax.setBounds(10, 321, 200, 14);
		this.pMiddle.add(lblFax);

		this.tfEmail = new JTextField();
		this.tfEmail.setBounds(220, 349, 254, 20);
		this.pMiddle.add(this.tfEmail);
		this.tfEmail.setColumns(10);

		JLabel lblEmail = new JLabel("\u0404\u043B\u0435\u043A\u0442\u0440\u043E\u043D\u0430 \u043F\u043E\u0448\u0442\u0430");
		lblEmail.setBounds(10, 352, 200, 14);
		this.pMiddle.add(lblEmail);

		this.tfNACE = new JTextField();
		this.tfNACE.setBounds(220, 380, 254, 20);
		this.pMiddle.add(this.tfNACE);
		this.tfNACE.setColumns(10);

		JLabel lblNace = new JLabel("\u041A\u0412\u0415\u0414");
		lblNace.setBounds(10, 383, 200, 14);
		this.pMiddle.add(lblNace);

		this.tfUSREOU = new JTextField();
		this.tfUSREOU.setBounds(220, 411, 254, 20);
		this.pMiddle.add(this.tfUSREOU);
		this.tfUSREOU.setColumns(10);

		JLabel lblUsreou = new JLabel("\u0404\u0414\u0420\u041F\u041E\u0423");
		lblUsreou.setBounds(10, 414, 200, 14);
		this.pMiddle.add(lblUsreou);

		this.tfKOATUU = new JTextField();
		this.tfKOATUU.setBounds(220, 442, 254, 20);
		this.pMiddle.add(this.tfKOATUU);
		this.tfKOATUU.setColumns(10);

		JLabel lblKoatuu = new JLabel("\u041A\u041E\u0410\u0422\u0423\u0423");
		lblKoatuu.setBounds(10, 445, 200, 14);
		this.pMiddle.add(lblKoatuu);

		this.cbCreditHistory = new JComboBox<>();
		this.cbCreditHistory.setModel(new DefaultComboBoxModel<>(CreditHistory.values()));
		this.cbCreditHistory.setBounds(220, 473, 254, 20);
		this.pMiddle.add(this.cbCreditHistory);

		JLabel lblCreditHistory = new JLabel("\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		lblCreditHistory.setBounds(10, 476, 200, 14);
		this.pMiddle.add(lblCreditHistory);

		logger.trace("Returning from Step3LegalWindow()");
	}
}

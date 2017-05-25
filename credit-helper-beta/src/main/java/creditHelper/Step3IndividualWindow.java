package creditHelper;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import main.IndividualModel.MaritialStatus;
import main.IndividualModel.CreditHistory;
import main.IndividualModel.FieldOfActivity;
import main.IndividualModel.Qualification;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

public class Step3IndividualWindow extends AbstractStepWindow {
	
	private static final long serialVersionUID = 7092260123649927694L;
	
	JTextField tfPhone;
	JTextField tfTIN;
	JTextField tfAge;
	JComboBox<MaritialStatus> cbMaritialStatus;
	JTextField tfChildrenNum;
	JComboBox<FieldOfActivity> cbFieldOfActivity;
	JComboBox<Qualification> cbQualification;
	JTextField tfWorkExperience;
	JComboBox<CreditHistory> cbCreditHistory;
	JTextField tfAvgIncome;

	public Step3IndividualWindow() {
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 502, 441);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblInfo = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0434\u0430\u043D\u043D\u0456 \u043F\u0440\u043E \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(lblInfo);
		pMiddle.setLayout(null);
		
		JLabel lblPhone = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0438\u0439 \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
		lblPhone.setBounds(10, 14, 200, 14);
		pMiddle.add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(224, 11, 250, 20);
		tfPhone.setColumns(10);
		pMiddle.add(tfPhone);
		
		JLabel lblTIN = new JLabel("\u0406\u041D\u041D");
		lblTIN.setBounds(10, 45, 200, 14);
		pMiddle.add(lblTIN);
		
		tfTIN = new JTextField();
		tfTIN.setBounds(224, 42, 250, 20);
		tfTIN.setColumns(10);
		pMiddle.add(tfTIN);
		
		JLabel lblAge = new JLabel("\u0412\u0456\u043A");
		lblAge.setBounds(10, 76, 200, 14);
		pMiddle.add(lblAge);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(224, 73, 250, 20);
		pMiddle.add(tfAge);
		
		JLabel lblMaritialStatus = new JLabel("\u0421\u0456\u043C\u0435\u0439\u043D\u0438\u0439 \u0441\u0442\u0430\u043D");
		lblMaritialStatus.setBounds(10, 107, 200, 14);
		pMiddle.add(lblMaritialStatus);
		
		cbMaritialStatus = new JComboBox<MaritialStatus>();
		cbMaritialStatus.setBounds(224, 104, 250, 20);
		cbMaritialStatus.setModel(new DefaultComboBoxModel<MaritialStatus>(MaritialStatus.values()));
		pMiddle.add(cbMaritialStatus);
		
		JLabel lblChildrenNum = new JLabel("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0434\u0456\u0442\u0435\u0439");
		lblChildrenNum.setBounds(10, 141, 200, 14);
		pMiddle.add(lblChildrenNum);
		
		tfChildrenNum = new JTextField();
		tfChildrenNum.setColumns(10);
		tfChildrenNum.setBounds(224, 135, 250, 20);
		pMiddle.add(tfChildrenNum);
		
		JLabel lblFieldOfActivity = new JLabel("\u0421\u0444\u0435\u0440\u0430 \u0434\u0456\u044F\u043B\u044C\u043D\u043E\u0441\u0442\u0456");
		lblFieldOfActivity.setBounds(10, 169, 200, 14);
		pMiddle.add(lblFieldOfActivity);
		
		cbFieldOfActivity = new JComboBox<FieldOfActivity>();
		cbFieldOfActivity.setBounds(224, 166, 250, 20);
		cbFieldOfActivity.setModel(new DefaultComboBoxModel<FieldOfActivity>(FieldOfActivity.values()));
		pMiddle.add(cbFieldOfActivity);
		
		JLabel lblQualification = new JLabel("\u041A\u0432\u0430\u043B\u0456\u0444\u0456\u043A\u0430\u0446\u0456\u044F");
		lblQualification.setBounds(10, 200, 200, 14);
		pMiddle.add(lblQualification);
		
		cbQualification = new JComboBox<Qualification>();
		cbQualification.setBounds(224, 197, 250, 20);
		cbQualification.setModel(new DefaultComboBoxModel<Qualification>(Qualification.values()));
		pMiddle.add(cbQualification);
		
		JLabel lblWorkExperience = new JLabel("\u0421\u0442\u0430\u0436 \u0440\u043E\u0431\u043E\u0442\u0438");
		lblWorkExperience.setBounds(10, 234, 200, 14);
		pMiddle.add(lblWorkExperience);
		
		tfWorkExperience = new JTextField();
		tfWorkExperience.setBounds(224, 228, 250, 20);
		pMiddle.add(tfWorkExperience);
		
		JLabel lblCreditHistory = new JLabel("\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		lblCreditHistory.setBounds(10, 262, 200, 14);
		pMiddle.add(lblCreditHistory);
		
		cbCreditHistory = new JComboBox<CreditHistory>();
		cbCreditHistory.setModel(new DefaultComboBoxModel<CreditHistory>(CreditHistory.values()));
		cbCreditHistory.setBounds(224, 259, 250, 20);
		pMiddle.add(cbCreditHistory);
		
		JLabel lblAvgIncome = new JLabel("\u0421\u0435\u0440\u0435\u0434\u043D\u044C\u043E\u043C\u0456\u0441\u044F\u0447\u043D\u0438\u0439 \u0434\u043E\u0445\u0456\u0434");
		lblAvgIncome.setBounds(10, 293, 200, 14);
		pMiddle.add(lblAvgIncome);
		
		tfAvgIncome = new JTextField();
		tfAvgIncome.setBounds(224, 290, 250, 20);
		pMiddle.add(tfAvgIncome);
		
	}
}

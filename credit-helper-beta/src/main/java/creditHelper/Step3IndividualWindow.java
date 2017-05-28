package creditHelper;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import main.IndividualModel.MaritialStatus;
import main.IndividualModel.CreditHistory;
import main.IndividualModel.FieldOfActivity;
import main.IndividualModel.Qualification;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

public class Step3IndividualWindow extends AbstractStepWindow {
	
	private static Logger logger = LoggerFactory.getLogger(Step3IndividualWindow.class);
	
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
		logger.trace("Calling Step3IndividualWindow()");
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 502, 441);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblInfo = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0434\u0430\u043D\u043D\u0456 \u043F\u0440\u043E \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.pTop.add(lblInfo);
		this.pMiddle.setLayout(null);
		
		JLabel lblPhone = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0438\u0439 \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
		lblPhone.setBounds(10, 14, 200, 14);
		this.pMiddle.add(lblPhone);
		
		this.tfPhone = new JTextField();
		this.tfPhone.setBounds(224, 11, 250, 20);
		this.tfPhone.setColumns(10);
		this.pMiddle.add(this.tfPhone);
		
		JLabel lblTIN = new JLabel("\u0406\u041D\u041D");
		lblTIN.setBounds(10, 45, 200, 14);
		this.pMiddle.add(lblTIN);
		
		this.tfTIN = new JTextField();
		this.tfTIN.setBounds(224, 42, 250, 20);
		this.tfTIN.setColumns(10);
		this.pMiddle.add(this.tfTIN);
		
		JLabel lblAge = new JLabel("\u0412\u0456\u043A");
		lblAge.setBounds(10, 76, 200, 14);
		this.pMiddle.add(lblAge);
		
		this.tfAge = new JTextField();
		this.tfAge.setColumns(10);
		this.tfAge.setBounds(224, 73, 250, 20);
		this.pMiddle.add(this.tfAge);
		
		JLabel lblMaritialStatus = new JLabel("\u0421\u0456\u043C\u0435\u0439\u043D\u0438\u0439 \u0441\u0442\u0430\u043D");
		lblMaritialStatus.setBounds(10, 107, 200, 14);
		this.pMiddle.add(lblMaritialStatus);
		
		this.cbMaritialStatus = new JComboBox<>();
		this.cbMaritialStatus.setBounds(224, 104, 250, 20);
		this.cbMaritialStatus.setModel(new DefaultComboBoxModel<>(MaritialStatus.values()));
		this.pMiddle.add(this.cbMaritialStatus);
		
		JLabel lblChildrenNum = new JLabel("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0434\u0456\u0442\u0435\u0439");
		lblChildrenNum.setBounds(10, 141, 200, 14);
		this.pMiddle.add(lblChildrenNum);
		
		this.tfChildrenNum = new JTextField();
		this.tfChildrenNum.setColumns(10);
		this.tfChildrenNum.setBounds(224, 135, 250, 20);
		this.pMiddle.add(this.tfChildrenNum);
		
		JLabel lblFieldOfActivity = new JLabel("\u0421\u0444\u0435\u0440\u0430 \u0434\u0456\u044F\u043B\u044C\u043D\u043E\u0441\u0442\u0456");
		lblFieldOfActivity.setBounds(10, 169, 200, 14);
		this.pMiddle.add(lblFieldOfActivity);
		
		this.cbFieldOfActivity = new JComboBox<>();
		this.cbFieldOfActivity.setBounds(224, 166, 250, 20);
		this.cbFieldOfActivity.setModel(new DefaultComboBoxModel<>(FieldOfActivity.values()));
		this.pMiddle.add(this.cbFieldOfActivity);
		
		JLabel lblQualification = new JLabel("\u041A\u0432\u0430\u043B\u0456\u0444\u0456\u043A\u0430\u0446\u0456\u044F");
		lblQualification.setBounds(10, 200, 200, 14);
		this.pMiddle.add(lblQualification);
		
		this.cbQualification = new JComboBox<>();
		this.cbQualification.setBounds(224, 197, 250, 20);
		this.cbQualification.setModel(new DefaultComboBoxModel<>(Qualification.values()));
		this.pMiddle.add(this.cbQualification);
		
		JLabel lblWorkExperience = new JLabel("\u0421\u0442\u0430\u0436 \u0440\u043E\u0431\u043E\u0442\u0438");
		lblWorkExperience.setBounds(10, 234, 200, 14);
		this.pMiddle.add(lblWorkExperience);
		
		this.tfWorkExperience = new JTextField();
		this.tfWorkExperience.setBounds(224, 228, 250, 20);
		this.pMiddle.add(this.tfWorkExperience);
		
		JLabel lblCreditHistory = new JLabel("\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		lblCreditHistory.setBounds(10, 262, 200, 14);
		this.pMiddle.add(lblCreditHistory);
		
		this.cbCreditHistory = new JComboBox<>();
		this.cbCreditHistory.setModel(new DefaultComboBoxModel<>(CreditHistory.values()));
		this.cbCreditHistory.setBounds(224, 259, 250, 20);
		this.pMiddle.add(this.cbCreditHistory);
		
		JLabel lblAvgIncome = new JLabel("\u0421\u0435\u0440\u0435\u0434\u043D\u044C\u043E\u043C\u0456\u0441\u044F\u0447\u043D\u0438\u0439 \u0434\u043E\u0445\u0456\u0434");
		lblAvgIncome.setBounds(10, 293, 200, 14);
		this.pMiddle.add(lblAvgIncome);
		
		this.tfAvgIncome = new JTextField();
		this.tfAvgIncome.setBounds(224, 290, 250, 20);
		this.pMiddle.add(this.tfAvgIncome);
		
		logger.trace("Returning from Step3IndividualWindow()");
	}
}

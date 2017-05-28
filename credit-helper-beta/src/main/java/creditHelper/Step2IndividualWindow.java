package creditHelper;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Step2IndividualWindow extends AbstractStepWindow {
	
	private static Logger logger = LoggerFactory.getLogger(Step2IndividualWindow.class);

	private static final long serialVersionUID = -564040192673284921L;
	
	JTextField tfLastName;
	JTextField tfFirstName;
	JTextField tfMiddleName;
	
	public Step2IndividualWindow() {
		logger.trace("Calling Step2IndividualWindow()");
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblInfo = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0434\u0430\u043D\u043D\u0456 \u043F\u0440\u043E \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.pTop.add(lblInfo);
		this.pMiddle.setLayout(null);
		
		JLabel lblLastName = new JLabel("\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
		lblLastName.setBounds(10, 14, 200, 14);
		this.pMiddle.add(lblLastName);
		
		this.tfLastName = new JTextField();
		this.tfLastName.setBounds(224, 11, 250, 20);
		this.tfLastName.setColumns(10);
		this.pMiddle.add(this.tfLastName);
		
		JLabel lblFirstName = new JLabel("\u0406\u043C'\u044F");
		lblFirstName.setBounds(10, 45, 200, 14);
		this.pMiddle.add(lblFirstName);
		
		this.tfFirstName = new JTextField();
		this.tfFirstName.setBounds(224, 42, 250, 20);
		this.tfFirstName.setColumns(10);
		this.pMiddle.add(this.tfFirstName);
		
		JLabel lblMiddleName = new JLabel("\u041F\u043E-\u0431\u0430\u0442\u044C\u043A\u043E\u0432\u0456");
		lblMiddleName.setBounds(10, 76, 200, 14);
		this.pMiddle.add(lblMiddleName);
		
		this.tfMiddleName = new JTextField();
		this.tfMiddleName.setBounds(224, 73, 250, 20);
		this.tfMiddleName.setColumns(10);
		this.pMiddle.add(this.tfMiddleName);
		
		logger.trace("Returning from Step2IndividualWindow()");
	}

}

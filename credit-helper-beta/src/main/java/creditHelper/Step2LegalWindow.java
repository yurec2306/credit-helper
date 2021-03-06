package creditHelper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Step2LegalWindow extends AbstractStepWindow {
	
	private static Logger logger = LoggerFactory.getLogger(Step2LegalWindow.class);
	
	private static final long serialVersionUID = 326528705394571210L;
	
	JTextField tfCompanyName;

	public Step2LegalWindow() {
		logger.trace("Calling Step2LegalWindow()");
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblInfo = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u043D\u0430\u0437\u0432\u0443 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u0430");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.pTop.add(lblInfo);
		this.pMiddle.setLayout(null);
		
		JLabel lblCompanyName = new JLabel("\u041D\u0430\u0437\u0432\u0430 \u043F\u0456\u0434\u043F\u0440\u0438\u0454\u043C\u0441\u0442\u0432\u0430");
		lblCompanyName.setBounds(10, 11, 200, 14);
		this.pMiddle.add(lblCompanyName);
		
		this.tfCompanyName = new JTextField();
		this.tfCompanyName.setBounds(220, 8, 254, 20);
		this.pMiddle.add(this.tfCompanyName);
		this.tfCompanyName.setColumns(10);

		logger.trace("Returning from Step2LegalWindow()");
	}
}

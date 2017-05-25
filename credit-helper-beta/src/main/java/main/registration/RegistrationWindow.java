package main.registration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class RegistrationWindow extends JFrame {
	
	private static final long serialVersionUID = -2349739184090003710L;
	
	JTextField tfLastName;
	JTextField tfFirstName;
	JTextField tfMiddleName;
	JTextField tfPhone;
	JTextField tfBunkNumber;
	JTextField tfLogin;
	JPasswordField tfPassword;
	JTextField tfEmail;
	JButton btnNext;

	public RegistrationWindow() {
		setTitle("\u0420\u0435\u0433\u0456\u0441\u0442\u0440\u0430\u0446\u0456\u044F");
		setBounds(100, 100, 500, 381);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("\u0420\u0435\u0433\u0456\u0441\u0442\u0440\u0430\u0446\u0456\u044F");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistration.setBounds(10, 11, 464, 14);
		getContentPane().add(lblRegistration);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(200, 36, 274, 20);
		getContentPane().add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel lblLastName = new JLabel("\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
		lblLastName.setBounds(10, 39, 180, 14);
		getContentPane().add(lblLastName);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(200, 67, 274, 20);
		getContentPane().add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("\u0406\u043C'\u044F");
		lblFirstName.setBounds(10, 70, 180, 14);
		getContentPane().add(lblFirstName);
		
		tfMiddleName = new JTextField();
		tfMiddleName.setBounds(200, 98, 274, 20);
		getContentPane().add(tfMiddleName);
		tfMiddleName.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("\u041F\u043E-\u0431\u0430\u0442\u044C\u043A\u043E\u0432\u0456");
		lblMiddleName.setBounds(10, 101, 180, 14);
		getContentPane().add(lblMiddleName);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(200, 129, 274, 20);
		getContentPane().add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel lblPhone = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0438\u0439 \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
		lblPhone.setBounds(10, 132, 180, 14);
		getContentPane().add(lblPhone);
		
		tfBunkNumber = new JTextField();
		tfBunkNumber.setBounds(200, 160, 274, 20);
		getContentPane().add(tfBunkNumber);
		tfBunkNumber.setColumns(10);
		
		JLabel lblBunkNumber = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u0432\u0456\u0434\u0434\u0456\u043B\u0443 \u0431\u0430\u043D\u043A\u0443");
		lblBunkNumber.setBounds(10, 163, 180, 14);
		getContentPane().add(lblBunkNumber);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(200, 191, 274, 20);
		getContentPane().add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0456\u043D");
		lblLogin.setBounds(10, 194, 180, 14);
		getContentPane().add(lblLogin);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(200, 222, 274, 20);
		getContentPane().add(tfPassword);
		tfPassword.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		lblPassword.setBounds(10, 225, 180, 14);
		getContentPane().add(lblPassword);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(200, 253, 274, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 256, 180, 14);
		getContentPane().add(lblEmail);
		
		btnNext = new JButton("\u041F\u0440\u043E\u0434\u043E\u0432\u0436\u0438\u0442\u0438");
		btnNext.setBounds(352, 309, 122, 23);
		getContentPane().add(btnNext);

	}
}

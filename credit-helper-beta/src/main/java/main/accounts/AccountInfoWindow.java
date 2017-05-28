package main.accounts;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.accounts.AccountModel.UserType;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class AccountInfoWindow extends JFrame {
	
	private static Logger logger = LoggerFactory.getLogger(AccountInfoWindow.class);

	private static final long serialVersionUID = 1901211038500597473L;
	
	JTextField tfLastName;
	JTextField tfFirstName;
	JTextField tfMiddleName;
	JTextField tfPhone;
	JTextField tfBunkNumber;
	JTextField tfLogin;
	JPasswordField tfPassword;
	JTextField tfEmail;
	JComboBox<UserType> cbUserType;
	JButton btnNext;
	
	public AccountInfoWindow() {
		logger.trace("Calling AccountInfoWindow()");
		
		setTitle("\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u043D\u043D\u044F \u0430\u043A\u043A\u0430\u0443\u043D\u0442\u0443");
		
		setBounds(100, 100, 500, 405);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u043D\u043D\u044F \u0430\u043A\u0430\u0443\u043D\u0442\u0443");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistration.setBounds(10, 11, 464, 14);
		getContentPane().add(lblRegistration);
		
		this.tfLastName = new JTextField();
		this.tfLastName.setBounds(200, 36, 274, 20);
		getContentPane().add(this.tfLastName);
		this.tfLastName.setColumns(10);
		
		JLabel lblLastName = new JLabel("\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
		lblLastName.setBounds(10, 39, 180, 14);
		getContentPane().add(lblLastName);
		
		this.tfFirstName = new JTextField();
		this.tfFirstName.setBounds(200, 67, 274, 20);
		getContentPane().add(this.tfFirstName);
		this.tfFirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("\u0406\u043C'\u044F");
		lblFirstName.setBounds(10, 70, 180, 14);
		getContentPane().add(lblFirstName);
		
		this.tfMiddleName = new JTextField();
		this.tfMiddleName.setBounds(200, 98, 274, 20);
		getContentPane().add(this.tfMiddleName);
		this.tfMiddleName.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("\u041F\u043E-\u0431\u0430\u0442\u044C\u043A\u043E\u0432\u0456");
		lblMiddleName.setBounds(10, 101, 180, 14);
		getContentPane().add(lblMiddleName);
		
		this.tfPhone = new JTextField();
		this.tfPhone.setBounds(200, 129, 274, 20);
		getContentPane().add(this.tfPhone);
		this.tfPhone.setColumns(10);
		
		JLabel lblPhone = new JLabel("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0438\u0439 \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
		lblPhone.setBounds(10, 132, 180, 14);
		getContentPane().add(lblPhone);
		
		this.tfBunkNumber = new JTextField();
		this.tfBunkNumber.setBounds(200, 160, 274, 20);
		getContentPane().add(this.tfBunkNumber);
		this.tfBunkNumber.setColumns(10);
		
		JLabel lblBunkNumber = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u0432\u0456\u0434\u0434\u0456\u043B\u0443 \u0431\u0430\u043D\u043A\u0443");
		lblBunkNumber.setBounds(10, 163, 180, 14);
		getContentPane().add(lblBunkNumber);
		
		this.tfLogin = new JTextField();
		this.tfLogin.setBounds(200, 191, 274, 20);
		getContentPane().add(this.tfLogin);
		this.tfLogin.setColumns(10);
		
		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0456\u043D");
		lblLogin.setBounds(10, 194, 180, 14);
		getContentPane().add(lblLogin);
		
		this.tfPassword = new JPasswordField();
		this.tfPassword.setBounds(200, 222, 274, 20);
		getContentPane().add(this.tfPassword);
		this.tfPassword.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		lblPassword.setBounds(10, 225, 180, 14);
		getContentPane().add(lblPassword);
		
		this.tfEmail = new JTextField();
		this.tfEmail.setBounds(200, 253, 274, 20);
		getContentPane().add(this.tfEmail);
		this.tfEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 256, 180, 14);
		getContentPane().add(lblEmail);
		
		this.btnNext = new JButton("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
		this.btnNext.setBounds(352, 333, 122, 23);
		getContentPane().add(this.btnNext);
		
		this.cbUserType = new JComboBox<>();
		this.cbUserType.setBounds(200, 284, 274, 20);
		this.cbUserType.setModel(new DefaultComboBoxModel<>(UserType.values()));
		getContentPane().add(this.cbUserType);
		
		JLabel lblUserType = new JLabel("\u0422\u0438\u043F \u0430\u043A\u0430\u0443\u043D\u0443\u0442\u0443");
		lblUserType.setBounds(10, 287, 180, 14);
		getContentPane().add(lblUserType);
		
		logger.trace("Returning from AccountInfoWindow()");
	}
}

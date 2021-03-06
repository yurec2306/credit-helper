package main.login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;

public class LoginWindow extends JFrame {
	
	private static final long serialVersionUID = -5053195039647441073L;
	
	private static Logger logger = LoggerFactory.getLogger(LoginWindow.class);
	
	JTextField tfLogin;
	JPasswordField pfPassword;
	JLabel lblError;
	JLabel lblRegistration;
	JButton btnNext;

	public LoginWindow() {
		logger.trace("Calling LoginWindow()");
		
		setResizable(false);
		setTitle("\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0456\u044F");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAuthorization = new JLabel("\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0456\u044F");
		lblAuthorization.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthorization.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorization.setBounds(10, 11, 424, 14);
		getContentPane().add(lblAuthorization);
		
		this.lblError = new JLabel("\u041B\u043E\u0433\u0456\u043D \u0430\u0431\u043E \u043F\u0430\u0440\u043E\u043B\u044C \u0431\u0443\u043B\u043E \u0432\u0432\u0435\u0434\u0435\u043D\u043E \u043D\u0435 \u043F\u0440\u0430\u0432\u0438\u043B\u044C\u043D\u043E, \u043F\u043E\u0432\u0442\u043E\u0440\u0456\u0442\u044C \u0432\u0432\u0435\u0434\u0435\u043D\u043D\u044F");
		this.lblError.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblError.setBounds(10, 36, 424, 14);
		this.lblError.setVisible(false);
		getContentPane().add(this.lblError);
		
		this.tfLogin = new JTextField();
		this.tfLogin.setBounds(162, 82, 168, 20);
		getContentPane().add(this.tfLogin);
		this.tfLogin.setColumns(10);
		
		this.pfPassword = new JPasswordField();
		this.pfPassword.setBounds(162, 113, 168, 20);
		getContentPane().add(this.pfPassword);
		this.pfPassword.setColumns(10);
		
		JLabel lblPassReminder = new JLabel("\u0417\u0430\u0431\u0443\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C");
		lblPassReminder.setForeground(Color.BLUE);
		lblPassReminder.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassReminder.setBounds(162, 144, 101, 14);
		getContentPane().add(lblPassReminder);
		
		this.lblRegistration = new JLabel("\u0420\u0435\u0433\u0456\u0441\u0442\u0440\u0430\u0446\u0456\u044F");
		this.lblRegistration.setForeground(Color.BLUE);
		this.lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRegistration.setBounds(162, 169, 101, 14);
		getContentPane().add(this.lblRegistration);
		
		this.btnNext = new JButton("\u041F\u0440\u043E\u0434\u043E\u0432\u0436\u0438\u0442\u0438");
		this.btnNext.setBounds(310, 238, 124, 23);
		getContentPane().add(this.btnNext);
		
		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0456\u043D:");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setBounds(10, 85, 132, 14);
		getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 116, 132, 14);
		getContentPane().add(lblPassword);
		
		logger.trace("Returning from LoginWindow()");
	}
}

package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorWindow extends JFrame {
	
	private static Logger logger = LoggerFactory.getLogger(ErrorWindow.class);

	private static final long serialVersionUID = -4748831964895485937L;

	public ErrorWindow(String message) {
		logger.trace("Calling ErrorWindow({})", message);
		
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 150);
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		
		JLabel label = new JLabel(message);
		setSize(450 + label.getWidth(), 150); 
		label.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label, BorderLayout.CENTER);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		getContentPane().add(btnOk, BorderLayout.SOUTH);
		
		logger.trace("Returning from ErrorWindow({})", message);
	}

}

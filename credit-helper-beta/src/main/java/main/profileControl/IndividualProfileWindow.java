package main.profileControl;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.accounts.ButtonColumn;

public class IndividualProfileWindow extends JFrame {

	private static Logger logger = LoggerFactory.getLogger(IndividualProfileWindow.class);

	private static final long serialVersionUID = 4629428961649721253L;

	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JLabel lblNewLabel_5;
	JLabel lblNewLabel_6;
	JLabel lblNewLabel_7;
	JLabel lblNewLabel_8;
	JLabel lblNewLabel_9;
	JLabel lblNewLabel_10;
	JLabel lblNewLabel_11;
	JTable table;
	ButtonColumn btnReturn;
	ButtonColumn btnNotReturn;
	JButton btnSave;

	public IndividualProfileWindow() {
		logger.trace("Calling IndividualProfileWindow()");

		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(
				"\u0414\u0430\u043D\u0456 \u043F\u0440\u043E \u043A\u043B\u0456\u0454\u043D\u0442\u0430");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(label, BorderLayout.NORTH);

		JPanel pMid = new JPanel();
		getContentPane().add(pMid, BorderLayout.CENTER);
		pMid.setLayout(new BorderLayout(0, 0));

		JPanel pCenter = new JPanel();
		pMid.add(pCenter, BorderLayout.NORTH);
		pCenter.setLayout(new GridLayout(12, 2));

		JLabel lblLastName = new JLabel("\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
		pCenter.add(lblLastName);

		this.lblNewLabel = new JLabel();
		pCenter.add(this.lblNewLabel);

		JLabel lblFirstName = new JLabel("\u0406\u043C'\u044F");
		pCenter.add(lblFirstName);

		this.lblNewLabel_1 = new JLabel();
		pCenter.add(this.lblNewLabel_1);

		JLabel lblMiddleName = new JLabel("\u041F\u043E-\u0431\u0430\u0442\u044C\u043A\u043E\u0432\u0456");
		pCenter.add(lblMiddleName);

		this.lblNewLabel_2 = new JLabel();
		pCenter.add(this.lblNewLabel_2);

		JLabel lblPhone = new JLabel(
				"\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u043D\u0438\u0439 \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
		pCenter.add(lblPhone);

		this.lblNewLabel_3 = new JLabel();
		pCenter.add(this.lblNewLabel_3);

		JLabel lblTIN = new JLabel("\u0406\u041D\u041D");
		pCenter.add(lblTIN);

		this.lblNewLabel_4 = new JLabel();
		pCenter.add(this.lblNewLabel_4);

		JLabel lblAge = new JLabel("\u0412\u0456\u043A");
		pCenter.add(lblAge);

		this.lblNewLabel_5 = new JLabel();
		pCenter.add(this.lblNewLabel_5);

		JLabel lblMaritialStatus = new JLabel(
				"\u0421\u0456\u043C\u0435\u0439\u043D\u0438\u0439 \u0441\u0442\u0430\u043D");
		pCenter.add(lblMaritialStatus);

		this.lblNewLabel_6 = new JLabel();
		pCenter.add(this.lblNewLabel_6);

		JLabel lblChildrenNum = new JLabel(
				"\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0434\u0456\u0442\u0435\u0439");
		pCenter.add(lblChildrenNum);

		this.lblNewLabel_7 = new JLabel();
		pCenter.add(this.lblNewLabel_7);

		JLabel lblFieldOfActivity = new JLabel(
				"\u0421\u0444\u0435\u0440\u0430 \u0434\u0456\u044F\u043B\u044C\u043D\u043E\u0441\u0442\u0456");
		pCenter.add(lblFieldOfActivity);

		this.lblNewLabel_8 = new JLabel();
		pCenter.add(this.lblNewLabel_8);

		JLabel lblQualification = new JLabel(
				"\u041A\u0432\u0430\u043B\u0456\u0444\u0456\u043A\u0430\u0446\u0456\u044F");
		pCenter.add(lblQualification);

		this.lblNewLabel_9 = new JLabel();
		pCenter.add(this.lblNewLabel_9);

		JLabel lblWorkExperience = new JLabel("\u0421\u0442\u0430\u0436 \u0440\u043E\u0431\u043E\u0442\u0438");
		pCenter.add(lblWorkExperience);

		this.lblNewLabel_10 = new JLabel();
		pCenter.add(this.lblNewLabel_10);

		JLabel lblCreditHistory = new JLabel(
				"\u041A\u0440\u0435\u0434\u0438\u0442\u043D\u0430 \u0456\u0441\u0442\u043E\u0440\u0456\u044F");
		pCenter.add(lblCreditHistory);

		this.lblNewLabel_11 = new JLabel();
		pCenter.add(this.lblNewLabel_11);

		this.table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(this.table);
		pMid.add(scrollPane, BorderLayout.CENTER);

		JPanel pBottom = new JPanel();
		getContentPane().add(pBottom, BorderLayout.SOUTH);

		this.btnSave = new JButton("\u0417\u0431\u0435\u0440\u0456\u0433\u0442\u0438");
		pBottom.add(this.btnSave);

		JButton btnToMain = new JButton("\u041D\u0430 \u0433\u043E\u043B\u043E\u0432\u043D\u0443");
		btnToMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		pBottom.add(btnToMain);

		logger.trace("Returning from IndividualProfileWindow()");
	}

}

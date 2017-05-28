package creditHelper;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JButton;

public class Step1Window extends AbstractStepWindow {

	private static Logger logger = LoggerFactory.getLogger(Step1Window.class);
	
	private static final long serialVersionUID = -3750608826831767430L;
	
	JButton btnNewIndividual;
	JButton btnNewLegal;
	JButton btnToMain;
	
	public Step1Window() {
		
		logger.trace("Calling Step1Window()");
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 300, 300);
		
		JLabel label = new JLabel("\u041E\u0431\u0435\u0440\u0456\u0442\u044C \u0432\u0438\u0434 \u043E\u0441\u043E\u0431\u0438 \u0434\u043B\u044F \u043E\u0446\u0456\u043D\u043A\u0438");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.pTop.add(label);
		
		this.btnNewIndividual = new JButton("\u0424\u0456\u0437\u0438\u0447\u043D\u0430 \u043E\u0441\u043E\u0431\u0430");
		
		this.btnNewLegal = new JButton("\u042E\u0440\u0438\u0434\u0438\u0447\u043D\u0430 \u043E\u0441\u043E\u0431\u0430");
		GroupLayout groupLayout = new GroupLayout(this.pMiddle);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.btnNewIndividual, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(this.btnNewLegal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(82))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.btnNewIndividual, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(this.btnNewLegal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(124))
		);
		this.pMiddle.setLayout(groupLayout);
		
		this.btnToMain = new JButton("\u041D\u0430 \u0433\u043E\u043B\u043E\u0432\u043D\u0443");
		this.pBottom.add(this.btnToMain);
	}

}

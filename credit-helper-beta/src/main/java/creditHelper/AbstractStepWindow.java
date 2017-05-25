package creditHelper;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

abstract public class AbstractStepWindow extends JFrame implements StepWindow {
	
	private static final long serialVersionUID = 1L;
	
	protected JPanel pTop;
	protected JPanel pMiddle;
	protected JPanel pBottom;
	protected JButton btnNext;

	public AbstractStepWindow() {		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pTop = new JPanel();
		getContentPane().add(pTop, BorderLayout.NORTH);
		
		pMiddle = new JPanel();
		getContentPane().add(pMiddle, BorderLayout.CENTER);
		
		pBottom = new JPanel();
		getContentPane().add(pBottom, BorderLayout.SOUTH);
		
		btnNext = new JButton("\u041F\u0440\u043E\u0434\u043E\u0432\u0436\u0438\u0442\u0438");
		pBottom.add(btnNext);
	}

}

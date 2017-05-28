package creditHelper;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

abstract public class AbstractStepWindow extends JFrame implements StepWindow {
	
	private static final long serialVersionUID = 1L;
	
	protected JPanel pTop;
	protected JPanel pMiddle;
	protected JPanel pBottom;
	protected JButton btnNext;

	public AbstractStepWindow() {		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.pTop = new JPanel();
		getContentPane().add(this.pTop, BorderLayout.NORTH);
		
		this.pMiddle = new JPanel();
		getContentPane().add(this.pMiddle, BorderLayout.CENTER);
		
		this.pBottom = new JPanel();
		getContentPane().add(this.pBottom, BorderLayout.SOUTH);
		
		this.btnNext = new JButton("\u041F\u0440\u043E\u0434\u043E\u0432\u0436\u0438\u0442\u0438");
		this.pBottom.add(this.btnNext);
	}

}

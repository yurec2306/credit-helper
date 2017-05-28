package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Step1Controller implements StepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step1Controller.class);

	private Step1Window window;
	
	public Step1Controller() {
		logger.trace("Calling Step1Controller()");
		logger.trace("Returning from Step1Controller()");
	}
	
	@Override
	public void init() {
		logger.trace("Calling init()");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new Step1Window();
					window.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.setVisible(false);
				window.btnNewIndividual.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						window.setVisible(false);
						window.dispose();
						Step2IndividualController step2 = new Step2IndividualController();
						step2.init();
					}
				});
				window.btnNewLegal.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						window.setVisible(false);
						window.dispose();
						Step2LegalController step2 = new Step2LegalController();
						step2.init();
					}
				});
				window.btnToMain.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						window.setVisible(false);
						window.dispose();
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}

}

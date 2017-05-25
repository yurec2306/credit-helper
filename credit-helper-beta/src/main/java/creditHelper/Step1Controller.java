package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step1Controller {

	private Step1Window window;
	
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step1Window();
					window.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.setVisible(false);
				window.btnNewIndividual.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						window.setVisible(false);
						window.dispose();
						Step2IndividualController step2 = new Step2IndividualController();
						step2.init();
					}
				});
				window.btnNewLegal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						window.setVisible(false);
						window.dispose();
						Step2LegalController step2 = new Step2LegalController();
						step2.init();
					}
				});
				window.btnToMain.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						window.setVisible(false);
						window.dispose();
					}
				});
			}
		});
	}

}

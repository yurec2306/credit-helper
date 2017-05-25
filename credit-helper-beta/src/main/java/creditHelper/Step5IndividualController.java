package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataBase.IndividualModelDAO;
import main.IndividualModel;;

public class Step5IndividualController extends AbstractStepController {
	
	private Step5IndividualWindow window;

	public Step5IndividualController(IndividualModel model) {
		super(model);
	}

	public void init() {
		try(IndividualModelDAO dao = new IndividualModelDAO()) {
			dao.saveOrUpdate(model);
		} catch (Exception e2) {
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step5IndividualWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						window.setVisible(false);
						window.dispose();
					}
				});
			}
		});
	}
	
}

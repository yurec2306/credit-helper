package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.IndividualModelDAO;
import main.IndividualModel;;

public class Step5IndividualController extends AbstractStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step5IndividualController.class);
	
	private Step5IndividualWindow window;

	public Step5IndividualController(IndividualModel model) {
		super(model);
		logger.trace("Calling Step5IndividualController({})", model);
		logger.trace("Returning from Step5IndividualController({})", model);
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		try(IndividualModelDAO dao = new IndividualModelDAO()) {
			dao.saveOrUpdate(model);
		}
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new Step5IndividualWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						window.setVisible(false);
						window.dispose();
					}
				});
			}
		});
		
		logger.trace("Returning from init()");
	}
	
}

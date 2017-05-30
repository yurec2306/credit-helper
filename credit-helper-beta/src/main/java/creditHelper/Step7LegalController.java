package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dataBase.LegalModelDAO;
import main.LegalModel;

public class Step7LegalController extends AbstractLegalStepController {
	
	private static Logger logger = LoggerFactory.getLogger(Step7LegalController.class);

	private Step7LegalWindow window;
	
	public Step7LegalController(LegalModel model) {
		super(model);
		
		logger.trace("Creating Step7LegalController");
	}

	@Override
	public void init() {
		logger.trace("Calling init()");
		
		saveModel(this.model);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new Step7LegalWindow();
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
	
	private static void saveModel(LegalModel model) {
		logger.trace("Calling saveModel(LegalModel model)");
		
		try(LegalModelDAO dao = new LegalModelDAO()) {
			dao.saveOrUpdate(model);
		}
		
		logger.trace("Returning from saveModel(...)");
	}
}

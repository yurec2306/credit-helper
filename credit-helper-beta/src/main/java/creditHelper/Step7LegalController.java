package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataBase.LegalModelDAO;
import main.LegalModel;

public class Step7LegalController extends AbstractLegalStepController {

	private Step7LegalWindow window;
	
	public Step7LegalController(LegalModel model) {
		super(model);
	}

	@Override
	public void init() {
		try(LegalModelDAO dao = new LegalModelDAO()) {
			dao.saveOrUpdate(model);
		} catch (Exception e) {
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step7LegalWindow();
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

package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.LegalModel;

public class Step4LegalController extends AbstractLegalStepController {

	private Step4LegalWindow window;

	public Step4LegalController(LegalModel model) {
		super(model);
	}

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step4LegalWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setToModel();
						window.setVisible(false);
						Step5LegalController step5 = new Step5LegalController(model);
						step5.init();
						window.dispose();
					}
				});
			}
		});
	}

	private void setToModel() {
		model.getLastCredit().setDownPayment(Double.parseDouble(window.tfDownPayment.getText()));
		model.getLastCredit().setProvision(Double.parseDouble(window.tfProvision.getText()));
		model.getLastCredit().setCreditSize(Double.parseDouble(window.tfCreditSize.getText()));
		model.getLastCredit().setCreditLength(Double.parseDouble(window.tfCreditLength.getText()));
		model.getLastCredit().setNetIncome(Double.parseDouble(window.tfIncome.getText()));
		model.getLastCredit().setCosts(Double.parseDouble(window.tfCosts.getText()));
		model.getLastCredit().setCreditRate(0.25); //25%
	}

}

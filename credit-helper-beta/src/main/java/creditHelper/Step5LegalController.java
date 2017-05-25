package creditHelper;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.LegalModel;

public class Step5LegalController extends AbstractLegalStepController {

	private Step5LegalWindow window;
	
	public Step5LegalController(LegalModel model) {
		super(model);
	}

	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Step5LegalWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setToModel();
						window.setVisible(false);
						Step6LegalController step6 = new Step6LegalController(model);
						step6.init();
						window.dispose();
					}
				});
			}
		});
	}

	private void setToModel() {
		model.getLastCredit().setCash(Double.parseDouble(window.tfCash.getText()));
		model.getLastCredit().setCurrentLiabilities(Double.parseDouble(window.tfCorrentLiabilities.getText()));
		model.getLastCredit().setDeferredIncome(Double.parseDouble(window.tfDeferredIncome.getText()));
		model.getLastCredit().setShortTermInvestments(Double.parseDouble(window.tfShortTermInvestments.getText()));
		model.getLastCredit().setPayableFor12Months(Double.parseDouble(window.tfPayableFor12Months.getText()));
		model.getLastCredit().setCurrentAssets(Double.parseDouble(window.tfCurrentAssets.getText()));
		model.getLastCredit().setReservesForFuturePayments(Double.parseDouble(window.tfReservesForFuturePayments.getText()));
		model.getLastCredit().setCapitalAndReservesAll(Double.parseDouble(window.tfCapitalAndReservesAll.getText()));
		model.getLastCredit().setLongTermCommitment(Double.parseDouble(window.tfLongTermCommitment.getText()));		
	}
	
}

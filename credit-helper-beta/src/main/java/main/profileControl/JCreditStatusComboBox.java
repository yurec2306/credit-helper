package main.profileControl;

import javax.swing.JComboBox;

import main.CreditModel.CreditStatus;

public class JCreditStatusComboBox extends JComboBox<CreditStatus> {

	private static final long serialVersionUID = -4094873733621313325L;

	public JCreditStatusComboBox(CreditStatus selectedCreditStatus) {
		super();
		insertItemAt(CreditStatus.UNKNOWN, 0);
		insertItemAt(CreditStatus.RETURNED, 1);
		insertItemAt(CreditStatus.NOT_RETURNED, 2);
		setSelectedItem(selectedCreditStatus);
	}
	
}

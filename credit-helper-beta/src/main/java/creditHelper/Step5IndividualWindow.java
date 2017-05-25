package creditHelper;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

public class Step5IndividualWindow extends AbstractStepWindow {

	private static final long serialVersionUID = 5426019328094087669L;

	public Step5IndividualWindow() {
		
		setTitle("\u041E\u0446\u0456\u043D\u043A\u0430 \u043A\u0440\u0435\u0434\u0438\u0442\u043E\u0441\u043F\u0440\u043E\u043C\u043E\u0436\u043D\u043E\u0441\u0442\u0456");
		setBounds(100, 100, 380, 360);

		JLabel label = new JLabel("\u041F\u0456\u0434\u0441\u0443\u043C\u043A\u0438");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		pTop.add(label);
		pMiddle.setLayout(new BorderLayout(0, 0));
		
		JLabel lblResult = new JLabel("\u0412\u044B \u043C\u043E\u0436\u0435\u0442\u0435 \u043E\u0442\u0440\u0438\u043C\u0430\u0442\u0438 \u043A\u0440\u0435\u0434\u0438\u0442!");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		pMiddle.add(lblResult);
		
		btnNext.setText("\u0417\u0430\u0432\u0435\u0440\u0448\u0438\u0442\u0438");
	}

}

package main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import neuralNetwork.NetworkHelper;
import neuralNetwork.NeuralNetwork;
import neuralNetwork.NeuralNetworkImpl;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

public class PropertyWindow extends JFrame {

	private static final long serialVersionUID = 6444911507804005087L;
	
	public PropertyWindow() {
		setResizable(false);
		setTitle("\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u0432\u0430\u043D\u043D\u044F");
		setBounds(100, 100, 280, 220);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pCenter = new JPanel();
		getContentPane().add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(null);
		
		JLabel label = new JLabel("\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u043D\u043A\u0438 \u0441\u0438\u0441\u0442\u0435\u043C\u0438 \u043E\u0446\u0456\u043D\u043A\u0438");
		label.setBounds(10, 11, 169, 14);
		pCenter.add(label);
		
		JButton btnResetNeuralNetwork = new JButton("\u0421\u043A\u0438\u043D\u0443\u0442\u0438 \u043F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u0438 \u043E\u0446\u0456\u043D\u043A\u0438");
		btnResetNeuralNetwork.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					NeuralNetwork nn = new NeuralNetworkImpl();
					NetworkHelper nh = new NetworkHelper();
					nh.readTrainSetFromFile("src/main/resources/trainSet.txt");
					nn.reset(nh.getTrainSet(), nh.getAnswers());
					ErrorWindow error = new ErrorWindow("Налаштування за замовчуванням успішно прийняті");
					error.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
					ErrorWindow error = new ErrorWindow(e.getLocalizedMessage());
					error.setVisible(true);
				} catch (URISyntaxException e) {
					e.printStackTrace();
					ErrorWindow error = new ErrorWindow(e.getLocalizedMessage());
					error.setVisible(true);
				}
			}
		});
		btnResetNeuralNetwork.setBounds(10, 36, 254, 23);
		pCenter.add(btnResetNeuralNetwork);
		
		JButton btnClose = new JButton("\u0417\u0430\u043A\u0440\u0438\u0442\u0438");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnClose.setBounds(175, 158, 89, 23);
		pCenter.add(btnClose);
		
		JButton button = new JButton("\u0414\u043E\u0432\u0447\u0438\u0442\u0438");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NeuralNetwork nn = new NeuralNetworkImpl();
				NetworkHelper nh = new NetworkHelper();
				try {
					nh.readTrainSetFromFile("src/main/resources/trainSet.txt");
					nn.train(nh.getTrainSet(), nh.getAnswers(), 500_000);
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
				ErrorWindow error = new ErrorWindow("Налаштування успішно прийняті");
				error.setVisible(true);
			}
		});
		button.setBounds(10, 70, 254, 23);
		pCenter.add(button);

	}
}

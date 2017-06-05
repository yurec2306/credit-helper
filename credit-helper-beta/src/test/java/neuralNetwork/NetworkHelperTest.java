package neuralNetwork;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Test;

import main.IndividualModel;
import main.IndividualModel.FieldOfActivity;
import main.IndividualModel.MaritialStatus;
import main.IndividualModel.Qualification;

public class NetworkHelperTest {

	private final static String WEIGHTS_PATH = ".\\src\\test\\resources\\NeuralNetworkSinapsWeights";
	private final static String DELTA_WEIGHTS_PATH = ".\\src\\test\\resources\\deltaWeights";
	private final static String TRAIN_SET_PATH = ".\\src\\test\\resources\\trainSet.txt";

	@Test
	public void formToNeuron_correctIndividualModel_correctNeuronLayer() {
		IndividualModel model = new IndividualModel();
		model.setAge(40);
		model.setMaritialStatus(MaritialStatus.SINGLE);
		model.setChildrenNum(0);
		model.setFieldOfActivity(FieldOfActivity.CIVIL_SERVICE);
		model.setQualification(Qualification.SPECIALIST);
		model.setYearsOfWorkExperience(5);
		model.setMonthlyIncome(10000);
		NetworkHelper nh = new NetworkHelper();
		Neuron[] neurons = { new NeuronNoSigmoidImpl(0.6666667f), new NeuronNoSigmoidImpl(0.6f),
				new NeuronNoSigmoidImpl(0.8f), new NeuronNoSigmoidImpl(0.5f), new NeuronNoSigmoidImpl(0.4f),
				new NeuronNoSigmoidImpl(0.8f), new NeuronNoSigmoidImpl(0.8f) , new NeuronBiasImpl()};
		NeuronInputLayer testLayer = new NeuronInputLayer(neurons);

		NeuronInputLayer layer = nh.formToNeuron(model);

		assertEquals(testLayer, layer);
	}

	@Test
	public void initNetwork_correctArrayAndFileExist_returnesCorrectArray() throws IOException {
		float[][][] testWeight = new float[5][5][5];
		float[][][] expected = new float[5][5][5];
		File file = new File(WEIGHTS_PATH);
		if (file.exists()) file.delete();
		file.createNewFile();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < expected.length; i++) {
				for (int j = 0; j < expected[i].length; j++) {
					for (int k = 0; k < expected[i][j].length; k++) {
						expected[i][j][k] = i * j * k;
						oos.writeFloat(expected[i][j][k]);
					}
				}
			}
			oos.flush();
		}

		testWeight = NetworkHelper.initNetwork(testWeight, WEIGHTS_PATH);

		for (int i = 0; i < expected.length; i++)
			for (int j = 0; j < expected.length; j++)
				assertArrayEquals(expected[i][j], testWeight[i][j], 0);
	}

	@Test
	public void saveNetwork_correctArrayAndFileExist_dataSavedToFile() throws IOException {
		float[][][] testWeight = {{{1}}};
		float[][][] expected = {{{1}}};
		File file = new File(WEIGHTS_PATH);
		if (file.exists()) file.delete();
		file.createNewFile();

		NetworkHelper.saveNetwork(testWeight, WEIGHTS_PATH);
		
		testWeight = NetworkHelper.initNetwork(testWeight, WEIGHTS_PATH);
		for (int i = 0; i < expected.length; i++)
			for (int j = 0; j < expected.length; j++)
				assertArrayEquals(expected[i][j], testWeight[i][j], 0);
	}
	
	@Test
	public void saveNetwork_correctArrayAndFileNotExist_dataSavedToFile() throws IOException {
		File file = new File(WEIGHTS_PATH);
		if (file.exists()) file.delete();
		float[][][] testWeight = {{{1}}};
		float[][][] expected = {{{1}}};
		
		NetworkHelper.saveNetwork(testWeight, WEIGHTS_PATH);
		
		testWeight = NetworkHelper.initNetwork(testWeight, WEIGHTS_PATH);
		for (int i = 0; i < expected.length; i++)
			for (int j = 0; j < expected.length; j++)
				assertArrayEquals(expected[i][j], testWeight[i][j], 0);
	}
	
	@Test
	public void readTrainSetFromFile_correctFile_ReturnsCorrectTrainSet() throws IOException {
		File file = new File(TRAIN_SET_PATH);
		if (file.exists()) file.delete();
		file.createNewFile();
		try(FileWriter fw = new FileWriter(file)) {
			fw.write("0.0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8");
			fw.flush();
		}
		Neuron[] neurons = {
				new NeuronNoSigmoidImpl(0.0f),
				new NeuronNoSigmoidImpl(0.1f),
				new NeuronNoSigmoidImpl(0.2f),
				new NeuronNoSigmoidImpl(0.3f),
				new NeuronNoSigmoidImpl(0.4f),
				new NeuronNoSigmoidImpl(0.5f),
				new NeuronNoSigmoidImpl(0.6f),
				new NeuronBiasImpl()
		};
		NeuronInputLayer[] expected = { new NeuronInputLayer(neurons) };
		NetworkHelper nh = new NetworkHelper();
		
		nh.readTrainSetFromFile(TRAIN_SET_PATH);
		
		assertArrayEquals(expected, nh.getTrainSet());
	}
	
	@Test
	public void initDeltaW_correctArrayAndFileExist_returnesCorrectArray() throws IOException {
		float[][][] testDeltaW = new float[5][5][5];
		float[][][] expected = new float[5][5][5];
		File file = new File(DELTA_WEIGHTS_PATH);
		if (file.exists()) file.delete();
		file.createNewFile();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < expected.length; i++) {
				for (int j = 0; j < expected[i].length; j++) {
					for (int k = 0; k < expected[i][j].length; k++) {
						expected[i][j][k] = i * j * k;
						oos.writeFloat(expected[i][j][k]);
					}
				}
			}
			oos.flush();
		}

		testDeltaW = NetworkHelper.initDeltaW(testDeltaW, DELTA_WEIGHTS_PATH);

		for (int i = 0; i < expected.length; i++)
			for (int j = 0; j < expected.length; j++)
				assertArrayEquals(expected[i][j], testDeltaW[i][j], 0);
	}
}

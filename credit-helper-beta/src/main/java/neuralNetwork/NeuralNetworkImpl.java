package neuralNetwork;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class NeuralNetworkImpl implements NeuralNetwork {

	/*
	 * Количество слоев нейронной сети. Первый - входной слой, 
	 * последний - выходной, остальные - скрытые слои.
	 */
	public final static int NEURAL_NETWORK_LAYERS = 3; // 3 <= X <= inf.
	public final static int INPUT_LAYER_SIZE = 7;
	public final static int HIDDEN_LAYER_SIZE = 14;
	public final static int OUTPUT_LAYER_SIZE = 2;
	
	private final static float LEARN_SPEED = 0.5f; // скорость обучения сети
	private final static float ALPHA = 1f; // момент
	
	private static NetworkHelper networkHelper;

	private NeuronLayer[] neuralNetwork; // нейронная сеть (матрица нейронов) [нейронный слой]
	private float[][][] networkWeights; // веса синапсов (связей между нейронами) [нейронный слой][индекс в слое][индекс в следующем слое]
	private float[][] delta; // дельта функции [нейронный слой][индекс в слое]
	private float[][][] grad; // градиент функции [нейронный слой][индекс в слое][индекс в следующем слое]
	private float[][][] deltaW; // [нейронный слой][индекс в слое][индекс в следующем слое]
	private int epochsNum = 1; // кол-во эпох (обучений)
	private int trainSetsNum; // размер набора последнего обучения
	private float errorRate = 1.0f; // вероятность ошибки (погрешность) 1.0 = 100%

	public NeuralNetworkImpl() {
		networkHelper = new NetworkHelper();
	}

	public void reset(NeuronLayer[] trainSet, float[][] answers) throws FileNotFoundException, IOException, URISyntaxException {
		reinitialize(trainSet);
	}
	
	public void train(NeuronLayer[] trainSet, float[][] answers, int epochs) throws FileNotFoundException, IOException, URISyntaxException {
		initialize(trainSet);
		
		float[] result = new float[OUTPUT_LAYER_SIZE];
		
		for (int epoch = 1; epoch < epochs; epoch++) {
			
			// высчитываем результат для каждого набора значений:
			for (int i = 0; i < trainSet.length; i++) {
				result = calculate(trainSet[i]);
				errorRate += countErrorRate(answers[i], result);
				recalcWeights(answers[i]);
			}
			errorRate /= (answers.length * answers[0].length);
			
			System.out.println("Эпоха: " + epochsNum + " Ошибка: " + errorRate);
			
			if (errorRate <= 0.005)
				return;
			
			++epochsNum; // +1 к количеству эпох
		}
		
		networkHelper.saveNetwork(networkWeights);
		networkHelper.saveDeltaW(deltaW);
	}

	public float[] run(NeuronLayer neuronLayer) throws FileNotFoundException, IOException, URISyntaxException {
		initialize(neuronLayer);
		return calculate(neuronLayer);
	}
	
	private void reinitialize(NeuronLayer[] trainSet) throws FileNotFoundException, IOException, URISyntaxException {
		trainSetsNum = trainSet.length;

		initializeNeuralNetwork(trainSet);

		initializeNetworkWeights();
		networkWeights = networkHelper.reinitNetwork(networkWeights); // загрузить значения весов

		initializeDeltaW();
		deltaW = networkHelper.reinitDeltaW(deltaW);		
	}

	private void initialize(NeuronLayer neuronLayer) throws FileNotFoundException, IOException, URISyntaxException {
		trainSetsNum = 1;

		initializeNeuralNetwork(neuronLayer);

		initializeNetworkWeights();
		networkWeights = networkHelper.initNetwork(networkWeights); // загрузить значения весов
	}
	
	private void initialize(NeuronLayer[] trainSet) throws FileNotFoundException, IOException, URISyntaxException {
		trainSetsNum = trainSet.length;

		initializeNeuralNetwork(trainSet);

		initializeNetworkWeights();
		networkWeights = networkHelper.initNetwork(networkWeights); // загрузить значения весов
		
		initializeDelta();
		
		initializeGradient();
		
		initializeDeltaW();
		deltaW = networkHelper.initDeltaW(deltaW);
	}

	private void initializeNeuralNetwork(NeuronLayer neuronInputLayer) {
		neuralNetwork = new NeuronLayer[NEURAL_NETWORK_LAYERS];
		neuralNetwork[0] = new NeuronFirstLayer(neuronInputLayer.size());
		for (int i = 1; i < neuralNetwork.length; i++) {
			if (i < neuralNetwork.length - 1) {
				neuralNetwork[i] = new NeuronHiddenLayer(HIDDEN_LAYER_SIZE);
			} else {
				neuralNetwork[i] = new NeuronHiddenLayer(OUTPUT_LAYER_SIZE);
			}
		}
	}
	
	private void initializeNeuralNetwork(NeuronLayer[] trainSet) {
		neuralNetwork = new NeuronLayer[NEURAL_NETWORK_LAYERS];
		neuralNetwork[0] = new NeuronHiddenLayer(trainSet[0].size());
		for (int i = 1; i < neuralNetwork.length; i++) {
			if (i < neuralNetwork.length - 1) {
				neuralNetwork[i] = new NeuronHiddenLayer(HIDDEN_LAYER_SIZE);
			} else {
				neuralNetwork[i] = new NeuronHiddenLayer(OUTPUT_LAYER_SIZE);
			}
		}
	}
	
	private void initializeNetworkWeights() {
		networkWeights = new float[neuralNetwork.length - 1][][];
		for (int i = 0; i < networkWeights.length; i++) {
			if (i < networkWeights.length - 1) {
				networkWeights[i] = new float[neuralNetwork[i].size() + 1][neuralNetwork[i + 1].size()];
			} else {
				networkWeights[i] = new float[neuralNetwork[i].size() + 1][OUTPUT_LAYER_SIZE];
			}
		}
	}
	
	private void initializeDelta() {
		delta = new float[neuralNetwork.length][];
		for (int i = 0; i < delta.length; i++) {
			delta[i] = new float[neuralNetwork[i].size()];
		}
	}

	private void initializeGradient() {
		grad = new float[networkWeights.length][][];
		for (int i = 0; i < grad.length; i++) {
			grad[i] = new float[networkWeights[i].length][];
			for (int j = 0; j < grad[i].length; j++) {
				grad[i][j] = new float[networkWeights[i][j].length];
			}
		}
	}
	
	private void initializeDeltaW() {
		deltaW = new float[networkWeights.length][][];
		for (int i = 0; i < deltaW.length; i++) {
			deltaW[i] = new float[networkWeights[i].length][];
			for (int j = 0; j < deltaW[i].length; j++) {
				deltaW[i][j] = new float[networkWeights[i][j].length];
			}
		}
	}
	
	public float[] calculate(NeuronLayer neuronLayer) throws FileNotFoundException, IOException, URISyntaxException {
		neuralNetwork[0] = neuronLayer;

		networkWeights = networkHelper.initNetwork(networkWeights);
		for (int i = 1; i < neuralNetwork.length; i++) {
			for (int j = 0; j < neuralNetwork[i].size(); j++) {
				for (int k = 0; k < neuralNetwork[i - 1].size(); k++) {
					neuralNetwork[i].getNeuron(j).addData(neuralNetwork[i - 1].getNeuron(k).getData() * networkWeights[i - 1][k][j]);
				}
				if(i < neuralNetwork.length - 1) {
					neuralNetwork[i].getNeuron(j).addData(networkWeights[i - 1][networkWeights[i-1].length-1][j]);
				}
			}
		}
		float[] results = new float[neuralNetwork[neuralNetwork.length - 1].size()];
		for(int i = 0; i < results.length; i++) {
			results[i] = neuralNetwork[neuralNetwork.length - 1].getNeuron(i).getData();
		}
		return results;
	}

	private float countErrorRate(float[] answers, float[] result) {
		float newErrorRate = 0f; // новая погрешность
		// расчет новой погрешности:
		for (int j = 0; j < result.length; j++) {
			newErrorRate += Math.pow(answers[j] - result[j], 2.0);
		}
		newErrorRate /= result.length;
		return newErrorRate;
	}

	private void recalcWeights(float[] answers) throws FileNotFoundException, IOException, URISyntaxException {
		// пересчет весов:
		for (int i = delta.length - 1; i >= 0; i--) {
			for (int j = 0; j < delta[i].length; j++) {
				if (i < delta.length - 1) {
					float sum = 0;
					for (int k = 0; k < networkWeights[i][j].length; k++) {
						sum += networkWeights[i][j][k] * delta[i + 1][k];
					}
					delta[i][j] = ((1 - neuralNetwork[i].getNeuron(j).getData()) * neuralNetwork[i].getNeuron(j).getData()) * sum;
				} else {
					delta[i][j] = (answers[j] - neuralNetwork[neuralNetwork.length - 1].getNeuron(j).getData()) *
							((1 - neuralNetwork[neuralNetwork.length - 1].getNeuron(j).getData()) *
									neuralNetwork[neuralNetwork.length - 1].getNeuron(j).getData());
				}
			}
		}
		// градиент:
		for (int i = 0; i < grad.length; i++) {
			for (int j = 0; j < grad[i].length; j++) {
				for (int k = 0; k < grad[i][j].length; k++) {
					if (j < grad[i].length - 1) {
						grad[i][j][k] = delta[i + 1][k] * neuralNetwork[i].getNeuron(j).getData();
					} else {
						grad[i][j][k] = delta[i + 1][k];
					}
					deltaW[i][j][k] = LEARN_SPEED * grad[i][j][k] + ALPHA * deltaW[i][j][k];
					networkWeights[i][j][k] += deltaW[i][j][k];
				}
			}
		}
	}

	public int getEpochsNum() {
		return epochsNum;
	}

	public int getTrainSetsNum() {
		return trainSetsNum;
	}

	public float getError() {
		return errorRate;
	}

}

package neuralNetwork;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NeuralNetworkImpl implements NeuralNetwork {
	
	private static Logger logger = LoggerFactory.getLogger(NeuralNetworkImpl.class);

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
	
	private NeuronLayer[] neuralNetwork; // нейронная сеть (матрица нейронов) [нейронный слой]
	private float[][][] networkWeights; // веса синапсов (связей между нейронами) [нейронный слой][индекс в слое][индекс в следующем слое]
	private float[][] delta; // дельта функции [нейронный слой][индекс в слое]
	private float[][][] grad; // градиент функции [нейронный слой][индекс в слое][индекс в следующем слое]
	private float[][][] deltaW; // [нейронный слой][индекс в слое][индекс в следующем слое]
	private int epochsNum = 1; // кол-во эпох (обучений)
	private int trainSetsNum; // размер набора последнего обучения
	private float errorRate = 1.0f; // вероятность ошибки (погрешность) 1.0 = 100%

	public NeuralNetworkImpl() {
		logger.trace("Calling NeuralNetworkImpl()");
		logger.trace("Returning from NeuralNetworkImpl()");
	}
	
	@Override
	public void reset(NeuronLayer[] trainSet, float[][] answers) throws FileNotFoundException, IOException, URISyntaxException {
		logger.trace("Calling reset({}, {})", trainSet, answers);
		reinitialize(trainSet);
		logger.trace("Returning from reset({}, {})", trainSet, answers);
	}
	
	@Override
	public void train(NeuronLayer[] trainSet, float[][] answers, int epochs) throws FileNotFoundException, IOException, URISyntaxException {
		logger.trace("Calling train({}, {}, {})", trainSet, answers, epochs);
		logger.info("Starting train Neural Network");
		
		initialize(trainSet);
		
		float[] result = new float[OUTPUT_LAYER_SIZE];
		
		for (int epoch = 1; epoch < epochs; epoch++) {
			
			// высчитываем результат для каждого набора значений:
			for (int i = 0; i < trainSet.length; i++) {
				result = calculate(trainSet[i]);
				this.errorRate += countErrorRate(answers[i], result);
				recalcWeights(answers[i]);
			}
			this.errorRate /= (answers.length * answers[0].length);
			
			logger.info("Epoch: " + this.epochsNum + " Error Rate: " + this.errorRate);
			
			if (this.errorRate <= 0.005)
				return;
			
			++this.epochsNum; // +1 к количеству эпох
		}
		
		NetworkHelper.saveNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH);
		NetworkHelper.saveDeltaW(this.deltaW, NetworkHelper.DELTA_WEIGHTS_PATH);
		
		logger.info("Neural Network train finished");
		logger.trace("Returning from train({}, {}, {})", trainSet, answers, epochs);
	}

	@Override
	public float[] run(NeuronLayer neuronLayer) throws FileNotFoundException, IOException, URISyntaxException {
		logger.trace("Calling run({})", neuronLayer);
		initialize(neuronLayer);
		logger.trace("Returning from run({})", neuronLayer);
		return calculate(neuronLayer);
	}
	
	private void reinitialize(NeuronLayer[] trainSet) throws FileNotFoundException, IOException {
		logger.trace("Calling reinitialize({})", trainSet);
		
		this.trainSetsNum = trainSet.length;

		initializeNeuralNetwork(trainSet);

		initializeNetworkWeights();
		this.networkWeights = NetworkHelper.reinitNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH); // загрузить значения весов

		initializeDeltaW();
		this.deltaW = NetworkHelper.reinitDeltaW(this.deltaW, NetworkHelper.DELTA_WEIGHTS_PATH);
		
		logger.trace("Returning from reinitialize({})", trainSet);
	}

	private void initialize(NeuronLayer neuronLayer) throws FileNotFoundException, IOException {
		logger.trace("Calling reinitialize({})", neuronLayer);
		
		this.trainSetsNum = 1;

		initializeNeuralNetwork(neuronLayer);

		initializeNetworkWeights();
		this.networkWeights = NetworkHelper.initNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH); // загрузить значения весов
		
		logger.trace("Returning from reinitialize({})", neuronLayer);
	}
	
	private void initialize(NeuronLayer[] trainSet) throws FileNotFoundException, IOException {
		logger.trace("Calling reinitialize({})", trainSet);
		
		this.trainSetsNum = trainSet.length;

		initializeNeuralNetwork(trainSet);

		initializeNetworkWeights();
		this.networkWeights = NetworkHelper.initNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH); // загрузить значения весов
		
		initializeDelta();
		
		initializeGradient();
		
		initializeDeltaW();
		this.deltaW = NetworkHelper.initDeltaW(this.deltaW, NetworkHelper.DELTA_WEIGHTS_PATH);
		
		logger.trace("Returning from reinitialize({})", trainSet);
	}

	private void initializeNeuralNetwork(NeuronLayer neuronInputLayer) {
		logger.trace("Calling initializeNeuralNetwork({})", neuronInputLayer);
		
		this.neuralNetwork = new NeuronLayer[NEURAL_NETWORK_LAYERS];
		this.neuralNetwork[0] = new NeuronFirstLayer(neuronInputLayer.size());
		for (int i = 1; i < this.neuralNetwork.length; i++) {
			if (i < this.neuralNetwork.length - 1) {
				this.neuralNetwork[i] = new NeuronHiddenLayer(HIDDEN_LAYER_SIZE);
			} else {
				this.neuralNetwork[i] = new NeuronHiddenLayer(OUTPUT_LAYER_SIZE);
			}
		}
		
		logger.trace("Returning from initializeNeuralNetwork({})", neuronInputLayer);
	}
	
	private void initializeNeuralNetwork(NeuronLayer[] trainSet) {
		logger.trace("Calling initializeNeuralNetwork({})", trainSet);
		
		this.neuralNetwork = new NeuronLayer[NEURAL_NETWORK_LAYERS];
		this.neuralNetwork[0] = new NeuronHiddenLayer(trainSet[0].size());
		for (int i = 1; i < this.neuralNetwork.length; i++) {
			if (i < this.neuralNetwork.length - 1) {
				this.neuralNetwork[i] = new NeuronHiddenLayer(HIDDEN_LAYER_SIZE);
			} else {
				this.neuralNetwork[i] = new NeuronHiddenLayer(OUTPUT_LAYER_SIZE);
			}
		}
		
		logger.trace("Returning from initializeNeuralNetwork({})", trainSet);
	}
	
	private void initializeNetworkWeights() {
		logger.trace("Calling initializeNetworkWeights()");
		
		this.networkWeights = new float[this.neuralNetwork.length - 1][][];
		for (int i = 0; i < this.networkWeights.length; i++) {
			if (i < this.networkWeights.length - 1) {
				this.networkWeights[i] = new float[this.neuralNetwork[i].size() + 1][this.neuralNetwork[i + 1].size()];
			} else {
				this.networkWeights[i] = new float[this.neuralNetwork[i].size() + 1][OUTPUT_LAYER_SIZE];
			}
		}
		
		logger.trace("Returning from initializeNetworkWeights()");
	}
	
	private void initializeDelta() {
		logger.trace("Calling initializeDelta()");
		this.delta = new float[this.neuralNetwork.length][];
		for (int i = 0; i < this.delta.length; i++) {
			this.delta[i] = new float[this.neuralNetwork[i].size()];
		}
		logger.trace("Returning from initializeDelta()");
	}

	private void initializeGradient() {
		logger.trace("Calling initializeGradient()");
		this.grad = new float[this.networkWeights.length][][];
		for (int i = 0; i < this.grad.length; i++) {
			this.grad[i] = new float[this.networkWeights[i].length][];
			for (int j = 0; j < this.grad[i].length; j++) {
				this.grad[i][j] = new float[this.networkWeights[i][j].length];
			}
		}
		logger.trace("Returning from initializeGradient()");
	}
	
	private void initializeDeltaW() {
		logger.trace("Calling initializeDeltaW()");
		this.deltaW = new float[this.networkWeights.length][][];
		for (int i = 0; i < this.deltaW.length; i++) {
			this.deltaW[i] = new float[this.networkWeights[i].length][];
			for (int j = 0; j < this.deltaW[i].length; j++) {
				this.deltaW[i][j] = new float[this.networkWeights[i][j].length];
			}
		}
		logger.trace("Returning from initializeDeltaW()");
	}
	
	public float[] calculate(NeuronLayer neuronLayer) throws FileNotFoundException, IOException {
		logger.trace("Calling calculate({})", neuronLayer);
		
		this.neuralNetwork[0] = neuronLayer;

		this.networkWeights = NetworkHelper.initNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH);
		for (int i = 1; i < this.neuralNetwork.length; i++) {
			for (int j = 0; j < this.neuralNetwork[i].size(); j++) {
				for (int k = 0; k < this.neuralNetwork[i - 1].size(); k++) {
					this.neuralNetwork[i].getNeuron(j).addData(this.neuralNetwork[i - 1].getNeuron(k).getData() * this.networkWeights[i - 1][k][j]);
				}
				if(i < this.neuralNetwork.length - 1) {
					this.neuralNetwork[i].getNeuron(j).addData(this.networkWeights[i - 1][this.networkWeights[i-1].length-1][j]);
				}
			}
		}
		float[] results = new float[this.neuralNetwork[this.neuralNetwork.length - 1].size()];
		for(int i = 0; i < results.length; i++) {
			results[i] = this.neuralNetwork[this.neuralNetwork.length - 1].getNeuron(i).getData();
		}
		logger.debug("Output: {}", results);
		
		logger.trace("Returning from calculate({})", neuronLayer);
		return results;
	}

	private static float countErrorRate(float[] answers, float[] result) {
		logger.trace("Calling countErrorRate({}, {})", answers, result);
		float newErrorRate = 0f; // новая погрешность
		// расчет новой погрешности:
		for (int j = 0; j < result.length; j++) {
			newErrorRate += Math.pow(answers[j] - result[j], 2.0);
		}
		newErrorRate /= result.length;
		logger.trace("Returning from countErrorRate({}, {})", answers, result);
		return newErrorRate;
	}

	private void recalcWeights(float[] answers) {
		logger.trace("Calling recalcWeights({})", answers);
		// пересчет весов:
		for (int i = this.delta.length - 1; i >= 0; i--) {
			for (int j = 0; j < this.delta[i].length; j++) {
				if (i < this.delta.length - 1) {
					float sum = 0;
					for (int k = 0; k < this.networkWeights[i][j].length; k++) {
						sum += this.networkWeights[i][j][k] * this.delta[i + 1][k];
					}
					this.delta[i][j] = ((1 - this.neuralNetwork[i].getNeuron(j).getData()) * this.neuralNetwork[i].getNeuron(j).getData()) * sum;
				} else {
					this.delta[i][j] = (answers[j] - this.neuralNetwork[this.neuralNetwork.length - 1].getNeuron(j).getData()) *
							((1 - this.neuralNetwork[this.neuralNetwork.length - 1].getNeuron(j).getData()) *
									this.neuralNetwork[this.neuralNetwork.length - 1].getNeuron(j).getData());
				}
				logger.debug("delta[{}][{}] = {}", i, j, delta[i][j]);
			}
		}
		// градиент:
		for (int i = 0; i < this.grad.length; i++) {
			for (int j = 0; j < this.grad[i].length; j++) {
				for (int k = 0; k < this.grad[i][j].length; k++) {
					if (j < this.grad[i].length - 1) {
						this.grad[i][j][k] = this.delta[i + 1][k] * this.neuralNetwork[i].getNeuron(j).getData();
					} else {
						this.grad[i][j][k] = this.delta[i + 1][k];
					}
					logger.debug("grad[{}][{}][{}] = {}",i,j,k, grad[i][j][k]);
					this.deltaW[i][j][k] = LEARN_SPEED * this.grad[i][j][k] + ALPHA * this.deltaW[i][j][k];
					logger.debug("deltaW[{}][{}][{}] = {}",i,j,k, deltaW[i][j][k]);
					this.networkWeights[i][j][k] += this.deltaW[i][j][k];
					logger.debug("networkWeights[{}][{}][{}] = {}",i,j,k, networkWeights[i][j][k]);
				}
			}
		}
		logger.trace("Returning from recalcWeights({})", answers);
	}

	@Override
	public int getEpochsNum() {
		return this.epochsNum;
	}

	@Override
	public int getTrainSetsNum() {
		return this.trainSetsNum;
	}

	@Override
	public float getError() {
		return this.errorRate;
	}

}
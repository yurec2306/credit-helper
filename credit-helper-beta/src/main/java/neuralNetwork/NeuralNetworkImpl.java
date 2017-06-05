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
	public final static int HIDDEN_LAYER_SIZE = 7;
	public final static int OUTPUT_LAYER_SIZE = 1;
	
	private final static float LEARN_SPEED = 0.0005f; // скорость обучения сети
	private final static float ALPHA = 0.001f; // момент
	
	private NeuronLayer[] neuralNetwork; // нейронная сеть (матрица нейронов) [нейронный слой]
	private float[][][] networkWeights; // веса синапсов (связей между нейронами) [нейронный слой][индекс в слое][индекс в следующем слое]
	private float[][] delta; // дельта функции [нейронный слой][индекс в слое]
	private float[][][] deltaW; // [нейронный слой][индекс в слое][индекс в следующем слое]
	private int epochsNum = 1; // кол-во эпох (обучений)
	private int trainSetsNum; // размер набора последнего обучения
	private float errorRate = 1.0f; // вероятность ошибки (погрешность) 1.0 = 100%

	public NeuralNetworkImpl() {
		logger.trace("Calling NeuralNetworkImpl()");
		logger.trace("Returning from NeuralNetworkImpl()");
	}
	
	@Override
	public void reset(NeuronInputLayer[] trainSet, float[][] answers) throws FileNotFoundException, IOException, URISyntaxException {
		logger.trace("Calling reset({}, {})", trainSet, answers);
		reinitialize(trainSet);
		logger.trace("Returning from reset({}, {})", trainSet, answers);
	}
	
	@Override
	public void train(NeuronInputLayer[] trainSet, float[][] answers, int epochs) throws FileNotFoundException, IOException, URISyntaxException {
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
	public float[] run(NeuronInputLayer neuronLayer) throws FileNotFoundException, IOException, URISyntaxException {
		logger.trace("Calling run({})", neuronLayer);
		initialize(neuronLayer);
		logger.trace("Returning from run({})", neuronLayer);
		return calculate(neuronLayer);
	}
	
	private void reinitialize(NeuronInputLayer[] trainSet) throws FileNotFoundException, IOException {
		logger.trace("Calling reinitialize({})", trainSet);
		
		this.trainSetsNum = trainSet.length;

		initializeNeuralNetwork(trainSet);

		initializeNetworkWeights();
		this.networkWeights = NetworkHelper.reinitNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH); // загрузить значения весов

		initializeDeltaW();
		this.deltaW = NetworkHelper.reinitDeltaW(this.deltaW, NetworkHelper.DELTA_WEIGHTS_PATH);
		
		logger.trace("Returning from reinitialize({})", trainSet);
	}

	private void initialize(NeuronInputLayer neuronLayer) throws FileNotFoundException, IOException {
		logger.trace("Calling reinitialize({})", neuronLayer);
		
		this.trainSetsNum = 1;

		initializeNeuralNetwork(neuronLayer);

		initializeNetworkWeights();
		this.networkWeights = NetworkHelper.initNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH); // загрузить значения весов
		
		logger.trace("Returning from reinitialize({})", neuronLayer);
	}
	
	private void initialize(NeuronInputLayer[] trainSet) throws FileNotFoundException, IOException {
		logger.trace("Calling reinitialize({})", trainSet);
		
		this.trainSetsNum = trainSet.length;

		initializeNeuralNetwork(trainSet);

		initializeNetworkWeights();
		this.networkWeights = NetworkHelper.initNetwork(this.networkWeights, NetworkHelper.WEIGHTS_PATH); // загрузить значения весов
		
		initializeDelta();
		
		initializeDeltaW();
		this.deltaW = NetworkHelper.initDeltaW(this.deltaW, NetworkHelper.DELTA_WEIGHTS_PATH);
		
		logger.trace("Returning from reinitialize({})", trainSet);
	}

	private void initializeNeuralNetwork(NeuronLayer neuronInputLayer) {
		logger.trace("Calling initializeNeuralNetwork({})", neuronInputLayer);
		
		this.neuralNetwork = new NeuronLayer[NEURAL_NETWORK_LAYERS];
		this.neuralNetwork[0] = new NeuronInputLayer(INPUT_LAYER_SIZE);
		for (int layerIndex = 1; layerIndex < this.neuralNetwork.length; layerIndex++) {
			if (layerIndex < this.neuralNetwork.length - 1) {
				this.neuralNetwork[layerIndex] = new NeuronHiddenLayer(HIDDEN_LAYER_SIZE);
			} else {
				this.neuralNetwork[layerIndex] = new NeuronOutputLayer(OUTPUT_LAYER_SIZE);
			}
		}
		
		logger.trace("Returning from initializeNeuralNetwork({})", neuronInputLayer);
	}
	
	private void initializeNeuralNetwork(NeuronInputLayer[] trainSet) {
		logger.trace("Calling initializeNeuralNetwork({})", trainSet);
		
		this.neuralNetwork = new NeuronLayer[NEURAL_NETWORK_LAYERS];
		this.neuralNetwork[0] = new NeuronInputLayer(trainSet[0].dataSize());
		for (int layerIndex = 1; layerIndex < this.neuralNetwork.length; layerIndex++) {
			if (layerIndex < this.neuralNetwork.length - 1) {
				this.neuralNetwork[layerIndex] = new NeuronHiddenLayer(HIDDEN_LAYER_SIZE);
			} else {
				this.neuralNetwork[layerIndex] = new NeuronOutputLayer(OUTPUT_LAYER_SIZE);
			}
		}
		
		logger.trace("Returning from initializeNeuralNetwork({})", trainSet);
	}
	
	private void initializeNetworkWeights() {
		logger.trace("Calling initializeNetworkWeights()");
		
		this.networkWeights = new float[this.neuralNetwork.length - 1][][];
		for (int layerIndex = 0; layerIndex < this.networkWeights.length; layerIndex++) {
			if (layerIndex < this.networkWeights.length - 1) {
				this.networkWeights[layerIndex] = new float[this.neuralNetwork[layerIndex].size()][this.neuralNetwork[layerIndex + 1].size()];
			} else {
				this.networkWeights[layerIndex] = new float[this.neuralNetwork[layerIndex].size()][OUTPUT_LAYER_SIZE];
			}
		}
		
		logger.trace("Returning from initializeNetworkWeights()");
	}
	
	private void initializeDelta() {
		logger.trace("Calling initializeDelta()");
		this.delta = new float[this.neuralNetwork.length][];
		for (int layerIndex = 0; layerIndex < this.delta.length; layerIndex++) {
			this.delta[layerIndex] = new float[this.neuralNetwork[layerIndex].size()];
		}
		logger.trace("Returning from initializeDelta()");
	}
	
	private void initializeDeltaW() {
		logger.trace("Calling initializeDeltaW()");
		this.deltaW = new float[this.networkWeights.length][][];
		for (int layerIndex = 0; layerIndex < this.deltaW.length; layerIndex++) {
			this.deltaW[layerIndex] = new float[this.networkWeights[layerIndex].length][];
			for (int neuronIndex = 0; neuronIndex < this.deltaW[layerIndex].length; neuronIndex++) {
				this.deltaW[layerIndex][neuronIndex] = new float[this.networkWeights[layerIndex][neuronIndex].length];
			}
		}
		logger.trace("Returning from initializeDeltaW()");
	}
	
	public float[] calculate(NeuronLayer neuronLayer) {
		logger.trace("Calling calculate({})", neuronLayer);
		
		this.neuralNetwork[0] = neuronLayer;

		for (int layerIndex = 1; layerIndex < this.neuralNetwork.length; layerIndex++) {
			for (int neuronIndex = 0; neuronIndex < this.neuralNetwork[layerIndex].size(); neuronIndex++) {
				for (int prevNeuronIndex = 0; prevNeuronIndex < this.neuralNetwork[layerIndex - 1].size(); prevNeuronIndex++) {
					this.neuralNetwork[layerIndex].getNeuron(neuronIndex).addData(this.neuralNetwork[layerIndex - 1].getNeuron(prevNeuronIndex).getData() * this.networkWeights[layerIndex - 1][prevNeuronIndex][neuronIndex]);
				}
			}
		}
		int neuralNetworkLastLayerIndex = this.neuralNetwork.length - 1;
		float[] results = new float[this.neuralNetwork[neuralNetworkLastLayerIndex].size()];
		for(int i = 0; i < results.length; i++) {
			results[i] = this.neuralNetwork[neuralNetworkLastLayerIndex].getNeuron(i).getData();
		}
		logger.debug("Output: {}", results);
		
		logger.trace("Returning from calculate({})", neuronLayer);
		return results;
	}

	private static float countErrorRate(float[] answers, float[] result) {
		logger.trace("Calling countErrorRate({}, {})", answers, result);
		float newErrorRate = 0f; // новая погрешность
		// расчет новой погрешности:
		for (int i = 0; i < result.length; i++) {
			newErrorRate += Math.pow(answers[i] - result[i], 2.0);
		}
		newErrorRate /= 2;
		logger.trace("Returning from countErrorRate({}, {})", answers, result);
		return newErrorRate;
	}

	private void recalcWeights(float[] answers) {
		logger.trace("Calling recalcWeights({})", answers);
		int lastLayerIndex = this.neuralNetwork.length - 1;
		float grad = 0;
		// пересчет весов:
		for (int layerIndex = this.delta.length - 1; layerIndex >= 0; layerIndex--) {
			for (int neuronIndex = 0; neuronIndex < this.delta[layerIndex].length; neuronIndex++) {
				if (layerIndex < this.delta.length - 1) {
					float sum = 0;
					for (int nextNeuron = 0; nextNeuron < this.networkWeights[layerIndex][neuronIndex].length; nextNeuron++) {
						sum += this.networkWeights[layerIndex][neuronIndex][nextNeuron] * this.delta[layerIndex + 1][nextNeuron];
					}
					this.delta[layerIndex][neuronIndex] = getSigmoidDerivative(this.neuralNetwork[layerIndex].getNeuron(neuronIndex).getData()) * sum;
				} else {
					this.delta[layerIndex][neuronIndex] = (answers[neuronIndex] - this.neuralNetwork[lastLayerIndex].getNeuron(neuronIndex).getData()) *
							getSigmoidDerivative(this.neuralNetwork[lastLayerIndex].getNeuron(neuronIndex).getData());
				}
				logger.debug("delta[{}][{}] = {}", layerIndex, neuronIndex, this.delta[layerIndex][neuronIndex]);
				
				if (layerIndex == lastLayerIndex) continue;
				for (int nextNeuronIndex = 0; nextNeuronIndex < this.deltaW[layerIndex][neuronIndex].length; nextNeuronIndex++) {
					if (neuronIndex < this.deltaW[layerIndex].length - 1) {
						grad = this.delta[layerIndex + 1][nextNeuronIndex] * this.neuralNetwork[layerIndex].getNeuron(neuronIndex).getData();
					} else {
						grad = this.delta[layerIndex + 1][nextNeuronIndex];
					}	
					logger.debug("grad = {}", grad);
					
					this.deltaW[layerIndex][neuronIndex][nextNeuronIndex] = LEARN_SPEED * grad + ALPHA * this.deltaW[layerIndex][neuronIndex][nextNeuronIndex];
					logger.debug("deltaW[{}][{}][{}] = {}",layerIndex,neuronIndex,nextNeuronIndex, this.deltaW[layerIndex][neuronIndex][nextNeuronIndex]);
					
					this.networkWeights[layerIndex][neuronIndex][nextNeuronIndex] += this.deltaW[layerIndex][neuronIndex][nextNeuronIndex];	
					logger.debug("networkWeights[{}][{}][{}] = {}",layerIndex,neuronIndex,nextNeuronIndex, this.networkWeights[layerIndex][neuronIndex][nextNeuronIndex]);
				}
			}
		}
		logger.trace("Returning from recalcWeights({})", answers);
	}

	public static float getSigmoidDerivative(float input) {
		return (1 - input) * input;
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
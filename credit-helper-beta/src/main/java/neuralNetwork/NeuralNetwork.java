package neuralNetwork;

import java.io.IOException;
import java.net.URISyntaxException;

public interface NeuralNetwork {

	public void train(NeuronInputLayer[] trainSet, float[][] answers, int epochs) throws IOException, URISyntaxException;
	
	public void reset(NeuronInputLayer[] trainSet, float[][] answers) throws IOException, URISyntaxException;
	
	public float[] run(NeuronInputLayer nl) throws IOException, URISyntaxException;

	public int getEpochsNum();

	public int getTrainSetsNum();

	public float getError();
	
}

package neuralNetwork;

import java.io.IOException;
import java.net.URISyntaxException;

public interface NeuralNetwork {

	public void train(NeuronLayer[] trainSet, float[][] answers, int epochs) throws IOException, URISyntaxException;
	
	public void reset(NeuronLayer[] trainSet, float[][] answers) throws IOException, URISyntaxException;
	
	public float[] run(NeuronLayer nl) throws IOException, URISyntaxException;

	public int getEpochsNum();

	public int getTrainSetsNum();

	public float getError();
	
}

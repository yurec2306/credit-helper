package neuralNetwork;

public interface NeuronLayer {

	public Neuron getNeuron(int index);

	public void setNeuron(int index, Neuron neuron);

	public int size();
	
}

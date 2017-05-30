package neuralNetwork;

import java.util.Arrays;

public class NeuronHiddenLayer implements NeuronLayer {
	
	private Neuron[] layer;
	
	public NeuronHiddenLayer(int neurons) {
		layer = new Neuron[neurons];
		for(int i = 0; i < layer.length; i++) {
			layer[i] = new NeuronImpl();
		}
	}
	
	public NeuronHiddenLayer(Neuron[] neurons) {
		layer = neurons;
	}
	
	public Neuron getNeuron(int index) {
		return layer[index];
	}
	
	public void setNeuron(int index, Neuron neuron) {
		this.layer[index] = neuron;
	}
	
	public int size() {
		return layer.length;
	}

	@Override
	public String toString() {
		return "[" + Arrays.toString(layer) + "]";
	}
	
}

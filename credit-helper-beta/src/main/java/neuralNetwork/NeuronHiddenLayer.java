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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(layer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NeuronHiddenLayer))
			return false;
		NeuronHiddenLayer other = (NeuronHiddenLayer) obj;
		if (!Arrays.equals(layer, other.layer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + Arrays.toString(layer) + "]";
	}
	
}

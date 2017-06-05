package neuralNetwork;

import java.util.Arrays;

public class NeuronOutputLayer implements NeuronLayer {

	private Neuron[] layer;
	
	public NeuronOutputLayer(int neurons) {
		this.layer = new Neuron[neurons];
		for(int i = 0; i < this.layer.length; i++) {
			this.layer[i] = new NeuronImpl();
		}
	}
	
	@Override
	public Neuron getNeuron(int index) {
		return this.layer[index];
	}
	
	@Override
	public void setNeuron(int index, Neuron neuron) {
		this.layer[index] = neuron;
	}
	
	@Override
	public int size() {
		return this.layer.length;
	}
	
	public int dataSize() {
		return this.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.layer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NeuronOutputLayer))
			return false;
		NeuronOutputLayer other = (NeuronOutputLayer) obj;
		if (!Arrays.equals(this.layer, other.layer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + Arrays.toString(this.layer) + "]";
	}
	
}

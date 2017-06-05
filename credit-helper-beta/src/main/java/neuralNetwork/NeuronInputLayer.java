package neuralNetwork;

import java.util.Arrays;

public class NeuronInputLayer implements NeuronLayer {

	private Neuron[] layer;

	public NeuronInputLayer(int neurons) {
		this.layer = new Neuron[neurons + 1];
		for (int i = 0; i < this.layer.length - 1; i++) {
			this.layer[i] = new NeuronNoSigmoidImpl();
		}
		this.layer[this.layer.length - 1] = new NeuronBiasImpl();
	}

	public NeuronInputLayer(Neuron[] neurons) {
		this.layer = neurons;
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
		return this.layer.length - 1;
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
		if (!(obj instanceof NeuronInputLayer))
			return false;
		NeuronInputLayer other = (NeuronInputLayer) obj;
		if (!Arrays.equals(this.layer, other.layer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + Arrays.toString(this.layer) + "]";
	}

}

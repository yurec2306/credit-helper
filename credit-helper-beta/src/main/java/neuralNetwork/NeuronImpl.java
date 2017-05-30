package neuralNetwork;

public class NeuronImpl implements Neuron {
	
	private float inputData = 0f;

	public NeuronImpl(float inputData) {
		this.inputData = inputData;
	}

	public NeuronImpl() {
	}

	public void setData(float inputData) {
		this.inputData = inputData;
	}
	
	public float getData() {
		return sigmoid(inputData);
	}

	public void addData(float inputData) {
		this.inputData += inputData;		
	}

	@Override
	public String toString() {
		return "" + sigmoid(inputData);
	}
	
	private static float sigmoid(float number) {
		return (float) (1.0f / (1.0f + Math.pow(Math.E, -number)));
	}

}

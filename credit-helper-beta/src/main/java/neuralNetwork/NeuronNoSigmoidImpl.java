package neuralNetwork;

public class NeuronNoSigmoidImpl implements Neuron {

	private float inputData = 0f;

	public NeuronNoSigmoidImpl() {
	}
	
	public NeuronNoSigmoidImpl(float inputData) {
		this.inputData = inputData;
	}
	
	public void setData(float inputData) {
		this.inputData = inputData;
	}
	
	public float getData() {
		return inputData;
	}

	public void addData(float inputData) {
		this.inputData  += inputData;	
	}

	@Override
	public String toString() {
		return ""+inputData;
	}

}

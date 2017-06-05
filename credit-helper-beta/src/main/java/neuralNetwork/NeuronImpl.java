package neuralNetwork;

public class NeuronImpl implements Neuron {
	
	private float inputData = 0f;

	public NeuronImpl(float inputData) {
		this.inputData = inputData;
	}

	public NeuronImpl() {
	}

	@Override
	public void setData(float inputData) {
		this.inputData = inputData;
	}
	
	@Override
	public float getData() {
		return sigmoid(this.inputData);
	}

	@Override
	public void addData(float inputData) {
		this.inputData += inputData;		
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.inputData);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NeuronImpl))
			return false;
		NeuronImpl other = (NeuronImpl) obj;
		if (Float.floatToIntBits(this.inputData) != Float.floatToIntBits(other.inputData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + sigmoid(this.inputData);
	}
	
	private static float sigmoid(float number) {
		return (float) (1.0f / (1.0f + Math.pow(Math.E, -number)));
	}

}

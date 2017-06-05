package neuralNetwork;

public class NeuronNoSigmoidImpl implements Neuron {

	private float inputData = 0f;

	public NeuronNoSigmoidImpl() {
	}
	
	public NeuronNoSigmoidImpl(float inputData) {
		this.inputData = inputData;
	}
	
	@Override
	public void setData(float inputData) {
		this.inputData = inputData;
	}
	
	@Override
	public float getData() {
		return this.inputData;
	}

	@Override
	public void addData(float inputData) {
		this.inputData  += inputData;	
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
		if (!(obj instanceof NeuronNoSigmoidImpl))
			return false;
		NeuronNoSigmoidImpl other = (NeuronNoSigmoidImpl) obj;
		if (Float.floatToIntBits(this.inputData) != Float.floatToIntBits(other.inputData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+this.inputData;
	}

}

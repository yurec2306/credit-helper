package neuralNetwork;

public class NeuronBiasImpl implements Neuron {

	private final float inputData = 1.0f;
	
	public NeuronBiasImpl(float inputData) {
		// Do nothing
	}

	public NeuronBiasImpl() {
	}
	
	@Override
	public void setData(float inputData) {
		// Do nothing
	}

	@Override
	public float getData() {
		return this.inputData;
	}

	@Override
	public void addData(float inputData) {
		// Do nothing
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
		if (!(obj instanceof NeuronBiasImpl))
			return false;
		NeuronBiasImpl other = (NeuronBiasImpl) obj;
		if (Float.floatToIntBits(this.inputData) != Float.floatToIntBits(other.inputData))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "" + this.inputData;
	}

}

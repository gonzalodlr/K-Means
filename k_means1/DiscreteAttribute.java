package k_means1;

class DiscreteAttribute extends Attribute {
	protected String[] values;

	public DiscreteAttribute(String name, int index, String[] values) {
		super(name, index);
		this.values = values;
	}

	public int getNumberOfDistinctValues() {
		return values.length;
	}

	public String getValue(int i) {
		return values[i];
	}
}
package data;

class ContinuousAttribute extends Attribute {
	protected double max;
	protected double min;

	public ContinuousAttribute(String name, int index, double min, double max) {
		super(name, index);
		this.min = min;
		this.max = max;
	}

	public double getScaledValue(double v) {
		return (v - min) / (max - min);
	}
}
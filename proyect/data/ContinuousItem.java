package data;

public class ContinuousItem extends Item {

	public ContinuousItem(ContinuousAttribute attribute, Double value) {
		super(attribute, value);
	}

	@Override
	public double distance(Object a) {
		double abs = 0;
		if (a instanceof ContinuousItem) {
			double scaledValue1 = ((ContinuousAttribute) getAttribute()).getScaledValue((double) getValue());
			double scaledValue2 = ((ContinuousAttribute) ((ContinuousItem) a).getAttribute())
					.getScaledValue((double) ((ContinuousItem) a).getValue());
			abs = Math.abs(scaledValue1 - scaledValue2);
			return abs;
		}
		return abs;
	}
}

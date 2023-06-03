package k_means2;

import utility.ArraySet;

class DiscreteAttribute extends Attribute {
	private String[] values;

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

	public int frequency(Data data, ArraySet idList, String v) {
		int count = 0;
		for (int i = 0; i < data.getNumberOfExamples(); i++) {
			if (idList.get(i)) {
				Tuple tuple = data.getItemSet(i);
				DiscreteItem item = (DiscreteItem) tuple.get(getIndex());
				if (item.getValue().equals(v)) {
					count++;
				}
			}
		}
		return count;
	}
}
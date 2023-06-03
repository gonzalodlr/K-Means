package k_means2;

import utility.ArraySet;

//Classe Item che modella un generico item (coppia attributo-valore)
public abstract class Item {
	private Attribute attribute;
	private Object value;

	public Item(Attribute attribute, Object value) {
		this.attribute = attribute;
		this.value = value;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public Object getValue() {
		return value;
	}

	public abstract double distance(Object a);

	public void update(Data data, ArraySet clusteredData) {
		value = data.computePrototype(clusteredData, attribute);
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
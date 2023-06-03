package data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class DiscreteAttribute extends Attribute implements Iterable<String> {
	private TreeSet<String> values;

	public DiscreteAttribute(String name, int index, String[] values) {
		super(name, index);
		this.values = new TreeSet<>();
		for (String value : values) {
			this.values.add(value);
		}
	}
	
	public TreeSet<String> getValues() {
        return values;
    }

	public int getNumberOfDistinctValues() {
		return values.size();
	}
	public int frequency(Data data, Set<Integer> idList, String v) {
	    int count = 0;
	    for (Integer i : idList) {
	        if (v.equals(data.getAttributeValue(i, getIndex()))) {
	            count++;
	        }
	    }
	    return count;
	}

	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}
}
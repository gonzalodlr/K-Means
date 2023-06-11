package data;

import java.sql.SQLException;
import java.util.*;

import Agent.DbAccess;
import Agent.Example;
import Agent.QUERY_TYPE;
import Agent.TableData;
import Agent.TableSchema;
import Exceptions.EmptySetException;
import Exceptions.NoValueException;
import Exceptions.OutOfRangeSampleSize;

public class Data {
	private List<Example> data;
	private int numberOfExamples;
	private List<Attribute> attributeSet;
	
	public Data(String tableName, DbAccess db) throws SQLException, NoValueException, EmptySetException {
		TableSchema table = new TableSchema(db, tableName);
		TableData tAttribute = new TableData(db);
		
		// Fill all distinct data examples
		data = tAttribute.getDistinctTransactions(tableName);

		// number of Examples:
		numberOfExamples = data.size();

		// explanatory Set

		attributeSet = new LinkedList<Attribute>();

		// If there is a digit: Continuous Attribute:
		for (int i = 0; i < table.getNumberOfAttributes(); i++) {
			if (table.getColumn(i).isNumber()) {
				// Casting the Object values into Double
				double min = (Float) tAttribute.getAggregateColumnValue(tableName, table.getColumn(i), QUERY_TYPE.MIN);
				double max = (Float) tAttribute.getAggregateColumnValue(tableName, table.getColumn(i), QUERY_TYPE.MAX);
				attributeSet.add(new ContinuousAttribute(table.getColumn(i).getColumnName(), i, min, max));
			} else {
				// Change the Set into array of Strings to be able to be added as dicrete attribute
				Set<Object> stringSet = tAttribute.getDistinctColumnValues(tableName, table.getColumn(i));
				String[] stringArray = stringSet.toArray(new String[stringSet.size()]);
				attributeSet.add(new DiscreteAttribute(table.getColumn(i).getColumnName(), i, stringArray));
			}
		}
	}

	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public int getNumberOfAttributes() {
		return attributeSet.size();
	}

	public List<Attribute> getAttributeSchema() {
		return attributeSet;
	}

	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return data.get(exampleIndex).get(attributeIndex);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getNumberOfExamples(); i++) {
			sb.append((i + 1)).append(": ");
			Example example = data.get(i);
			sb.append(example.toString()).append("\n");
		}
		return sb.toString();
	}

	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(getNumberOfAttributes());
		Example example = data.get(index);
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			Attribute attribute = attributeSet.get(i);
			Object value = example.get(i);
			if (attribute instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attribute, (String) value), i);
			} else if (attribute instanceof ContinuousAttribute) {
				double value1 = (Float) value;
				tuple.add(new ContinuousItem((ContinuousAttribute) attribute, value1), i);
			}
		}
		return tuple;
	}

	public int[] sampling(int k) throws OutOfRangeSampleSize {
		if (k <= 0) {
			throw new OutOfRangeSampleSize("Error:\tk <= 0\n");
		}else if(k >= getNumberOfExamples()) {
			throw new OutOfRangeSampleSize("Error:\tk >= " + getNumberOfExamples() + "\n");
		}

		int[] centroidIndexes = new int[k];
		// choose k random different centroids in data.
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i = 0; i < k; i++) {
			boolean found;
			int c;
			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroide already stored in
				// CentroidIndexes
				for (int j = 0; j < i; j++) {
					if (compare(centroidIndexes[j], c)) {
						found = true;
						break;
					}
				}
			} while (found);
			centroidIndexes[i] = c;
		}
		return centroidIndexes;
	}

	private boolean compare(int i, int j) {
		for (int k = 0; k < getNumberOfAttributes(); k++) {
			if (!data.get(i).get(k).equals(data.get(j).get(k))) {
				return false;
			}
		}
		return true;
	}

	Object computePrototype(Set<Integer> clusteredData, Attribute attribute) {
		if (attribute instanceof DiscreteAttribute) {
			return computePrototype(clusteredData, (DiscreteAttribute) attribute);
		}
		if (attribute instanceof ContinuousAttribute) {
			return computePrototype(clusteredData, (ContinuousAttribute) attribute);
		}
		return null;
	}

	public Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
		double sum = 0;
		int count = 0;

		for (Integer index : idList) {
			Example example = data.get(index);
			Object value = example.get(1);
			double value1 = (Float) value;
			ContinuousItem item = new ContinuousItem(attribute, value1);

			if (item != null) {
				sum += (double) item.getValue();
				count++;
			}
		}

		if (count > 0) {
			double prototypeValue = sum / count;
			return attribute.getScaledValue(prototypeValue);
		}

		return null;
	}

	String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {
		String prototype = null;
		int maxFrequency = -1;
		for (String value : attribute.getValues()) {
			int frequency = attribute.frequency(this, idList, value);
			if (frequency > maxFrequency) {
				maxFrequency = frequency;
				prototype = value;
			}
		}
		return prototype;
	}
}
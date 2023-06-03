package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Agent.DbAccess;
import Agent.Example;
import Agent.TableData;
import Agent.TableSchema;
import Exceptions.OutOfRangeSampleSize;

public class Data {
	private List<Example> data;
	private int numberOfExamples;
	private List<Attribute> attributeSet;

	/*
	 * public Data() { // data TreeSet<Example> tempData = new TreeSet<Example>();
	 * 
	 * Example ex0 = new Example(); Example ex1 = new Example(); Example ex2 = new
	 * Example(); Example ex3 = new Example(); Example ex4 = new Example(); Example
	 * ex5 = new Example(); Example ex6 = new Example(); Example ex7 = new
	 * Example(); Example ex8 = new Example(); Example ex9 = new Example(); Example
	 * ex10 = new Example(); Example ex11 = new Example(); Example ex12 = new
	 * Example(); Example ex13 = new Example();
	 * 
	 * // 0:sunny,hot,high,weak,no ex0.add(new String("sunny")); ex0.add(new
	 * Double(37.5)); ex0.add(new String("high")); ex0.add(new String("weak"));
	 * ex0.add(new String("no")); // 1:sunny,hot,high,strong,no ex1.add(new
	 * String("sunny")); ex1.add(new Double(38.7)); ex1.add(new String("high"));
	 * ex1.add(new String("strong")); ex1.add(new String("no")); //
	 * 2:overcast,hot,high,weak,yes ex2.add(new String("overcast")); ex2.add(new
	 * Double(37.5)); ex2.add(new String("high")); ex2.add(new String("weak"));
	 * ex2.add(new String("yes")); // 3:rain,mild,high,weak,yes ex3.add(new
	 * String("rain")); ex3.add(new Double(20.5)); ex3.add(new String("high"));
	 * ex3.add(new String("weak")); ex3.add(new String("yes")); //
	 * 4:rain,cool,normal,weak,yes ex4.add(new String("rain")); ex4.add(new
	 * Double(20.7)); ex4.add(new String("normal")); ex4.add(new String("weak"));
	 * ex4.add(new String("yes")); // 5:rain,cool,normal,strong,no ex5.add(new
	 * String("rain")); ex5.add(new Double(21.2)); ex5.add(new String("normal"));
	 * ex5.add(new String("strong")); ex5.add(new String("no")); //
	 * 6:overcast,cool,normal,strong,yes ex6.add(new String("overcast"));
	 * ex6.add(new Double(20.5)); ex6.add(new String("normal")); ex6.add(new
	 * String("strong")); ex6.add(new String("yes")); // 7:sunny,mild,high,weak,no
	 * ex7.add(new String("sunny")); ex7.add(new Double(21.2)); ex7.add(new
	 * String("high")); ex7.add(new String("weak")); ex7.add(new String("no")); //
	 * 8:sunny,cool,normal,weak,yes ex8.add(new String("sunny")); ex8.add(new
	 * Double(21.2)); ex8.add(new String("normal")); ex8.add(new String("weak"));
	 * ex8.add(new String("yes")); // 9:rain,mild,normal,weak,yes ex9.add(new
	 * String("rain")); ex9.add(new Double(19.8)); ex9.add(new String("normal"));
	 * ex9.add(new String("weak")); ex9.add(new String("yes")); //
	 * 10:sunny,mild,normal,strong,yes ex10.add(new String("sunny")); ex10.add(new
	 * Double(3.5)); ex10.add(new String("normal")); ex10.add(new String("strong"));
	 * ex10.add(new String("yes")); // 11:overcast,mild,high,strong,yes ex11.add(new
	 * String("overcast")); ex11.add(new Double(3.6)); ex11.add(new String("high"));
	 * ex11.add(new String("strong")); ex11.add(new String("yes")); //
	 * 12:overcast,hot,normal,weak,yes ex12.add(new String("overcast"));
	 * ex12.add(new Double(3.5)); ex12.add(new String("normal")); ex12.add(new
	 * String("weak")); ex12.add(new String("yes")); // 13:rain,mild,high,strong,no
	 * ex13.add(new String("rain")); ex13.add(new Double(3.2)); ex13.add(new
	 * String("high")); ex13.add(new String("strong")); ex13.add(new String("no"));
	 * 
	 * tempData.add(ex0); tempData.add(ex1); tempData.add(ex2); tempData.add(ex3);
	 * tempData.add(ex4); tempData.add(ex5); tempData.add(ex6); tempData.add(ex7);
	 * tempData.add(ex8); tempData.add(ex9); tempData.add(ex10); tempData.add(ex11);
	 * tempData.add(ex12); tempData.add(ex13);
	 * 
	 * data = new ArrayList<Example>(tempData);
	 * 
	 * // numberOfExamples numberOfExamples = 14;
	 * 
	 * // explanatory Set
	 * 
	 * attributeSet = new LinkedList<Attribute>();
	 * 
	 * // Outlook Values String outLookValues[] = new String[3]; outLookValues[0] =
	 * "overcast"; outLookValues[1] = "rain"; outLookValues[2] = "sunny";
	 * attributeSet.add(new DiscreteAttribute("Outlook", 0, outLookValues));
	 * 
	 * // Temperature Values // String temperatureValues[] = new String[3]; //
	 * temperatureValues[0] = "hot"; // temperatureValues[1] = "cold"; //
	 * temperatureValues[2] = "mild"; // attributeSet.add(new
	 * DiscreteAttribute("Temperature", 1, temperatureValues)); attributeSet.add(new
	 * ContinuousAttribute("Temperature", 1, 3.2, 38.7));
	 * 
	 * // Humidity Values String humidityValues[] = new String[2]; humidityValues[0]
	 * = "high"; humidityValues[1] = "normal"; attributeSet.add(new
	 * DiscreteAttribute("Humidity", 2, humidityValues));
	 * 
	 * // Wind Values String windValues[] = new String[2]; windValues[0] = "weak";
	 * windValues[1] = "strong"; attributeSet.add(new DiscreteAttribute("Wind", 3,
	 * windValues));
	 * 
	 * // PlayTennis Values String playTennisValues[] = new String[2];
	 * playTennisValues[0] = "yes"; playTennisValues[1] = "no"; attributeSet.add(new
	 * DiscreteAttribute("PlayTennis", 4, playTennisValues));
	 * 
	 * 
	 * 
	 * }
	 */

	public Data(String tableName, DbAccess db) throws SQLException {
		data = new ArrayList<Example>();
		try {
			Statement s = db.getConnection().createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM " + tableName);

			while (r.next()) {
				Example example = new Example();

				// Aggiungi i valori delle colonne alla transazione
				for (int i = 1; i <= r.getMetaData().getColumnCount(); i++) {
					Object value = r.getObject(i);
					if (value instanceof Float) {
						double value2 = (float) value;
						example.add(value2);
					} else {
						example.add(value);
					}
				}
				data.add(example);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		numberOfExamples = data.size();

		// explanatory Set
		
		TableSchema table = new TableSchema(db,tableName);
		TableData tAttribute = new TableData(db);

		attributeSet = new LinkedList<Attribute>();

		// Outlook Values
		// String outLookValues[] = new String[3];
		// outLookValues[0] = "overcast";
		// outLookValues[1] = "rain";
		// outLookValues[2] = "sunny";
		//attributeSet.add(new DiscreteAttribute("Outlook", 0, outLookValues));
		// Convierte el Set en un array de Strings
		Set<Object> stringSet =  tAttribute.getDistinctColumnValues(tableName,table.getColumn(0));
		String[] stringArray = stringSet.toArray(new String[stringSet.size()]);
		attributeSet.add(new DiscreteAttribute(table.getColumn(0).getColumnName(),0, stringArray));

		// Temperature Values
		attributeSet.add(new ContinuousAttribute("Temperature", 1, 3.2, 38.7));

		// Humidity Values
		String humidityValues[] = new String[2];
		humidityValues[0] = "high";
		humidityValues[1] = "normal";
		attributeSet.add(new DiscreteAttribute("Humidity", 2, humidityValues));

		// Wind Values
		String windValues[] = new String[2];
		windValues[0] = "weak";
		windValues[1] = "strong";
		attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));

		// PlayTennis Values
		String playTennisValues[] = new String[2];
		playTennisValues[0] = "yes";
		playTennisValues[1] = "no";
		attributeSet.add(new DiscreteAttribute("PlayTennis", 4, playTennisValues));

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
		Example example = data.get(exampleIndex);
		return example.get(attributeIndex);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfExamples; i++) {
			sb.append((i + 1)).append(": ");
			Example example = data.get(i);
			sb.append(example.toString()).append("\n");
		}
		return sb.toString();
	}

	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(attributeSet.size());
		Example example = data.get(index);
		for (int i = 0; i < attributeSet.size(); i++) {
			Attribute attribute = attributeSet.get(i);
			Object value = example.get(i);
			if (attribute instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attribute, (String) value), i);
			} else if (attribute instanceof ContinuousAttribute) {
				tuple.add(new ContinuousItem((ContinuousAttribute) attribute, (double) value), i);
			}
		}
		return tuple;
	}

	public int[] sampling(int k) throws OutOfRangeSampleSize {
		if (k <= 0) {
			throw new OutOfRangeSampleSize("Error:\tk <= 0\n");
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
		for (int k = 0; k < attributeSet.size(); k++) {
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
			ContinuousItem item = new ContinuousItem(attribute, (Double) value);

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
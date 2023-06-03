package k_means2;

import java.util.*;

import utility.ArraySet;

public class Data {
	private Object[][] data;
	private int numberOfExamples;
	private Attribute[] attributeSet;

	public Data() {
		// data
		 data = new Object[][]{
             {"sunny", "hot", "high", "weak", "no"},
             {"sunny", "hot", "high", "strong", "no"},
             {"overcast", "hot", "high", "weak", "yes"},
             {"rain", "mild", "high", "weak", "yes"},
             {"rain", "cool", "normal", "weak", "yes"},
             {"rain", "cool", "normal", "strong", "no"},
             {"overcast", "cool", "normal", "strong", "yes"},
             {"sunny", "mild", "high", "weak", "no"},
             {"sunny", "cool", "normal", "weak", "yes"},
             {"rain", "mild", "normal", "weak", "yes"},
             {"sunny", "mild", "normal", "strong", "yes"},
             {"overcast", "mild", "high", "strong", "yes"},
             {"overcast", "hot", "normal", "weak", "yes"},
             {"rain", "mild", "high", "weak", "yes"}
     };/*
		data = new Object[14][5];

		data[0][0] = new String("sunny");
		data[1][0] = new String("sunny");
		data[2][0] = new String("sunny");
		data[3][0] = new String("rain");
		data[4][0] = new String("rain");
		data[5][0] = new String("rain");
		data[6][0] = new String("rain");
		data[7][0] = new String("rain");
		data[8][0] = new String("rain");
		data[9][0] = new String("rain");
		data[10][0] = new String("overcast");
		data[11][0] = new String("overcast");
		data[12][0] = new String("overcast");
		data[13][0] = new String("overcast");

		data[0][1] = new String("hot");
		data[1][1] = new String("hot");
		data[2][1] = new String("hot");
		data[3][1] = new String("mild");
		data[4][1] = new String("mild");
		data[5][1] = new String("mild");
		data[6][1] = new String("mild");
		data[7][1] = new String("mild");
		data[8][1] = new String("mild");
		data[9][1] = new String("mild");
		data[10][1] = new String("cold");
		data[11][1] = new String("cold");
		data[12][1] = new String("cold");
		data[13][1] = new String("cold");

		data[0][2] = new String("high");
		data[1][2] = new String("high");
		data[2][2] = new String("high");
		data[3][2] = new String("normal");
		data[4][2] = new String("normal");
		data[5][2] = new String("normal");
		data[6][2] = new String("normal");
		data[7][2] = new String("normal");
		data[8][2] = new String("normal");
		data[9][2] = new String("normal");
		data[10][2] = new String("high");
		data[11][2] = new String("high");
		data[12][2] = new String("high");
		data[13][2] = new String("high");

		data[0][3] = new String("weak");
		data[1][3] = new String("weak");
		data[2][3] = new String("weak");
		data[3][3] = new String("strong");
		data[4][3] = new String("strong");
		data[5][3] = new String("strong");
		data[6][3] = new String("strong");
		data[7][3] = new String("strong");
		data[8][3] = new String("strong");
		data[9][3] = new String("strong");
		data[10][3] = new String("strong");
		data[11][3] = new String("strong");
		data[12][3] = new String("strong");
		data[13][3] = new String("strong");

		data[0][4] = new String("no");
		data[1][4] = new String("no");
		data[2][4] = new String("no");
		data[3][4] = new String("yes");
		data[4][4] = new String("yes");
		data[5][4] = new String("yes");
		data[6][4] = new String("yes");
		data[7][4] = new String("yes");
		data[8][4] = new String("yes");
		data[9][4] = new String("yes");
		data[10][4] = new String("yes");
		data[11][4] = new String("yes");
		data[12][4] = new String("yes");
		data[13][4] = new String("yes");
*/
		// numberOfExamples
		numberOfExamples = 14;

		// explanatory Set
		attributeSet = new Attribute[5];

		// Outlook Values
		String outLookValues[] = new String[3];
		outLookValues[0] = "overcast";
		outLookValues[1] = "rain";
		outLookValues[2] = "sunny";
		attributeSet[0] = new DiscreteAttribute("Outlook", 0, outLookValues);

		// Temperature Values
		String temperatureValues[] = new String[3];
		temperatureValues[0] = "hot";
		temperatureValues[1] = "cold";
		temperatureValues[2] = "mild";
		attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);

		// Humidity Values
		String humidityValues[] = new String[2];
		humidityValues[0] = "high";
		humidityValues[1] = "normal";
		attributeSet[2] = new DiscreteAttribute("Humidity", 2, new String[] { "high", "normal" });

		// Wind Values
		String windValues[] = new String[2];
		windValues[0] = "weak";
		windValues[1] = "strong";
		attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);

		// PlayTennis Values
		String playTennisValues[] = new String[2];
		playTennisValues[0] = "yes";
		playTennisValues[1] = "no";
		attributeSet[4] = new DiscreteAttribute("PlayTennis", 4, playTennisValues);

	}

	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public int getNumberOfAttributes() {
		return attributeSet.length;
	}

	public Attribute[] getAttributeSchema() {
		return attributeSet;
	}

	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return data[exampleIndex][attributeIndex];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfExamples; i++) {
			sb.append((i + 1)).append(": ");
			for (int j = 0; j < attributeSet.length; j++) {
				sb.append(data[i][j]).append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(attributeSet.length);
		for (int i = 0; i < attributeSet.length; i++)
			tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet[i], (String) data[index][i]), i);
		return tuple;
	}

	public int[] sampling(int k) {
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
		for (int k = 0; k < attributeSet.length; k++) {
			if (!data[i][k].equals(data[j][k])) {
				return false;
			}
		}
		return true;
	}

	public Object computePrototype(ArraySet idList, Attribute attribute) {
		if (attribute instanceof DiscreteAttribute) {
			return computePrototype(idList, (DiscreteAttribute) attribute);
		}
		// Add implementation for continuous attributes if needed
		return null;
	}

	public String computePrototype(ArraySet idList, DiscreteAttribute attribute) {
		String prototype = null;
		int maxFrequency = -1;
		for (int i = 0; i < attribute.getNumberOfDistinctValues(); i++) {
			String value = attribute.getValue(i);
			int frequency = attribute.frequency(this, idList, value);
			if (frequency > maxFrequency) {
				maxFrequency = frequency;
				prototype = value;
			}
		}
		return prototype;
	}
}
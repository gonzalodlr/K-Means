package k_means2;

import utility.ArraySet;

public class Cluster {
	private Tuple centroid;
	private ArraySet clusteredData;

	/*
	 * Cluster(){
	 * 
	 * }
	 */

	public Cluster(Tuple centroid) {
		this.centroid = centroid;
		clusteredData = new ArraySet();
	}

	public Tuple getCentroid() {
		return centroid;
	}

	public void computeCentroid(Data data) {
		for (int i = 0; i < centroid.getLength(); i++) {
			centroid.get(i).update(data, clusteredData);
		}
	}

	// return true if the tuple is changing cluster
	public boolean addData(int id) {
		return clusteredData.add(id);

	}

	// verifica se una transazione � clusterizzata nell'array corrente
	public boolean contain(int id) {
		return clusteredData.get(id);
	}

	// remove the tuplethat has changed the cluster
	public void removeTuple(int id) {
		clusteredData.delete(id);
	}

	public String toString() {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i);
		str += ")";
		return str;
	}

	public String toString(Data data) {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i) + " ";
		str += ")\nExamples:\n";
		int array[] = clusteredData.toArray();
		for (int i = 0; i < array.length; i++) {
			str += "[";
			for (int j = 0; j < data.getNumberOfAttributes(); j++)
				str += data.getAttributeValue(array[i], j) + " ";
			str += "] dist=" + getCentroid().getDistance(data.getItemSet(array[i])) + "\n";

		}
		str += "\nAvgDistance=" + getCentroid().avgDistance(data, array);
		return str;

	}

}

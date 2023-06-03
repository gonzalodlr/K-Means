package k_means2;

public class ClusterSet {
	private Cluster[] C;
	private int i;

	public ClusterSet(int k) {
		C = new Cluster[k];
		i = 0;
	}

	public void add(Cluster c) {
		C[i++] = c;
	}

	public Cluster get(int i) {
		return C[i];
	}

	public void initializeCentroids(Data data) {
		int[] centroidIndexes = data.sampling(C.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			Tuple centroid = data.getItemSet(centroidIndexes[i]);
			add(new Cluster(centroid));
		}
	}

	public Cluster nearestCluster(Tuple tuple) {
		// Initialise the minDistance with the first distance
		double minDistance = Double.MAX_VALUE; //C[0].getCentroid().getDistance(tuple);
		Cluster nearestCluster = null;
		for (int i = 0; i < C.length; i++) {
			double distance = tuple.getDistance(C[i].getCentroid());
			if (distance < minDistance) {
				minDistance = distance;
				nearestCluster = C[i];
			}
		}
		return nearestCluster;
	}

	public Cluster currentCluster(int id) {
		for (int i = 0; i < C.length; i++) {
			if (C[i].contain(id)) {
				return C[i];
			}
		}
		return null;
	}

	public void updateCentroids(Data data) {
		for (int i = 0; i < C.length; i++) {
			C[i].computeCentroid(data);
		}
	}

	//@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null) {
				str += i + ":" + C[i].toString() + "\n";
			}
		}
		return str;
	}

	public String toString(Data data) {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null) {
				str += i + ":" + C[i].toString(data) + "\n";
			}
		}
		return str;
	}

}
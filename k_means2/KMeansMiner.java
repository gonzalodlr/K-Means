package k_means2;

public class KMeansMiner {
	private ClusterSet C;

	public KMeansMiner(int k) {
		C = new ClusterSet(k);
	}

	public int kmeans(Data data) {
		int numberOfIterations = 0;
		// STEP 1
		C.initializeCentroids(data);
		boolean changedCluster;
		do {
			numberOfIterations++;
			// STEP 2
			changedCluster = false;
			for (int i = 0; i < data.getNumberOfExamples(); i++) {
				Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));
				Cluster oldCluster = C.currentCluster(i);
				boolean currentChange = nearestCluster.addData(i);
				if (currentChange) {
					changedCluster = true;
				}
				// remove tuple from old cluster
				if (currentChange && oldCluster != null) {
					oldCluster.removeTuple(i);
				}
			}
			// STEP 3
			C.updateCentroids(data);
		} while (changedCluster);
		return numberOfIterations;
	}

	public ClusterSet getC() {
		return C;
	}

	/*
	 * int kmeans(Data data) { int numberOfIterations = 0; // STEP 1
	 * C.initializeCentroids(data); boolean changedCluster; do {
	 * numberOfIterations++; // STEP 2 changedCluster = false; for (int i = 0; i <
	 * data.getNumberOfExamples(); i++) { Cluster nearestCluster =
	 * C.nearestCluster(data.getItemSet(i)); Cluster oldCluster =
	 * C.currentCluster(i); boolean currentChange = nearestCluster.addData(i); if
	 * (currentChange) { changedCluster = true; } // remove tuple from old cluster
	 * if (currentChange && oldCluster != null) { oldCluster.removeTuple(i); } } //
	 * STEP 3 C.updateCentroids(data); } while (changedCluster); return
	 * numberOfIterations; }
	 */
}
package clustering;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.canopy.CanopyDriver;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;

public class Clustering {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException 
	{	
		//KMeansDriver.run(new Path("kmeans_output_file"), new Path("output/clusters-0"), new Path("output"),new EuclideanDistanceMeasure(), 0.001, 10, true,0.001,true);
	
		CanopyDriver.run(new Path("kmeans_output_file"), new Path("output"), new EuclideanDistanceMeasure(), 3.1, 10,true, 2.1,true);
				//ManhattanDistanceMeasure.class.getName(), (float) 3.1, (float) 2.1, false);
	}

}

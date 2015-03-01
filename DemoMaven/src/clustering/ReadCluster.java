package clustering;

import java.io.IOException;

import javax.sound.midi.Sequence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.clustering.classify.WeightedVectorWritable;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.VectorWritable;

public class ReadCluster {

	public static void main(String[] args) throws IOException {
		String OUTPUT_FILE="output/clusters-0-final/part-r-00000";
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(conf);
		SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(OUTPUT_FILE), conf);
IntWritable key=new IntWritable();
VectorWritable value = new VectorWritable();
while(reader.next(key,value))
{
	System.out.println(value.get().asFormatString()+" belongs to cluster "+key.toString());
}

	}

}

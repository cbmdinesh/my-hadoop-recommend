package dataPreparation;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DataPrepDriver
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	  {
		
		  Configuration conf=new Configuration();
		  Job job = new Job(conf,"Data preparation");
		  job.setJarByClass(DataPrepDriver.class);
		  job.setMapperClass(DataPrepMapper.class);
		  job.setReducerClass(DataPrepReducer.class);
		  job.setOutputKeyClass(Text.class);
		  job.setOutputValueClass(Text.class);
		  FileInputFormat.addInputPath(job, new Path(args[0]));
		  FileOutputFormat.setOutputPath(job, new Path(args[1]));
		  System.exit(job.waitForCompletion(true)?0:1);
	  }
}

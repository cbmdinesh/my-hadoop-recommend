package canopymaker;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NetflixCanopyDataDriver 
{
// This map-reduce marks each movie vector with the canopies that it occurs in.
    // Each mapper load the list of canopies into memory during the configure method,
    // and then maps over the movie vector data. We use the identity reducer.
    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
    	  Configuration conf=new Configuration();
    	  Job job=new Job(conf,"Title preparation");
    	  job.setJarByClass(NetflixCanopyDataDriver.class);
    	  job.setOutputFormatClass(org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat.class);
    	  job.setInputFormatClass(org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat.class);
    	  job.setJobName("NetflixCanopyData");
          
    	  job.setOutputKeyClass(Text.class);
    	  job.setOutputValueClass(Text.class);
          job.setNumReduceTasks(80);
    	                    
            //conf.setOutputFormat(org.apache.hadoop.mapred.TextOutputFormat.class);

    if (args.length < 2) {
        System.out.println("Usage: NetflixCanopyData <input path> <output path>");
        System.exit(0);
     }
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

            job.setMapperClass(NetflickCanopyDataMapper.class);
            //conf.setReducerClass(NetflixCanopyDataReducer.class);
           
           
            System.exit(job.waitForCompletion(true)?0:1);
    }

}

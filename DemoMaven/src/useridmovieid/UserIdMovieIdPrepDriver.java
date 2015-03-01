/**
 * 
 */
package useridmovieid;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


/**
 * @author Dinesh
 *
 */
public class UserIdMovieIdPrepDriver {
	public static void main(String args) throws IOException, ClassNotFoundException, InterruptedException {
String outputfolder="useridmovie";
File output=new File(outputfolder);
if(output.exists())
{
	for(File f:output.listFiles())
	{
		f.delete();
	}
	output.delete();
}
Configuration conf=new Configuration();
Job job=new Job(conf, "user Id based Movie ids");
job.setInputFormatClass(TextInputFormat.class);
job.setOutputFormatClass(TextOutputFormat.class);
job.setJarByClass(UserIdMovieIdPrepDriver.class);
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(Text.class);
job.setMapperClass(UserIdMovieIdMapper.class);
job.setReducerClass(UserIdMovieIdReducer.class);
FileInputFormat.addInputPath(job,new Path("input/ratings.csv"));
FileOutputFormat.setOutputPath(job,new Path(outputfolder));
if(job.waitForCompletion(true))
{
	JOptionPane.showMessageDialog(null, "Data Clustered Successfully");
}
//System.exit(job.waitForCompletion(true)?0:1);
	}
}

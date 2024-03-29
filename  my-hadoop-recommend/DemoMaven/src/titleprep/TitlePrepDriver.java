package titleprep;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class TitlePrepDriver 
{
	static File file=new File("filepath.properties");
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
	{
		FileInputStream inputStream=new FileInputStream(file);
		Properties properties=new Properties();
		properties.load(inputStream);
		String TitleOutput=properties.getProperty("movieTitleOutput");
		String titleInput=properties.getProperty("titleInput");
		
		File output=new File(TitleOutput);
		if(output.exists())
		{
		File f[]=output.listFiles();
		for(File i:f)
		{
			i.delete();
		}
		output.delete();
		}
		
		Configuration conf=new Configuration();
		Job job=new Job(conf,"Title preparation");
		job.setJarByClass(TitlePrepDriver.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapperClass(TitleDataPrefer.class);
		job.setReducerClass(org.apache.hadoop.mapreduce.Reducer.class);
		FileInputFormat.addInputPath(job, new Path(titleInput));
		FileOutputFormat.setOutputPath(job, new Path(TitleOutput));
		System.exit(job.waitForCompletion(true)?0:1);
	}

}

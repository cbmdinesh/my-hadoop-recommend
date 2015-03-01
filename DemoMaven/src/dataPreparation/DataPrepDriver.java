package dataPreparation;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DataPrepDriver extends Configured implements Tool

{
	static File file=new File("filepath.properties");
	
	public static void main(String[] args) throws Exception 
	  {
		FileInputStream inputfile=new FileInputStream(file);
		Properties properties=new Properties();
		properties.load(inputfile);
		String inputFilePath=properties.getProperty("ratings");
		String outputFolder=properties.getProperty("dataprepoutput");
		
		File output=new File(outputFolder);
		if(output.exists())
		{
		File f[]=output.listFiles();
		for(File i:f)
		{
			i.delete();
		}
		output.delete();
		}
		
		
		int res = ToolRunner.run(new Configuration(), new DataPrepDriver(), args);

	//	System.exit(res);

	  }
	

	public int run(String[] args) throws Exception {
	
		
		
		
		FileInputStream inputfile=new FileInputStream(file);
		Properties properties=new Properties();
		properties.load(inputfile);
		String inputFilePath=properties.getProperty("ratings");
		String outputFolder=properties.getProperty("dataprepoutput");
		
		
		
		Configuration conf = getConf();

		Job job = new Job(conf, "MyJob");

		job.setJarByClass(DataPrepDriver.class);
		Path in = new Path(inputFilePath);
		Path out = new Path(outputFolder);
		FileInputFormat.setInputPaths(job, in);

		FileOutputFormat.setOutputPath(job, out);

		job.setMapperClass(DataPrepMapper.class);

		job.setReducerClass(DataPrepReducer.class);

		job.setInputFormatClass(TextInputFormat.class); 

		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(Text.class);
	
		if(job.waitForCompletion(true))
		{
			JOptionPane.showMessageDialog(null, "Data preprocessed");
		}

		return 0;

	
	}
}

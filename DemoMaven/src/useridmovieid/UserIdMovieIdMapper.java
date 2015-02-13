/**
 * 
 */
package useridmovieid;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author Dinesh
 *
 */
public class UserIdMovieIdMapper extends Mapper<LongWritable, Text, Text, Text> 
{

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	@Override
	protected void map(LongWritable key, Text value,Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException,
			InterruptedException 
	{
	
		String line=value.toString();
		
		String[] items=line.split(",");
		String userId="";
		String ratedMovies="";
		if(items.length!=3)
		{
			context.setStatus("Key value missing at line "+line);	 
			return;
		}
		
		userId=items[0];
		ratedMovies=items[1]+","+items[2];
		context.write(new Text(userId),new Text(ratedMovies));
		
	}
	

}

package dataPreparation;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class DataPrepMapper extends Mapper<LongWritable, Text, Text, Text>
{

	@Override
	public void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException
            {

			String line=((Text)value).toString();
			String[] items=line.split(",");
			if(items.length!=3)
			{
				context.setStatus("Key value is missing at "+line);
				return;
			}
			Text keyToEmit=new Text(items[1]);
			Text valueToEmit=new Text(items[0]+","+items[2]);
			context.write(keyToEmit,valueToEmit);
			
		
            }

}

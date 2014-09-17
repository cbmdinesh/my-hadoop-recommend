package dataPreparation;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DataPrepReducer extends Reducer<Text, Iterator<Text>, Text, Text>
{
public void reduce(Text key,Iterator<Text> values,Context context) throws IOException, InterruptedException
{

	TreeMap<Integer,String> features=new TreeMap<Integer, String>();
	
	while(values.hasNext())
	{
		String[] items=values.next().toString().split(",");
		features.put(new Integer(items[0]),items[1]);
	}
	
	StringBuilder builder=new StringBuilder();
	
	Iterator<Integer> it=features.keySet().iterator();
	
	while(it.hasNext())
	{
		Integer featurekey=it.next();
		String featurevalue=features.get(featurekey);
		builder.append(featurekey);
		builder.append(",");
		builder.append(featurevalue);
		if(it.hasNext())
		{
			builder.append(";");
		}
		
	}
	String toOutput = builder.toString();
	
	context.write(key,new Text(toOutput));
}
	
	
}

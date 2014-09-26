/**
 * 
 */
package useridmovieid;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author Dinesh
 *
 */
public class UserIdMovieIdReducer extends Reducer<Text,Iterable<Text>,Text,Text>
{

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
	 */
	@Override
	protected void reduce(Text key, Iterable<Iterable<Text>> values,Context context) throws IOException,
			InterruptedException
	{
		
	Iterator<Iterable<Text>> value=values.iterator();
	TreeMap<String,String> features=new TreeMap<String, String>();
	while(value.hasNext())
	{
		String[] items=((Text)value.next()).toString().split(",");
		features.put(items[0],items[1]);
	}
	StringBuilder builder=new StringBuilder();
	
	Iterator<String> keyIterator=features.keySet().iterator();
	
	while(keyIterator.hasNext())
	{
		String featurekey=keyIterator.next();
		String featureValue=features.get(featurekey);
		builder.append(featurekey);
		builder.append(",");
		builder.append(featureValue);
		if(keyIterator.hasNext())
		{
			builder.append(";");
		}
	}
	String toOutput=builder.toString();
	context.write(key,new Text(toOutput));
	}
	

}

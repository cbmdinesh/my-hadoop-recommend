package titleprep;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;

public class TitleDataPrefer extends Mapper<WritableComparable<Text>,Text, Text, Text>
{
public void map(WritableComparable<Text> key,Text values,Context context) throws IOException, InterruptedException
{
String data=values.toString();
int index=data.indexOf(",");
if(index==-1)
return;
String movie_id=data.substring(0, index);
index=data.indexOf(",",index+1);
if(index==-1)
return;
String title=data.substring(index+1);
context.write(new Text(movie_id),new Text(title+"\t"));	
	
}
	
	
	
}

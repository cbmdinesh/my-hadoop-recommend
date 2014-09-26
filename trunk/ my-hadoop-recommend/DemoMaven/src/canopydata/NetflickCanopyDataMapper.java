package canopydata;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class NetflickCanopyDataMapper extends Mapper<Text,Text,Text, Text>
{
	 private static ArrayList<NetflixMovie> capopyCenters = new ArrayList<NetflixMovie>();
     private boolean done = false;
     private int count = 0;
    
     
         
     
     
     
     /* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#setup(org.apache.hadoop.mapreduce.Mapper.Context)
	 */
 	// Load the canopy centers into memory.
	@Override
	protected void setup(Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		  try {
              if(done)
                      return;
              else
                      done = true;
          FileSystem fs = FileSystem.get(context.getConfiguration());
          Path path = new Path("/home/hduser/workspace/DemoMaven/dataprep/part-r-00000");
          SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, context.getConfiguration());
          Text key = new Text();
          Text value = new Text();
          while(true) {
              reader.next(key, value);
              if(key.toString().equals(""))
                      break;
                      NetflixMovie curr = new NetflixMovie(key.toString(), value.toString());
                      capopyCenters.add(curr);
                      key.set("");
          }
  } catch (IOException e) {
           e.printStackTrace();
  }
	}


 
  
     // This needs to read through the netflix data and for each point if it belongs
     // to a canopy, then emit it to it.
     // Final form will be CanopyID1:CanopyID2:... MovieID:movieVector
     // where movieVector contains the userID,rating pairs.
     @Override
     public void map(Text key,Text values,Context context) throws IOException, InterruptedException {
             count += 1;
             String movie_id = key.toString();
             String data = ((Text)values).toString();
             String status = count+":"+capopyCenters.size()+":"+movie_id;
             context.setStatus(status);                                                                                                                            
             NetflixMovie curr = new NetflixMovie(movie_id, data);
             boolean emitted = false;
             StringBuilder builder = new StringBuilder();
             for(NetflixMovie center: capopyCenters) {
                     int matchCount = curr.MatchCount(center);
                     if(matchCount > 2) {
                             emitted |= true;
                             if(builder.length()>0)
                                     builder.append(":");
                             builder.append(center.movie_id);
                     }
             }
             if(emitted) {
                     builder.append(":");
                     builder.append(movie_id);
                     builder.append(":");
                     builder.append(data);
                     String to_emit = builder.toString();
                     context.write(new Text(movie_id), new Text(to_emit));
             }else
                     context.setStatus("Did not emit: "+movie_id);
     }

}

package canopymaker;

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
    
     // Load the canopy centers into memory.
     public void configure(Configuration conf) {
            
          try {
              if(done)
                      return;
              else
                      done = true;
          FileSystem fs = FileSystem.get(conf);
          Path path = new Path("/user/jhebert/out2/part-00000");
          SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
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
     public void map(WritableComparable<Text> key, Writable values,Context context) throws IOException, InterruptedException {
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

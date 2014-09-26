/**
 * 
 */
package user;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;



/**
 * @author Dinesh
 *
 */
public class UserInput
{
public static void main(String[] args) throws IOException
{
String arg="useridmovie/part-r-00000";
Scanner	 s=new Scanner(System.in);	
Map<String,String> rate=new HashMap<String,String>();

System.out.println("Enter user Id");
long id=s.nextLong();
File file=new File(arg);
try {
	String line;
	BufferedReader in=new BufferedReader(new FileReader(file));
	while((line=in.readLine())!=null)
	{
		String[] lines=line.split("\t");
		if(lines.length>=2)
		{
			if(Long.parseLong(lines[0])==id)
			{
			String[] feature=lines[1].split(";");
		for(String k:feature)
		{
		String item[]=k.split(",");
		rate.put(item[0], item[1]);
		}
			}
		}
		}
	printMovieTitles(rate);
	
}
catch (FileNotFoundException e) {
	e.printStackTrace();
}



	}

/**
 * @param rate
 * @throws IOException 
 */
private static void printMovieTitles(Map<String, String> rate) throws IOException 
{
	Iterator<String> movieIdIterator=rate.keySet().iterator();
	StringBuilder builder=new StringBuilder();
	while(movieIdIterator.hasNext())
	{
		BufferedReader in=new BufferedReader(new FileReader("movietitles/part-r-00000"));
		String line="";
		String movieId=movieIdIterator.next();
		while((line=in.readLine())!=null)
		{
			String title[]=line.split("\t");
			if(title.length==2)
			{
				if(title[0].equals(movieId))
				{
					builder.append("Movie Id:"+movieId+"\t");
					builder.append("Movie Name:"+title[1]+"\t");
					builder.append("User Rating:"+rate.get(movieId)+"\n");
					break;
				}
			}
			
		}
		
	}
	System.out.println(builder.toString());
	
}

}

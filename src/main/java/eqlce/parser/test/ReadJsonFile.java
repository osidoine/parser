package eqlce.parser.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
	
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJsonFile {

	public ReadJsonFile() {
	}
	
	// Java function to read JSON from a file 
	public List<String> readJsonFile(String jsonFile){	
		
	        Object obj = null;
	        
			try {
				obj = new JSONParser().parse(new FileReader(jsonFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
	          
	        List<JSONArray> eql = new ArrayList<JSONArray>();
	        List<String> eqlQueries = new ArrayList<String>();
	        List<String> queries = new ArrayList<String>();
	        
	        eql = (List<JSONArray>) obj;
			Iterator i = eql.iterator();
            
            while (i.hasNext()) {
            	JSONObject jsonObject = (JSONObject) i.next() ;
               queries = (List<String>) jsonObject.get("queries") ;
               for(String query:queries)
            	   eqlQueries.add(query);
                
            }
	     
			return eqlQueries; 
	}

}
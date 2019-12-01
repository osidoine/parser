package eqlce.parser.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExecuteMongoQuery {
	
	protected String[] splits ;
	List<String> mongoQueries = new ArrayList<String>();

	public ExecuteMongoQuery() {
	}
	
	public List<String> readMongoFile(String mongoFile)
	{
		try {
			mongoQueries = Files.readAllLines(Paths.get(mongoFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return mongoQueries ;
	}
	
	public void executeMongoQuery(String mongoFile) {
		//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://heroku_2cpz0mfq:7vvnevnurqtio30hufhkd6hv49@ds139934.mlab.com:39934/heroku_2cpz0mfq?retryWrites=false"));
		
		DB database = mongoClient.getDB("heroku_2cpz0mfq");
		
		//https://www.mlab.com/databases/heroku_2cpz0mfq/collections/scalar_observation
		//username = eqlce
		//password = eqlce0
		
		mongoQueries = readMongoFile(mongoFile);
		
		for (String mongoQuery : mongoQueries) {
			if(mongoQuery.contains("insert")) {
				splits = mongoQuery.split(".insert");
				splits[0] = splits[0].replace("db.","");
				splits[1] = splits[1].replace(";","");
				splits[1] = splits[1].replace("(","");
				splits[1] = splits[1].replace(")","");
				
				DBCollection collection = database.getCollection(splits[0]);
				DBObject dbObject = (DBObject)JSON.parse(splits[1]);
				collection.insert(dbObject);
			}
			else if(mongoQuery.contains("rename")) {
				splits = mongoQuery.split(".rename");
			}
			
			//for(String split:splits)
			//System.out.println("split: "+split);
		}
	}

}

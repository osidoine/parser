package eqlce.parser.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList ;
import java.util.List;

import org.springframework.stereotype.Service;

import eqlce.parser.factory_parser.FactoryParser;
import eqlce.parser.parser.Parser;

@Service
public class JsonFileParser {
	
	FactoryParser factoryParser = new FactoryParser();
	List<String> eqlQueries = new ArrayList<String>();
	ReadJsonFile readJsonFile = new ReadJsonFile();
	WriteMongoQuery writeMongoQuery = new WriteMongoQuery();

	public JsonFileParser() {
	}
	
	
	public String parse(String jsonFile)
	{
		String mongoFile = jsonFile.replace(".json", ".mongo");
		
		Path path = Paths.get(mongoFile);
		try {
			Files.deleteIfExists(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	//	System.out.println("Reading query from the file : " + jsonFile);
		
		try {
			eqlQueries = readJsonFile.readJsonFile(jsonFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for(String eqlQuery:eqlQueries)
		{
			Parser parser = factoryParser.getParser(eqlQuery);
			if(parser == null)
			//	System.out.println("Enable to parse eqlQuery : "+eqlQuery);
				continue;
			else
			{
			//	System.out.println("Parsing eqlQuery : "+eqlQuery);
				
				String mongoQuery = parser.parse(eqlQuery);
				
			//	System.out.println("Writing mongoQuery in the file: " +mongoFile);
				
				writeMongoQuery.writeMongoQuery(mongoQuery, mongoFile);
				
			//	System.out.println("Done");
			}
		}
		
		
		return "Successfully parsing queries "; //in the file " + mongoFile;
	}
	
}

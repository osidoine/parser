package eqlce.parser.factory_parser;

import eqlce.parser.parser.Parser;

public class FactoryParser {
	
	private Parser parser;
	
	public FactoryParser() {
		// TODO Auto-generated constructor stub
	}

	
	public Parser getParser(String context)
	{
		//sensor_network_parser
		if(context.contains("INSERT SCALAR OBSERVATION")|| context.contains("INSERT MEDIA OBSERVATION") || context.contains("INSERT PROPERTY")
				|| context.contains("INSERT SENSOR")) 
			parser =  new eqlce.parser.sensor_network_parser.CreateParser();
		else parser = null;
		
		return parser ;
	}

}

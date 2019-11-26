package eqlce.parser.sensor_network_parser;

import eqlce.parser.parser.Parser;

public abstract class SensorNetworkParser implements Parser{

	public SensorNetworkParser() {
		// TODO Auto-generated constructor stub
	}
	
	protected String[] splits ;
	
	public void splitEqlQuery(String eqlQuery) {
		splits = eqlQuery.split("[(',{});]+");
	}
	
	public String getComponent() {
		String component = null ;
		if(splits[0].contains("OBSERVATION") && splits[0].contains("SCALAR")) component = "scalar_observation" ;
		else if(splits[0].contains("OBSERVATION") && splits[0].contains("MEDIA")) component = "media_observation" ;
		else if(splits[0].contains("PROPERTY")) component = "property" ;
		else if(splits[0].contains("SENSOR")) component = "sensor" ;
		return component ;
	}
	
	
}

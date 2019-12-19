package eqlce.parser.sensor_network_parser;

import java.util.ArrayList;
import java.util.List;

public class InsertParser extends SensorNetworkParser{

	public InsertParser() {
		// TODO Auto-generated constructor stub
	}
	
	protected List<String> values = new ArrayList<String>();
	protected String[] subSplits ;

	@Override
	public String parse(String eqlQuery) {
		// TODO Auto-generated method stub
		splitEqlQuery(eqlQuery);
		//for(String split:splits) System.out.println(split);
		String component = getComponent();
		String mongoQuery = "db."+ component + ".insert({\"id\":\""+ splits[1]+"\",";
		
		if(splits[0].contains("OBSERVATION"))
		{
			mongoQuery +="\"description\":\""+splits[2]+"\",\"data\":{\"data_value_or_object_id\":\""+splits[3];
			mongoQuery +="\",\"data_type_id\":\""+splits[4]+"\"},\"metadata\":[";
			int i = 5;
			while(i<splits.length-1) {
				if(i != 5) mongoQuery +=",";
				if (!splits[i].equalsIgnoreCase(":"))
				{
						mongoQuery += "{\"metadata_tag\":\"" + splits[i] + "\",\"metadata_value\":\""+ splits[i+2]+"\"}";
						i +=3 ;
				}
			}
		}
		
		mongoQuery +="]});";
		return mongoQuery;
	}

}

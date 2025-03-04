package akkiFrameworks.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException
	{
//		1. Read json to string 
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\akkiFrameworks\\data\\PurchaseOrder.json"), 
            StandardCharsets.UTF_8);
		
//		2. Convert string to hashmap using dependency called -> Jackson Databind
//		Jackson Databind -> Converts String data to hashmap
//		Add that dependency into pom.xml
		
//		Now to convert we need a class called ObjectMapper 
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String , String>>>() {
			
		});
		return data;
//		.readValue - method which can read string value and convert that to hashmap it accepts two arguments
//		1st - the string which we want to convert to hashmap 
//		2nd - how we want to convert it 
//		When it creates two hashmaps how it will give us back 
//		So after it creates hashmaps , it will create a list and put those to hashmaps and it will give us back 
		
//		Finally we will get {map ,map}
	}
}

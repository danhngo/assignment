package test.util;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import test.cache.StockMemoryCache;
import test.cache.ThreadSafeCacheSingleton;

public class FileUtil {
	
	public static void popularJSONFileToCache() {
		JSONParser parser = new JSONParser();
	    try {
 
            Object obj = parser.parse(new FileReader("/resources/GE.json"));
            JSONObject jsonObject = (JSONObject) obj;
 
            JSONObject dataSet = (JSONObject) jsonObject.get("dataset");
            String symbol = (String) dataSet.get("dataset_code");
            JSONArray data = (JSONArray) jsonObject.get("data");
            
            Iterator<JSONArray> iterator = data.iterator();
            while (iterator.hasNext()) {  
            	
            	Iterator<String> values = ((JSONArray)iterator.next()).iterator();
            	int i = 0;
            	while (values.hasNext()) {  
            		String value = values.next();
            		i = i + 1;
            		
            	}
            }
            StockMemoryCache<String, StockMemoryCache<String,String>> cache = ThreadSafeCacheSingleton.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

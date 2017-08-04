package test.util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import test.cache.StockMemoryCache;
import test.cache.ThreadSafeCacheSingleton;

public class FileUtil {
	
	public static void popularJSONFileToCache() {
		JSONParser parser = new JSONParser();
	    try {
	        Object obj = parser.parse(new FileReader("./GE.json"));
            JSONObject jsonObject = (JSONObject) obj;
 
            JSONObject dataSet = (JSONObject) jsonObject.get("dataset");
            String symbol = (String) dataSet.get("dataset_code");
            JSONArray data = (JSONArray) dataSet.get("data");
            Map<String,Double> datePriceMap = new HashMap<String,Double>();
            Iterator<JSONArray> iterator = data.iterator();
            while (iterator.hasNext()) {  
            	Iterator<String> values = ((JSONArray)iterator.next()).iterator();
            	int i = 0;
            	String date = null;
            	Double price = null;
            	while (values.hasNext()) {  
            		Object value = values.next();
            		if (i == 0) {
            			date = (String)value;
            		} else if (i == 4) {
            			price = (Double)value;
            		}
            		i = i + 1;
            		if (i >= 5) {
            			break;
            		}
            	}  
            	if (date != null && price != null) {
            		if (!datePriceMap.containsKey(date)) {
            			datePriceMap.put(date, price);
            		}
            	}
            }
            
            StockMemoryCache<String, Map<String,Double>> cache = ThreadSafeCacheSingleton.getInstance();
            cache.put(symbol, datePriceMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

package test.cache;

import java.util.Map;

public class ThreadSafeCacheSingleton {
	
	private static StockMemoryCache<String, Map<String,Double>>  instance;
    
    private ThreadSafeCacheSingleton(){}
    
    public static synchronized StockMemoryCache<String, Map<String,Double>> getInstance(){
        if(instance == null){
            instance = new StockMemoryCache<String, Map<String,Double>>(3600, 500, 10000);
        }
        return instance;
    }
}

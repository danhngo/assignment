package test.cache;

public class ThreadSafeCacheSingleton {
	
	private static StockMemoryCache<String, StockMemoryCache<String,String>>  instance;
    
    private ThreadSafeCacheSingleton(){}
    
    public static synchronized StockMemoryCache<String, StockMemoryCache<String,String>> getInstance(){
        if(instance == null){
            instance = new StockMemoryCache<String, StockMemoryCache<String,String>>(3600, 500, 10000);
        }
        return instance;
    }
}

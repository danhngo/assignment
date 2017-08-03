package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import test.cache.StockMemoryCache;
import test.cache.ThreadSafeCacheSingleton;
import test.dto.DatePrice;
import test.dto.Price;
import test.dto.PriceAverage;
import test.dto.Response;
import test.util.DateUtil;
 
@Path("api/v2")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class StockPriceAPI {
 
	private static int DATE_MOVE_200 = 200;
	 
    @GET
    @Path("/{symbol}/closePrice")
    public Response closePrice(@PathParam("symbol") String symbol, 
    		@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
    	Response resp = new Response();
    	StockMemoryCache<String, Map<String,Double>> cache = ThreadSafeCacheSingleton.getInstance();
    	Map<String,Double> datePriceMap = cache.get(symbol);
    	if (datePriceMap != null) {
    		List<Price> prices = new ArrayList<Price>();
    		Price price = new Price();
    		price.setTicker(symbol);
    		List<DatePrice> dateCloses = new ArrayList<DatePrice>();
    		//2017-06-12
    		int result = DateUtil.validateDate(startDate, endDate);
    		if (result == 0) {
    			List<String> dates = DateUtil.getDaysBetweenDates(startDate,endDate);
    			for (String date : dates) {
    				Double closePrice = datePriceMap.get(date);
    				DatePrice datePrice = new DatePrice();
    				datePrice.setDateClose(date);
    				datePrice.setPrice(closePrice);
    				dateCloses.add(datePrice);
				}    		
    			price.setDateCloses(dateCloses);
    			prices.add(price);
    			resp.setSuccess(true);
        		resp.setData(prices);
    		} else {
    			putError(resp,result);
    		}
    	} else {
    		resp.setSuccess(false);
    		resp.setErrorCode("stock.symbol.notfound");
    		resp.setMessage("Symbol is not found");
    	}
    	/*
    	 *   "Prices": [
	      {"Ticker": "GE",
	      "DateClose": { ["1999-03-30", "28.32"], ["1999-03-31", "27.94"]},...
	    ]
	     "data": [
        {
            "dateCloses": [
                {
                    "dateClose": "2016-06-12",
                    "price": null
                },
                {
                    "dateClose": "2016-06-13",
                    "price": 29.83
                },
    	 */
        
        return resp;
    }
    
    @GET
    @Path("/{symbol}/200dma")
    public Response stock200dma(@PathParam("symbol") String symbol, @QueryParam("startDate") String startDate) {
    	Response resp = new Response();
    	StockMemoryCache<String, Map<String,Double>> cache = ThreadSafeCacheSingleton.getInstance();
    	Map<String,Double> datePriceMap = cache.get(symbol);
    	if (datePriceMap != null) {
    		String endDate = DateUtil.getEndDateOfDuration(startDate,DATE_MOVE_200);
    		if (endDate != null) {
    			List<String> dates = DateUtil.getDaysBetweenDates(startDate,endDate);
    			Double total200ClosePrices = 0D;
    			int totalDate = 0;
    			for (String date : dates) {
    				Double closePrice = datePriceMap.get(date);
    				if (closePrice != null) {
    					total200ClosePrices = total200ClosePrices + closePrice;
    					totalDate = totalDate + 1;
    				}
    			}
    			Double avg200Price = total200ClosePrices/totalDate;
    			
    			PriceAverage avgPrice = new PriceAverage();
    			avgPrice.setTicker(symbol);
    			avgPrice.setAvg(avg200Price);
    			
    			resp.setSuccess(true);
        		resp.setData(avgPrice);
        	} else {
    			resp.setSuccess(false);
    			resp.setErrorCode("stock.date.emptyInvalid");
        		resp.setMessage("Start Date is empty or invalid");
    		}
    	} else {
    		resp.setSuccess(false);
    		resp.setErrorCode("stock.symbol.notfound");
    		resp.setMessage("Symbol is not found");
    	}    	
        return resp;
    }
    
    
    private void putError(Response resp, int result) {
    	resp.setSuccess(false);
    	switch (result) {
		case -1:
			resp.setErrorCode("stock.date.empty");
    		resp.setMessage("Start Date or End Date is empty");
			break;
		case -2:
			resp.setErrorCode("stock.date.invalid");
    		resp.setMessage("Start Date or End Date is invalid");
			break;	
		case -3:
			resp.setErrorCode("stock.date.invalidRange");
    		resp.setMessage("Start Date must before or equals to End Date");
			break;
		default:
			break;
		}
    }
    
}
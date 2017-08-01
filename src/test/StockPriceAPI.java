package test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import test.dto.Price;
import test.dto.Response;
 
@Path("api")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class StockPriceAPI {
 
    @GET
    public Response get() {
    	Response resp = new Response();
    	resp.setSuccess(true);
        return resp;
    }
    
    @GET
    @Path("/{symbol}/closePrice")
    public Response closePrice(@PathParam("symbol") String symbol, 
    		@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
    	Response resp = new Response();
    	resp.setSuccess(true);
    	Price price = new Price();
    	price.setTicker("GE");
    	resp.setData(price);
        return resp;
    }
    
}
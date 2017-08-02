package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.ws.rs.core.UriBuilder;

import test.cache.StockMemoryCache;
import test.cache.ThreadSafeCacheSingleton;
import test.util.FileUtil;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
 
@SuppressWarnings("restriction")
public class StockPriceServiceServer {
 
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Embedded Jersey HTTPServer...\n");
        HttpServer crunchifyHTTPServer = createHttpServer();
        crunchifyHTTPServer.start();
   
        FileUtil.popularJSONFileToCache();
        System.out.println(String.format("\nJersey Application Server started with WADL available at " + "%sapplication.wadl\n", getURI()));
        System.out.println("Started Embedded Jersey HTTPServer Successfully !!!");
    }
 
    private static HttpServer createHttpServer() throws IOException {
        ResourceConfig crunchifyResourceConfig = new PackagesResourceConfig("test");
        // This tutorial required and then enable below line: http://crunchify.me/1VIwInK
        //crunchifyResourceConfig.getContainerResponseFilters().add(CrunchifyCORSFilter.class);
        return HttpServerFactory.create(getURI(), crunchifyResourceConfig);
    }
 
    private static URI getURI() {
        return UriBuilder.fromUri("http://" + getHostName() + "/").port(8085).build();
    }
 
    private static String getHostName() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
    
}

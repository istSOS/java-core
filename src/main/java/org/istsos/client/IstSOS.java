package org.istsos.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.Response;
import com.google.gson.JsonParser;

/**
 * Main class for initializing IstSOS configuration and Server initialization. 
 * Implements HTTP asynchronous requests to IstSOS environment.
 *
 */
public class IstSOS{
	
	private HashMap<String, Server> servers = new HashMap<String, Server>(0);
	
	private static volatile IstSOS instance;
	
	private static AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
		
    private IstSOS() { }
    
    public static IstSOS getInstance() {
        if (instance == null ) {
            synchronized (IstSOS.class) {
                if (instance == null) {
                    instance = new IstSOS();
                }
            }
        }
        return instance;
    }
	
    public Server addServer(String name, Server server) {
        this.servers.put(name, server);
    	return server;
    }
	
    public Server initServer(String name, String url) {
        return this.addServer(name, new Server(name, url));
    }
	
    public Server initServer(String name, String url, String user, String password) {
    	return this.addServer(name, new Server(name, url, user, password));
    }
    
    public Server getServer(String name){
    	return this.servers.get(name);
    }
    
    public Collection<Server> getServers(){
    	return this.servers.values();
    }



    protected static void executeGet(String url, final IstSOSListener callback, ArrayList<String> realm){
    	
    	BoundRequestBuilder builder = asyncHttpClient.prepareGet(url);
    	
    	if(realm != null){
			Realm tmpRealm = new Realm.Builder(realm.get(0), realm.get(1))
					.setUsePreemptiveAuth(true)
					.setScheme(AuthScheme.BASIC)
					.build();
    		builder.setRealm(tmpRealm);
    	}
    	
    	builder.execute(new AsyncCompletionHandler<Integer>(){
			
		    @Override
		    public Integer onCompleted(Response response) throws Exception{
		    	
		    	System.out.println("Request executed..");
		    	
		        EventObject eventObject = new EventObject(
		        		Event.REQUEST, 
		        		new JsonParser()
					        .parse(response.getResponseBody())
					        .getAsJsonObject());
        		
        		if(callback != null){
        			callback.onSuccess(eventObject);
        		}
        		
		        return response.getStatusCode();
		    }
		    
		    @Override
		    public void onThrowable(Throwable t){
		    	System.out.println("Request error!!");
		    	System.out.println(t.getMessage());
		    	t.printStackTrace();
		    }
		    
		});
    	
    }
    
    //post request
    protected static void executePost(String url, String data, final IstSOSListener callback, ArrayList<String> realm) {
    	
    	BoundRequestBuilder builder = asyncHttpClient.preparePost(url).setBody(data);

    	if(realm != null){
			Realm tmpRealm = new Realm.Builder(realm.get(0), realm.get(1))
					.setUsePreemptiveAuth(true)
					.setScheme(AuthScheme.BASIC)
					.build();
			builder.setRealm(tmpRealm);
    	}
    	
    	builder.execute(new AsyncCompletionHandler<Integer>(){
    		
    		@Override
		    public Integer onCompleted(Response response) throws Exception {
    			
		    	System.out.println("Request executed..");
		    	
		        EventObject eventObject = new EventObject(
		        		Event.REQUEST, 
		        		new JsonParser()
					        .parse(response.getResponseBody())
					        .getAsJsonObject());
        		
        		if(callback != null){
        			callback.onSuccess(eventObject);
        		}
        		
		        return response.getStatusCode();
    			
    		}
    		
		    @Override
		    public void onThrowable(Throwable t){
		    	System.out.println("Request error!!");
		    	System.out.println(t.getMessage());
		    	t.printStackTrace();
		    }
		    
    	});
    	
    	
    }
    
    //put request
    protected static void executePut(String url, String data, final IstSOSListener callback, ArrayList<String> realm) {
    	
    	BoundRequestBuilder builder = asyncHttpClient.preparePut(url).setBody(data);
    	
    	if(realm != null){
			Realm tmpRealm = new Realm.Builder(realm.get(0), realm.get(1))
					.setUsePreemptiveAuth(true)
					.setScheme(AuthScheme.BASIC)
					.build();
			builder.setRealm(tmpRealm);
    	}
    	
    	builder.execute(new AsyncCompletionHandler<Integer>(){
    		
    		@Override
		    public Integer onCompleted(Response response) throws Exception {
    			
		    	System.out.println("Request executed..");
		    	
		        EventObject eventObject = new EventObject(
		        		Event.REQUEST, 
		        		new JsonParser()
					        .parse(response.getResponseBody())
					        .getAsJsonObject());
        		
        		if(callback != null){
        			callback.onSuccess(eventObject);
        		}
        		
		        return response.getStatusCode();
    			
    		}
    		
		    @Override
		    public void onThrowable(Throwable t){
		    	System.out.println("Request error!!");
		    	System.out.println(t.getMessage());
		    	t.printStackTrace();
		    }
		    
    	});
   

    }
    
    protected static void executeDelete(String url, String data, final IstSOSListener callback, ArrayList<String> realm){
    	
   	BoundRequestBuilder builder = asyncHttpClient.prepareDelete(url).setBody(data);
    	
    	if(realm != null){
			Realm tmpRealm = new Realm.Builder(realm.get(0), realm.get(1))
					.setUsePreemptiveAuth(true)
					.setScheme(AuthScheme.BASIC)
					.build();
			builder.setRealm(tmpRealm);
    	}
    	
    	builder.execute(new AsyncCompletionHandler<Integer>(){
    		
    		@Override
		    public Integer onCompleted(Response response) throws Exception {
    			
		    	System.out.println("Request executed..");
		    	
		        EventObject eventObject = new EventObject(
		        		Event.REQUEST, 
		        		new JsonParser()
					        .parse(response.getResponseBody())
					        .getAsJsonObject());
        		
        		if(callback != null){
        			callback.onSuccess(eventObject);
        		}
        		
		        return response.getStatusCode();
    			
    		}
    		
		    @Override
		    public void onThrowable(Throwable t){
		    	System.out.println("Request error!!");
		    	System.out.println(t.getMessage());
		    	t.printStackTrace();
		    }
		    
    	});
    	
    }

}
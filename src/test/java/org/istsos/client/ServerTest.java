/**
 * 
 */
package org.istsos.client;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests for Server class
 */
public class ServerTest {
	
	IstSOS sos = IstSOS.getInstance();
    ArrayList<Service> test_services = new ArrayList<>();
    
    
    @Before
    public void setUp(){
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        @SuppressWarnings("unused")
		Server server = sos.getServer(serverName);
    }

	/**
	 * Test method for {@link Server#loadServices(IstSOSListener)}.
	 */
	@Test
	public void testLoadServicesIstSOSListener() {
		
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
		
		server.loadServices(new IstSOSListener() {
			
			@Override
			public void onSuccess(EventObject event) {
				
				if (event.getEvent() == Event.SERVICE_LOADED){
					
					@SuppressWarnings("unchecked")
					ArrayList<Service> services = ((ArrayList<Service>)event.getObject());
					
					System.out.println(services.size() +  " services loaded.");
					
					for(Service service : services){
						System.out.println(service.getName());
						test_services.add(service);
					}
					
				}
			}
			
			@Override
			public void onError(EventObject event) {
				
			}
			
		});
	}
	
	/**
	 * Test method for {@link Server#getServices()}.
	 */
	@Test
	public void testGetServices() {
		
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
		System.out.println(server.getServices());
	}

	/**
	 * Test method for {@link Server#getService(java.lang.String)}.
	 */
	@Test
	public void testGetService() {
		
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
		System.out.println(server.getService("demo"));
	}

	/**
	 * Test method for {@link Server#getServerName()}.
	 */
	@Test
	public void testGetServerName() {
		
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
        
        System.out.println(server.getServerName());
	}

	/**
	 * Test method for {@link Server#getServerUrl()}.
	 */
	@Test
	public void testGetServerUrl() {
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
        
        System.out.println(server.getServerUrl());
	}

	/**
	 * Test method for {@link Server#getUser()}.
	 */
	@Test
	public void testGetUser() {
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
        System.out.println(server.getUser());
	}

	/**
	 * Test method for {@link Server#setUser(java.lang.String)}.
	 */
	@Test
	public void testSetUser() {
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
        server.setUser("istsos");
	}

	/**
	 * Test method for {@link Server#setPassword(java.lang.String)}.
	 */
	@Test
	public void testSetPassword() {
        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        Server server = sos.getServer(serverName);
        server.setPassword("simple");
	}
}

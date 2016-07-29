package istsos;


import java.io.IOException;
import java.util.ArrayList;

/**
 * DatabaseConnection class tests.
 * 
 * Tests on loadDatabase and validateDatabase are being done.
 *
 */
public class DatabaseConnectionTest {

	
	public static void main(String[] args) throws IOException {
		
		String demo = "demo";
		
		IstSOS istsos = IstSOS.getInstance();
		Server serverDemo = istsos.initServer(demo, "http://istsos.org/istsos/");
		
		serverDemo.loadServices(new IstSOSListener() {
			
			@Override
			public void onSuccess(EventObject event) {
				
				if (event.getEvent() == Event.SERVICE_LOADED){
					
					@SuppressWarnings("unchecked")
					ArrayList<Service> services = ((ArrayList<Service>)event.getObject());
					
					System.out.println(services.size() +  " services loaded.");
					
					for(Service service : services){
						System.out.println(" - " + service.getName());
						service.loadDatabase(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								service.validateDatabase((DatabaseConnection) event.getObject());
								
							}
							
							@Override
							public void onError(EventObject event) {
								// TODO Auto-generated method stub
								
							}
						});
						
					}
					
				}
			}
			
			@Override
			public void onError(EventObject event) {
				
			}
			
		});
		
	}

}

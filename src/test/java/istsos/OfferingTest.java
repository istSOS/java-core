package istsos;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Offerings test
 *
 */
public class OfferingTest {

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
					//1 services loaded
					
					for(Service service : services){
						System.out.println(" - " + service.getName());
						service.loadOfferings(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								if(event.getEvent() == Event.OFFERINGS_LOADED){
									
									
									@SuppressWarnings("unchecked")
									ArrayList<Offering> offerings = (ArrayList<Offering>)event.getObject(); 
									
									System.out.println(offerings.size() +  " offerings loaded.");
									//  offerings loaded
									
									for(Offering offering : offerings){
										//print offerings names
										System.out.println(" - " + offering.getName());
									}
								}
								
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

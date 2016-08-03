package org.istsos.client;


import java.io.IOException;
import java.util.ArrayList;

/**
 * ObservedProperty tests
 *
 */
public class ObservedPropertyTest {

	
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
						service.loadObservedProperties(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								if(event.getEvent() == Event.OBSERVED_PROPERTIES_LOADED){
									
									
									@SuppressWarnings("unchecked")
									ArrayList<ObservedProperty> obsProperties = (ArrayList<ObservedProperty>)event.getObject(); 
									
									System.out.println(obsProperties.size() +  " observed properties loaded.");
									//  observed properties loaded
									
									for(ObservedProperty obs : obsProperties){
										//print obs definitionURNs
										System.out.println(" - " + obs.getDefinition());
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

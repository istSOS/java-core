package org.istsos.client;

import java.io.IOException;
import java.util.ArrayList;

public class VirtualProcedureTest {

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
						service.loadVirtualProcedures(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								if(event.getEvent() == Event.VIRTUAL_PROCEDURES_LOADED){
									
									
									@SuppressWarnings("unchecked")
									ArrayList<VirtualProcedure> virtualProcedures = (ArrayList<VirtualProcedure>)event.getObject(); 
									
									System.out.println(virtualProcedures.size() +  " virtual procedures loaded.");
									//  virtual procedures loaded
									
									for(VirtualProcedure vp : virtualProcedures){
										//print virtula procedures property
										System.out.println(" - " + vp.getDescription());
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

package org.istsos.client;

import java.io.IOException;
import java.util.ArrayList;

/**
 * DataQuality class test
 * 
 * It tests loadDataQualities method from Service, as well as the
 * de/serialization capabilities.
 *
 */
public class DataQualityTest {

	
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
						service.loadDataQualities(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								if(event.getEvent() == Event.DATA_QUALITIES_LOADED){
									
									
									@SuppressWarnings("unchecked")
									ArrayList<DataQuality> dataQualities = (ArrayList<DataQuality>)event.getObject(); 
									
									System.out.println(dataQualities.size() +  " data qualities loaded.");
									//  data qualities loaded
									
									for(DataQuality dq : dataQualities){
										//print data quality names
										System.out.println(" - " + dq.getDataQualityName());
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

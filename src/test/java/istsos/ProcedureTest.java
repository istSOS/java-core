package istsos;

import java.io.IOException;
import java.util.ArrayList;

public class ProcedureTest {

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
					// services loaded
					
					for(Service service : services){
						System.out.println(" - " + service.getName());
						service.loadProcedures(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								if(event.getEvent() == Event.PROCEDURES_LOADED){
									
									
									@SuppressWarnings("unchecked")
									ArrayList<Procedure> procedures = (ArrayList<Procedure>)event.getObject(); 
									
									System.out.println(procedures.size() +  " procedures loaded.");
									//  procedures loaded
									
									for(Procedure procedure : procedures){
										//print procedures ids
										System.out.println(" - " + procedure.getId());
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

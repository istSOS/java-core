package istsos;


import java.io.IOException;
import java.util.ArrayList;


/**
 * Unit of Measure tests
 *
 */
public class UnitOfMeasureTest {


	
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
						service.loadUnitsOfMeasure(new IstSOSListener() {
							
							@Override
							public void onSuccess(EventObject event) {
								// TODO Auto-generated method stub
								
								if(event.getEvent() == Event.UOMS_LOADED){
									
									
									@SuppressWarnings("unchecked")
									ArrayList<UnitOfMeasure> uoms = (ArrayList<UnitOfMeasure>)event.getObject(); 
									
									System.out.println(uoms.size() +  " uoms loaded.");
									//  units of measure loaded
									
									for(UnitOfMeasure uom : uoms){
										//print data quality names
										System.out.println(" - " + uom.getUnitName());
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

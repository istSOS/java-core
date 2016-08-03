package org.istsos.client;

/**
 * Created by doscio on 13.07.16.
 */
public class Test {

    public static void main(String[] args) {

        IstSOS sos = IstSOS.getInstance();

        String serverName = "localhost";
        sos.initServer(serverName, "http://istsos.org/istsos/");

        final Server server = sos.getServer(serverName);

        server.loadServices(new IstSOSListener() {
            @Override
            public void onSuccess(EventObject event) {

                Service service = server.getService("demo");

                System.out.println(service.getName());
                
                service.describeSensor("BELLINZONA", new IstSOSListener() {
                    @Override
                    public void onSuccess(EventObject event) {
                        Procedure procedure = (Procedure) event.getObject();

                        System.out.println(procedure.getAssignedid());

                        System.out.println(procedure.getLocation());
                        System.out.println(procedure.getObservedproperties());

                    }

                    @Override
                    public void onError(EventObject event) {

                    }
                });
            }
            	

            @Override
            public void onError(EventObject event) {

            }

        });
    }
}

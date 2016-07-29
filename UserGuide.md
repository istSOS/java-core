
#User Guide

##Author: Florin-Daniel Cioloboc



To create an instance and initialize a server you use the following:

```java

	import istsos

	String demo = "demo";
	IstSOS istsos = IstSOS.getInstance();
	Server serverDemo = istsos.initServer(demo, "http://istsos.org/istsos/");

```

To use a method such as DescribeSensor you have to first load services:

```java

    server.loadServices(new IstSOSListener() {
        @Override
        public void onSuccess(EventObject event) {
            
        //do something
            
        @Override
        public void onError(EventObject event) {

        }

    });


```
Then inside loadServices you can call methods on the selected service, as below.

```java

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

```
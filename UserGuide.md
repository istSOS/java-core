
#User Guide

Author: Florin-Daniel Cioloboc
Last updated: 30.07.2016

## Overview

Consider this user guide as a tutorial to get you started on how to develop
with istSOS Java Core.


[Getting Started](#getting-started)


### Getting Started

Important to keep in mind that istSOS Java Core makes use of event listeners, therefore whenever you use a method
whether on a e.g. `service` or `server` instance you have to use a `IstSOSListener` as a parameter for all methods.

Whenever you develop keep in mind that first the object's configuration must be loaded, thus you can see the use of `IstSOSListener`, in order to keep the reference of the object.


### Import the Java Core

It follows the regular Java programming style of importing a library

```java

	import istsos;

```

Same applies if you want to use a specific class.

```java

	import istsos.Service;

```


To create an instance of `istSOS` you have to initialize it in the following way.

```java

	IstSOS istsos = IstSOS.getInstance();

```
For initializing a server, an instance of `Server` is declared followed by the `initServer` method on the
istsos instance previously created.

```java

        String demo = "demo";
        Server serverDemo = istsos.initServer(demo, "http://istsos.org/istsos/");

```

## Using Service

To load services as Java object you will have to use `loadServices` method on a `server` instance that
was previously initialized. 

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
        }

        @Override
        public void onError(EventObject event) {

        }
    });

```
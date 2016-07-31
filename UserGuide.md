
# User Guide

Author: Florin-Daniel Cioloboc
Last updated: 31.07.2016


##Table of contents

 - [Overview](#overview)
 - [Before getting started](#before-getting-started)
 - [Importing the Java Core](#importing-the-java-core)
 - [Create an instance of istSOS](#create-an-instance-of-istsos)
 - [Initialize a Server](#initialize-a-server)
 - [Using Services](#using-services)
 - [Load and Validate a Database Connection](#load-and-validate-a-database-connection)
 - [Describe Sensor](#describe-sensor)
 - [Register Sensor](#register-sensor)
 - [Get Observation](#get-observation)
   - [Offering](#offering)
   - [Procedure](#procedure)
   - [ObservedProperty](#observedproperty)
   - [More on Get Observation](#more-on-get-observation)
 - [Insert Observation](#insert-observation)


## Overview

Consider this user guide as a tutorial to get you started on how to develop
with istSOS Java Core. This will walk you through how to use all the features
currently available in the library.

More details about istSOS itself check -> [documentation](http://istsos.org/en/latest/doc/)

## Before getting started

Important to keep in mind that istSOS Java Core makes use of event listeners, therefore whenever you use a method
whether on a e.g. `service` or `server` instance you have to use a `IstSOSListener` as a parameter for all methods.

Whenever you develop keep in mind that first the object's configuration must be loaded, thus you can see the use of `IstSOSListener`, 
in order to keep the reference of the object.


### Importing the Java Core

It follows the regular Java programming style of importing a library

```java

	import istsos;

```

Same applies if you want to use a specific class. Below see an example of importing Service class.

```java

	import istsos.Service;

```

### Create an instance of istSOS

To create an instance of `istSOS` you have to initialize it in the following way.

```java

	IstSOS istsos = IstSOS.getInstance();

```

### Initialize a Server

There are two way to accomplish the initialization of a server.

One is by using `initServer` on the `istSOS` instance, then use `getServer` method and assign it into a Server instance. 
This is probably the most straightforward way you could think of when first trying this out.

```java
		
		//create a serverName
		String serverName = "localhost";

```


```java

		istsos.initServer(serverName, "http://istsos.org/istsos/");
		Server server = sos.getServer(serverName);
        
```

Second, you can shorten the process by directly assigning the `istSOS.initServer` to
a `Server` instance.

```java

        Server server = istsos.initServer(serverName, "http://istsos.org/istsos/");

```

Notice that you could have hard-coded the serverName into the initServer since it takes two strings as parameter if you really wanted.


### Using Services

To load services as Java object you will have to use `loadServices` method on a `server` instance that was previously initialized. 

```java

    server.loadServices(new IstSOSListener() {

        @Override
        public void onSuccess(EventObject event) {
            
        //apply other methods here
            
        @Override
        public void onError(EventObject event) {

    }

    });


```

Here you might want to notice that whenever you will make use of a method on a `Server`
instance or on a `Service`, you will have to create `IstSOSListener` in order to
specify what the response will be if the method is successful, likewise if you get an
error. 

As you might have got the hint this process makes use of Events and EventsListeners concept, know that you were right.

One of the **key features** of the `Service` instance is that it has the property of
storing lists of the data classes used in istSOS, whether its `Procedure`, `Offering`,
`ObservedProperty`. You can see more about in the [Get Observation](#get-observation) section.


### Load and Validate a Database Connection


To load a database, you have to apply the `loadDatabase` method on a `Service`. This return in a manner of speaking a connection to database of
the service. Once done, inside the nest you can use `validateDatabase`. 

```java
	
	//load the database
	service.loadDatabase(new IstSOSListener() {
							
		@Override
		public void onSuccess(EventObject event) {
		
			//validate database
			service.validateDatabase((DatabaseConnection) event.getObject());
								
		}
							
		@Override
		public void onError(EventObject event) {
					
								
		}
	});

```

### Describe sensor

In order to obtain a description of the sensor, you have to use `describeSensor`. 
Since this will return an object that is basically a `Procedure`, you might as well 
assign the result of request in a `Procedure`.


```java

    service.describeSensor("BELLINZONA", new IstSOSListener() {
        @Override
        public void onSuccess(EventObject event) {
        
            Procedure procedure = (Procedure) event.getObject();
            
        }

        @Override
        public void onError(EventObject event) {

        }
    });

```

### Register sensor

At this point you will see that this is a pretty convenient step-by-step approach to 
developing using the Java Core.

Since you have already used the `describeSensor` to retrieve a `Procedure`, we can use it
to register another sensor with `regiserSensor`. Simply put, a request was made for a
`Procedure`, that can be used to register a sensor. 


```java

    service.describeSensor("BELLINZONA", new IstSOSListener() {
        @Override
        public void onSuccess(EventObject event) {
        
            Procedure procedure = (Procedure) event.getObject();
            
            
            service.registerSensor(procedure, new IstSOSListener() {
							
				@Override
				public void onSuccess(EventObject event) {
						
								
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

```


### Get Observation

Getting an Observation follows pretty much the same pattern as before, although you have to keep in
mind that you will need additional parameters to make perform the request.

To better understand, let's have a look at the header for the method `getObservation`.

```java

		getObervation(Offering offering, Procedure procedure, ObservedProperty defUrn, 
			Date beginPosition, Date endPosition, IstSOSListener callback

```

Keep in mind that there are in total **2** `getObservation` methods so take care when using it because the input
differs quite in bit in terms of data structure. 

Looking at the header we notice that it requires an `Offering`, `Procedure`, `ObservedProperty`, and the dates
for the `Observation`, this way it will return data within that time series.

Those three things can all be obtained in the slightly similar as you did with `DatabaseConnection`, however there are a few things you need to do beforehand.


#### Offering

To retrieve an `Offering`, you would have to load all the offerings on the selected
service. To achieve that, you can follow the same pattern after using `loadServices`, 
to apply `loadOfferings` on `Service`. 

```java

	service.loadOfferings(new IstSOSListener() {
							
		@Override
		public void onSuccess(EventObject event) {
								
		
		}
							
		@Override
		public void onError(EventObject event) {

								
		}
	});


```
`loadOfferings` will return every `Offering` available in the `Service` as a list, there it is import to know that to access them you will have to request the list of `Offering` using another method available in the Java Core.

```java

	service.getOfferings();	//returns an ArrayList<Offering>

```

Use the predefined `getOffering` to get a specific offering.

```java

	service.getOffering(offeringName);

```

#### Procedure

Following closely the `Offering` part, you can apply the same technique for `Procedure`.

```java

	service.getProcedures(); //returns an ArrayList<Procedure>

```

From this point on, you don't have to implement a method for retrieving a specific `Procedure`,
you can use the predefined `getProcedure` method for that by specifying its name.

```java

	service.getProcedure(procedureName);
	
```

#### ObservedProperty

For `ObservedProperty` you can repeat the same process as before.

```java

	service.getObservedProperties(); //returns an ArrayyList<ObservedProperty>

```

Same as for `Procedure`

```java

	service.getObservedProperty(String defUrn);
	
```


#### More on Get Observation

For the second `getObservation` method notice that it takes as input a list of
`Procedure` and `ObservedProperty`. 

```java

	getObervation(Offering offering, List<Procedure> procedure, 
					List<ObservedProperty> defUrn, Date beginPosition, Date 
					endPosition, IstSOSListener callback)
```

You can make use of the predefined methods again to get the lists.

```java

	service.getProcedures();
	service.getObservedProperties();
	
```


### Insert Observation




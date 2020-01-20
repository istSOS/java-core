
# User Guide

Author: Florin-Daniel Cioloboc

Last updated: 22.08.2016


##Table of contents

 - [Overview](#overview)
 - [Before getting started](#before-getting-started)
 - [Short note about the structure](#short-note-about-the-structure)
 - [Importing the Java Core](#importing-the-java-core)
 - [Create an instance of istSOS](#create-an-instance-of-istsos)
 - [Initialize a Server](#initialize-a-server)
   - [Initialize once, use many times](#initialize-once-use-many-times)
   - [Server authentication](#server-authentication)
 - [Using Service](#using-service)
   - [Working with the same service](#working-with-the-same-service)
   - [Multiple services on a server](#multiple-services-on-a-server)
 - [Load and Validate a Database Connection](#load-and-validate-a-database-connection)
 - [Describe Sensor](#describe-sensor)
 - [Register Sensor](#register-sensor)
 - [Get Observation](#get-observation)
   - [Offering](#offering)
   - [Procedure](#procedure)
   - [More about Procedure class](#more-about-procedure-class)
   - [ObservedProperty](#observedproperty)
   - [More on Get Observation](#more-on-get-observation)
 - [Insert Observation](#insert-observation)
 - [Other data classes](#other-data-classes)
   - [DataQuality](#data-quality)
   - [VirtualProcedure](#virtualprocedure)
   - [UnitOfMeasure](#unitofmeasure)
   - [Provider](#provider)
     
   


## Overview

Consider this user guide as a sort of documentation to get you started on how to develop
with istSOS Java Core. This will walk you through how to use all the features
currently available in the library.

More details about istSOS itself check -> [documentation](http://istsos.org/en/latest/doc/)

## Before getting started

Important to keep in mind that istSOS Java Core makes use of event listeners, therefore whenever you use a method
whether on a e.g. `Service` or `Server` instance you have to use a `IstSOSListener` as a parameter for all methods.

Whenever you develop keep in mind that first the object's configuration must be loaded, thus you can see the use of `IstSOSListener`, 
in order to keep the reference of the object.

## Short note about the structure

Java Core is structured in 1 package

```java 

	org.istsos.client
```

And two sub-packages, one for `Observation` and another for `Procedure`

```java  

	org.istsos.client.observation```

```java  

	org.istsos.client.procedure ```


## Importing the Java Core

It follows the regular Java programming style of importing a library

```java

	import org.istsos.client;

```

Same applies if you want to use a specific class. Below see an example of importing Service class.

```java

	import org.istsos.client.Service;

```

## Create an instance of istSOS

To create an instance of `istSOS` you have to initialize it in the following way.

```java

	IstSOS istsos = IstSOS.getInstance();

```

## Initialize a Server

There are two way to accomplish the initialization of a server.

One is by using `initServer` on the `istSOS` instance, then use `getServer` method and assign it into a Server instance. 
This is probably the most straightforward way you could think of when first trying this out.

```java
		
		String serverName = "localhost";

```


```java

		istsos.initServer(serverName, "http://istsos.org/istsos/");
		Server server = istsos.getServer(serverName);
        
```

Second, you can shorten the process by directly assigning the `istSOS.initServer` to
a `Server` instance.

```java

        Server server = istsos.initServer(serverName, "http://istsos.org/istsos/");

```

Notice that you could have hard-coded the serverName into the initServer since it takes two strings as parameter if you really wanted.

### Initialize once, use many times

When developing applications, a good practice is to avoid initializing the `IstSOS` and
`Server` instances. Instead you can get the `Server` instance by using the `getInstance` method, followed by `getServer` with the name of the server.

```java

    final Server server = IstSOS.getInstance().getServer("localhost");


```

`IstSOS` only needs to be instantiated once, from there on you can use several methods
to get a connection to the object.


### Server authentication

If you have ever read the istSOS documentation then you know that it is possible to 
implement a login system. The Java Core also supports this in two ways.

Either when you initialize a server.

```java

	Server server = initServer(String name, String url, String user, String password);
	
```

Or the option of setting it later using setters after it was initialized.

```java
	
	server.setUser(String user);
	
	server.setPassword(String password);
	
```


## Using Service

To load services as Java object you will have to use `loadServices` method on a `server` instance that was previously initialized. 

```java

    server.loadServices(new IstSOSListener() {

        @Override
        public void onSuccess(EventObject event) {
            
        //apply other methods here
        }    
        @Override
        public void onError(EventObject event) {

    }

    });


```

Here you might want to notice that whenever you will make use of a method on a `Server`
instance or on a `Service`, you will have to create `IstSOSListener` in order to
specify what the response will be if the method is successful, likewise if you get an
error. 

As you might have got the hint this process makes use of **Events** and **EventsListeners** concept, know that you were right.

One of the **key features** of the `Service` instance is that it has the property of
storing lists of the data classes used in istSOS, whether its `Procedure`, `Offering`,
`ObservedProperty`. You can see more about in the [Get Observation](#get-observation) section.


### Working with the same service

When working with `Service`, you have to keep in mind that it is one of the most important things for
handling your istSOS requests. You need to use the same service if you want to request observations.

The trick is to use `getService` on the `Server` instance as a `Server` stores a list of services available.

```java

    Service service = server.getService("demo");

```

### Multiple services on a server 

In some cases you might need more services than a single one as every project has its own
specifications. If you have read the documentation, then you know that `istSOS` is capable
of that and so is the Java Core.

To retrieve all services present on a `Server` use the following:

```java

	server.getServices();

```

If you want to access a specific one, then you can use the simple `getService`.

```java

	server.getService(String serviceName)
	
```

It will return a `Service` object.


As a last thing about `Service`, remember the order `istSOS` -> `Server` -> `Service`. From there on you can use whatever methods are available in the service.



## Load and Validate a Database Connection


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

## Describe sensor

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

## Register sensor

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


## Get Observation

Getting an Observation follows pretty much the same pattern as before, although you have to keep in
mind that you will need additional parameters to make perform the request.

To better understand, let's have a look at the header for the method `getObservation`.

```java

		service.getObervation(Offering offering, Procedure procedure, 
						ObservedProperty defUrn, Date beginPosition, 
						Date endPosition, IstSOSListener callback)

```

Keep in mind that there are in total **2** `getObservation` methods so take care when using it because the input
differs quite in bit in terms of data structure. 

Looking at the header we notice that it requires an `Offering`, `Procedure`, `ObservedProperty`, and the dates
for the `Observation`, this way it will return data within that time series.

Those three things can all be obtained in the slightly similar as you did with `DatabaseConnection`, however there are a few things you need to do beforehand.


### Offering

To retrieve an `Offering`, you would have to load all the offerings on the selected
service. To achieve that, you can follow the same pattern after using `loadServices`, 
to apply `loadOfferings` on `Service`. 

```java

	service.loadOfferings(new IstSOSListener() {
							
		@Override
		public void onSuccess(EventObject event) {
								
				//insert code
				
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

### Procedure

Following closely the `Offering` part, you can apply the same technique for `Procedure`.

```java

	service.getProcedures(); //returns an ArrayList<Procedure>

```

From this point on, you don't have to implement a method for retrieving a specific `Procedure`,
you can use the predefined `getProcedure` method for that by specifying its name.

```java

	service.getProcedure(procedureName);
	
```

However, if you already know in advance which `Procedure` you require and would prefer not to go through the whole process of loading and getting all procedures, you can use the following method to request a specific `Procedure`.

```java

	service.getProcedure(String procedureName, final IstSOSListener callback){...};

```

A `Procedure` can be registered:

```java

	service.registerProcedure(Procedure procedure, final IstSOSListener callback){...});

```

or updated:

```java

	service.updateProcedure(Procedure procedure, final IstSOSListener callback){...});

```

These are implemented methods currently.

### More about Procedure class

Beyond the requests, `Procedure` is a class that is used in the context of an `Observation` or when describing or registering a sensor. In order to satisfy the requirements, a sub-package for `Procedure` was created.

This means that a `Procedure` properties will be serialized depending on the request.

### ObservedProperty

For `ObservedProperty` you can repeat the same process as before.

```java

	service.getObservedProperties(); //returns an ArrayyList<ObservedProperty>

```

Same as for `Procedure`

```java

	service.getObservedProperty(String defUrn);
	
```


### More on Get Observation

For the second `getObservation` method notice that it takes as input a list of
`Procedure` and `ObservedProperty`. 

```java

	getObervation(Offering offering, List<Procedure> procedure, 
					List<ObservedProperty> defUrn, Date beginPosition, 
					Date endPosition, IstSOSListener callback)
```

You can make use of the predefined methods again to get the lists.

```java

	service.getProcedures();
	service.getObservedProperties();
	
```


## Insert Observation

For this to work it would require you to use a `Procedure` and an `Observation` using
a similar technique as you used above in the previous sections.

```java

	insertObservation(Procedure procedure, Observation observation, IstSOSListener callback)
	
```

An `Observation` can be obtained fusing `getObservation` in case you want to retrieve
a specific one from the Service.

```java

	service.getObservation(Offering offering, Procedure procedure, ObservedProperty defUrn, 
				Date beginPosition, Date endPosition, IstSOSListener callback)
	
``` 
While a `Procedure` can be retrieved from the list of procedures.

## Other data classes

Besides the classes presented previously as part of the Observation related requests, the Java Core supports the rest of the objects as well.

* DataQuality
* VirtualProcedure
* UnitOfMeasure
* Provider

### Data Quality

In order to retrieve `DataQuality` instances from the `Service` you can use the following request:

```java

	service.loadDataQualities(new IstSOSListener(){...});


```

Accessing the list of data qualities you can use a implemented method for that:

```java

	service.getDataQualities();


```

To get a specific object from the list of data qualities, you can follow the
same pattern as the one used for `Procedure`, `Offering` class examples in the previous section. 

```java

	service.getDataQuality(int code);
	
```

Apart from loading there are requests which are available for registering, updating, and deleting data qualities from the service.

```java

	service.registerDataQuality(DataQuality dataQuality, final IstSOSListener callback){...});

```

```java

	service.updateDataQuality(DataQuality dataQuality, final IstSOSListener callback){...});

```

```java

	service.removeDataQuality(DataQuality dataQuality, final IstSOSListener callback){...});

```


The `int` code parameter stands for the code of the `DataQuality`.

All istSOS properties for this class were implemented.

* code (``   getDataQualityCode()  ``)
* name (`` getDataQualityName() ``)
* description (``   getDataQualityDescription() ``)


### VirtualProcedure

Obtaining the list of `VirtualProcedure` you can perform the following request:

```java

	service.loadVirtualProcedures(new IstSOSListener() {...});

```

From there you can retrieve the available virtual procedures from the service instance using the following method:

```

	service.getVirtualProcedures();

```

At the moment, load and get are the only methods available.


From a `VirtualProcedure` you can obtain the code and the `RatingCurveParameters` as those two are the available properties.

Keep in mind that RatingCurve is a class of its own, which means that you can get every property it has available by using one of the implemented methods for it.

In comparison to other classes, a `VirtualProcedure` has two special objects that it works with, namely `Code`, and `RatingCurve`. The former is the one that allows you to
add `Python` code to the istSOS VirtualProcedure.


**Code**

The following code below  is to give you a glimpse of what can be done:

```java

	service.registerVirtualProcedureCode(VirtualProcedure virtualProcedure, final IstSOSListener callback){...});
	
```

```java

	service.loadVirtualProcedureCode(VirtualProcedure virtualProcedure, final IstSOSListener callback){...});

```

Additional methods for updating and removing `Code` are available in the same format.

**Rating Curve**

In comparison to `Code`, update method is not available for RatingCurve.


```java

	service.loadVirtualProcedureRatingCurve(VirtualProcedure virtualProcedure, final IstSOSListener callback){...});

```

```java

	service.registerVirtualProcedureRatingCurve(VirtualProcedure virtualProcedure, final IstSOSListener callback){...});

```

```java

	service.removeVirtualProcedureRatingCurve(VirtualProcedure virtualProcedure, final IstSOSListener callback){...});

```

### UnitOfMeasure

Working with `UnitOfMeasure` can be done in a similar fashion as it was done with the other classes.

Requesting units of measure to be loaded into the `Service` object:

```java

	service.loadUnitsOfMeasure(new IstSOSListener() {...});

```

Retrieving the list of `UnitOfMeasure` from `Service`:

```java

	service.getUnitsOfMeasure();
	
```

Getting a specific `UnitOfMeasure` can be done using `getUom` which returns a
`UnitOfMeasure` object.
 

```java

	service.getUom(String uomName);
	
```

To register a `UnitOfMeasure`:

```java

	service.registerUnitOfMeasure(UnitOfMeasure uom, final IstSOSListener callback){...});

```

update:

```java

	service.updateUnitOfMeasure(UnitOfMeasure uom, final IstSOSListener callback){...});

```

remove:

```java

	service.removeUnitOfMeasure(UnitOfMeasure uom, final IstSOSListener callback){...});
	
```

`UnitOfMeasure` has the following properties:

* Name
* Description
* List of Procedures

### Provider

This perhaps has less influence than the others as it will affect the information about the Service Provider. 

Nonetheless, should you need to make modifications to it, feel free to use it.

Currently, two methods are offered in the `Service` class to handle this aspect.

```java

	loadProvider(final IstSOSListener callback){...});

```

and an update method:

```java

	updateProvider(Provider provider, final IstSOSListener callback){...});
	
```

As for properties, `Provider` class has the properties implemented that are used by istSOS.

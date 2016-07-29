# istSOS Java Core Library



## Introduction

The istSOS Java Core Library is mainly an REST API wrapper. It will expose in Java language the communication with the istSOS WA REST interface.

As istSOS was built with Python, the Java Core was designed to be an independent library for Java developers to customize
their own programs using Java. 

Secondly, it was designed to serve as a starting point for the development of Android library for istSOS.

## Features

Configured as a Gradle project to handle dependencies in a swift and efficient way

Establish an IstSOS instance with own server and services

All data classes present in istSOS are included

Developed to handle HTTP requests asynchronously to a server using [AsyncHTTPClient] (https://github.com/AsyncHttpClient/async-http-client)

Handles JSON de-serialization and serialization using [Gson] (https://github.com/google/gson/blob/master/UserGuide.md)

Developed for Java 8

## Installation

Currently, you have to `git clone ` this repository or download it as a zip.

## Usage

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

## Documentation

Check out the [GitHub wiki] (https://github.com/masterflorin/java-core/wiki) of this repository.

## Support (Questions and issues)

For the moment, opening an issue on this GitHub repository would be the only option at the moment.

## Authors

Florin-Daniel Cioloboc ([@florincioloboc](https://twitter.com/florincioloboc)) implemented the Java Core during Google Summer of Code 2016.

Mentors from istSOS: Mirko Cardoso, Milan Antonovic.

## Last time updated

21.07.2016

## Useful links

istSOS platform [here] (http://istsos.org/)
 
istSOS mailing list [here] (https://groups.google.com/forum/#!forum/istsos)

GSoC development log on OSGeo [here] (https://wiki.osgeo.org/wiki/Android_istSOS)
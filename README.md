# istSOS Java Core Library


<p align="center">
<b><a href="#introduction">Introduction</a></b>
|
<b><a href="#features">Features</a></b>
|
<b><a href="#installation">Installation</a></b>
|
<b><a href="#documentation">Documentation</a></b>
|
<b><a href="#support">Support</a></b>
|
<b><a href="#author">Author</a></b>
|
<b><a href="#release history">Release history</a></b>
|
<b><a href="#useful links">Useful links</a></b>
</p>


## Introduction


The istSOS Java Core Library is mainly an REST API wrapper. It will expose in Java language the communication with the istSOS WA REST interface.

As istSOS was built with Python, the Java Core was designed to be an independent library for Java developers to customize their own programs using Java. 

Secondly, it was designed to serve as a starting point for the development of Android library for istSOS.

## Features

* Configured as a Gradle project to handle dependencies in a swift and efficient way
* Establish an IstSOS instance with own server and services
* All data classes present in istSOS are included
* Developed to handle HTTP requests asynchronously to a server using [AsyncHTTPClient] (https://github.com/AsyncHttpClient/async-http-client)
* Handles JSON de-serialization and serialization using [Gson] (https://github.com/google/gson)
* Developed for Java 8
* Can be used as a library for Android development

## Installation

There are two options for using this library:
* look inside the build -> libs and find the java-core.jar
* `git clone ` this repo, and create jar using Gradle command `gradle buildJar`

## Documentation

* JavaCore [UserGuide](https://github.com/masterflorin/java-core/blob/master/UserGuide.md): Tutorial on how to get started.
* JavaCore [API](https://github.com/masterflorin/java-core/wiki): references to classes, methods.
* JavaCore [Version History](https://github.com/masterflorin/java-core/blob/master/VERSION.md): see release history of library.

## Support (Requests and issues)

Open an issue in the [github issue tracker](https://github.com/masterflorin/java-core/issues) for **bugs** and **requesting new features**.

## Author

Florin-Daniel Cioloboc ([@florincioloboc](https://twitter.com/florincioloboc)) implemented during Google Summer of Code 2016.

Mentors from istSOS: Mirko Cardoso, Milan Antonovic.

## Release history

* Last updated: 30.07.2016
* Version: 0.1

## Useful links

* istSOS [website](http://istsos.org/)
* istSOS [mailing list](https://groups.google.com/forum/#!forum/istsos)
* GSoC development log on [OSGeo wiki](https://wiki.osgeo.org/wiki/Android_istSOS)
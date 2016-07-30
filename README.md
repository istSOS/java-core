# istSOS Java Core Library




<p align="center">
<b><a href="#about">About</a></b>
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
<b><a href="#release-history">Release history</a></b>
|
<b><a href="#useful-links">Useful links</a></b>
</p>




## About

Designed as a simple to use REST API wrapper to expose communication with the [istSOS](http://istsos.org/) WA REST interface. 

Serves a starting point for developing Android apps for istSOS platform.

## Features

* Configured as a Gradle-based project to handle dependencies
* Developed to handle HTTP requests asynchronously using [AsyncHTTPClient] (https://github.com/AsyncHttpClient/async-http-client)
* Handles JSON (de)serialization using [Gson] (https://github.com/google/gson)
* Built for Java 8 and above
* Usable as a library in Android development

## Installation

There are two options for using this library:
* Look inside the following folders path `build` -> `libs` and find the `java-core.jar`
* `git clone ` this repo, then create the jar using Gradle command `gradle buildJar` in a terminal

## Documentation

* JavaCore [UserGuide](https://github.com/masterflorin/java-core/blob/master/UserGuide.md): Tutorial on how to get started.
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
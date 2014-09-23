# Roboconf's Maven Plug-in
[![Build Status](http://travis-ci.org/roboconf/roboconf-maven-plugin.png?branch=master)](http://travis-ci.org/roboconf/roboconf-maven-plugin)

Website: [http://roboconf.net](http://roboconf.net)  
Licensed under the terms of the **Apache License v2**.

-------------------------------------------------------

A Maven plug-in dedicated to Roboconf projects.  
This plug-in supports the following goals.

* **initialize**: configure the project (model directory, build output).
* **validate-project**: validate the project's structure.
* **resolve-dependencies**: resolve and import dependencies.
* **validate-application**: validate the Roboconf application.
* **package**: package a Roboconf application as a ZIP file.

The validation of the application is performed after dependency resolution and resource filtering.
It means we can insert Maven properties in the application resources.

Find information about Roboconf on [its web site](http://roboconf.net).  
For later:

* **doc**: generate documentation based on templates and the Roboconf application files.
* **run**: execute actions after a build (such as deploy the application, etc).
* **test**: execute user scenarios to test the configuration files.

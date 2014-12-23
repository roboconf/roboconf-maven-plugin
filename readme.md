# Roboconf's Maven Plug-in
[![Build Status](http://travis-ci.org/roboconf/roboconf-maven-plugin.png?branch=master)](http://travis-ci.org/roboconf/roboconf-maven-plugin)
[![Coverage Status](http://coveralls.io/repos/roboconf/roboconf-platform/badge.png)](http://coveralls.io/r/roboconf/roboconf-maven-plugin)
[![License](https://pypip.in/license/apache-libcloud/badge.png)](http://www.apache.org/licenses/LICENSE-2.0)

Website: [http://roboconf.net](http://roboconf.net)  
Licensed under the terms of the **Apache License v2**.

A Maven plug-in dedicated to Roboconf projects.  
This plug-in supports the following goals.

* **initialize**: configure the project (model directory, build output).
* **validate-project**: validate the project's structure.
* **validate-application**: validate the Roboconf application.
* **package**: package a Roboconf application as a ZIP file.

The validation of the application is performed after dependency resolution and resource filtering.  
It means we can insert Maven properties in the application resources.

Find information about Roboconf on [its web site](http://roboconf.net).  
For later:

* **resolve-dependencies**: resolve and import dependencies.
* **doc**: generate documentation based on templates and the Roboconf application files.
* **run**: execute actions after a build (such as deploy the application, etc).
* **test**: execute user scenarios to test the configuration files.

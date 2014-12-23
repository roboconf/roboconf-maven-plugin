#!/bin/bash

# Get the Java version.
# Java 1.5 will give 15.
# Java 1.6 will give 16.
# Java 1.7 will give 17.
VER=`java -version 2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q'`

# We build for several JDKs on Travis.
# Some actions, like analyzing the code (Coveralls) and uploading
# artifacts on a Maven repository, should only be made for one version.

# If the version is 1.6, then perform the following actions.
# 1. Upload artifacts to Sonatype.
# 2. Use -q option to only display Maven errors and warnings.
# 3. Use --settings to force the usage of our "settings.xml" file.
# 4. Enable the profile that generates the javadoc and the sources archives.

if [ $VER == "16" ]; then
	mvn clean cobertura:cobertura coveralls:report deploy -q --settings settings.xml -P jdoc-and-sources
else
	echo "No action to undertake (not a JDK 6)."
fi

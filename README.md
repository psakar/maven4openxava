maven4openxava
==============

# Quick start:
* git clone https://github.com/jecuendet/maven4openxava

# Build openxava jars
* cd dist
* ./maven-create-install.sh : *This will take some minutes*

# This will build the provided OxApp template
* cd ../template
* mvn -Pnot clean install   :  *-Pnot avoids running Unit Tests that still fail*
* You are done!

# Running
* Open Intellij or Eclipse
* import the template application with the provided pom.xml
* Open the OxAppTomcatRunner class
* Run/Debug it
* Point your browser at http://localhost:8083/OxApp/
* You are done!


# Creating a basic application with the archetype

rm -Rf carenet/  ; mvn archetype:generate -DarchetypeGroupId=org.openxava -DarchetypeArtifactId=openxava-application-archetype -DarchetypeVersion=1.0.1-SNAPSHOT -DgroupId=ch.jesc.carenet -DartifactId=carenet^C


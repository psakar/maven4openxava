maven4openxava
==============

# Quick start:
* git clone https://github.com/jecuendet/maven4openxava

# Build openxava jars
* cd dist
* ./maven-create-install.sh : *This will take some minutes*

# This will build the OxApp template
* cd ../template
* mvn -Pnot clean install   :  *-Pnot avoids running Unit Tests that still fail*
* You are done!

# Running
* Open Intellij or Eclipse
* import the template application with the provided pom.xml
* Open the OxAppTomcatRunner class
* Run/Debug it
* Point your browser at http://localhost:8083/OxApp/modules/Teacher
* You are done!



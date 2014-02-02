maven4openxava
==============

Quick start:
- git clone https://github.com/jecuendet/maven4openxava
- cd dist
# will create maven artifacts with OpenXava
# and install them locally in .m2/repository
- ./maven-create-install.sh
- cd ../template
# -Pnot avoids running Unit Tests that still fail
- mvn -Pnot clean install
Done!

- Open Intellij or Eclipse
- import the template application with the provided pom.xml
- Open the OxAppTomcatRunner class
- Run/Debug it
- Point your browser at http://localhost:8083/OxApp/modules/Teacher
Done!



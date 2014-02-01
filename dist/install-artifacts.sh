#!/bin/bash

groupId=org.openxava.deps
version=4.9
pomfile=gen-pom.xml
lib=openxava-4.9/workspace/OpenXava/lib


#(cd openxava-${version}/workspace/MySchool && ant)


echo "" > $pomfile

for f in $(ls $lib) ; do

  name=$(echo $f | sed -e 's/\.jar//')
  echo "Installing $name ..."

  echo "<dependency>" >> $pomfile
  echo "<groupId>${groupId}</groupId>" >> $pomfile
  echo "<artifactId>${name}</artifactId>" >> $pomfile
  echo "<version>${version}</version>" >> $pomfile
  echo "</dependency>" >> $pomfile

  if [ 0 = 1 ] ; then
      mvn install:install-file -Dfile=$lib/$f \
                    -DgroupId=$groupId \
                    -DartifactId=$name \
                             -Dversion=$version \
                             -Dpackaging=jar \
                             -DgeneratePom=true
  fi

  if [ $? != 0 ]; then
    exit 1
  fi
  echo "$name done ..."


done

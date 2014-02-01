#!/bin/bash

version=4.9

#(cd openxava-${version}/workspace/MySchool && ant)

lib=openxava-4.9/workspace/OpenXava/lib

for f in $(ls $lib) ; do

  name=$(echo $f | sed -e 's/\.jar//')
  echo "Installing $name ..."
  
  mvn install:install-file -Dfile=$lib/$f \
			    -DgroupId=org.openxava.deps \
			    -DartifactId=$name \
                         -Dversion=$version \
                         -Dpackaging=jar \
                         -DgeneratePom=true
                         
  if [ $? != 0 ]; then
    exit 1
  fi
  echo "$name done ..."  
  

done


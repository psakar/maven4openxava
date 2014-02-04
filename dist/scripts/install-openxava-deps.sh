#!/bin/bash

groupId=org.openxava.deps
version=$1
pomfile=/tmp/ox-deps.txt
openxava=openxava/workspace

if [ -z $version ]; then
	echo "Usage: $0 <version>"
	exit 1
fi






function upload_from_dir {
    lib=$1
    echo "Lib: $lib"
    #exit 9

    for f in $(ls $lib) ; do

        if [ $f == "openxava.jar" ]; then
            echo "Skipping $f ..."
            continue;
        fi

        echo "Processing $f ..."

      name=$(echo $f | sed -e 's/\.jar//')

      echo "<dependency>" >> $pomfile
      echo "<groupId>${groupId}</groupId>" >> $pomfile
      echo "<artifactId>${name}</artifactId>" >> $pomfile
      echo "<version>${version}</version>" >> $pomfile
      echo "</dependency>" >> $pomfile

      # To disable installing while debugging the script
      if [ 0 = 0 ] ; then
          mvn install:install-file -Dfile=$lib/$f \
                        -DgroupId=$groupId \
                        -DartifactId=$name \
                                 -Dversion=$version \
                                 -Dpackaging=jar \
                                 -DgeneratePom=true

        if [ $? != 0 ]; then
            exit 1
        fi

        echo "Installation of $groupId:$name:$version done ."

      fi

    done
}


echo "" > $pomfile

upload_from_dir ${openxava}/OpenXava/lib
upload_from_dir ${openxava}/MySchool/web/WEB-INF/lib


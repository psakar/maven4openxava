#!/bin/bash

groupId=org.openxava.deps
version=4.9
pomfile=gen-pom.xml
openxava=openxava-4.9/workspace/OpenXava


#(cd openxava-${version}/workspace/MySchool && ant)




function upload_from_dir {
    lib=$1
    echo "Lib: $lib"
    #exit 9

    for f in $(ls $lib) ; do

      name=$(echo $f | sed -e 's/\.jar//')

      echo "<dependency>" >> $pomfile
      echo "<groupId>${groupId}</groupId>" >> $pomfile
      echo "<artifactId>${name}</artifactId>" >> $pomfile
      echo "<version>${version}</version>" >> $pomfile
      echo "</dependency>" >> $pomfile

      if [ 0 = 0 ] ; then
          mvn install:install-file -Dfile=$lib/$f \
                        -DgroupId=$groupId \
                        -DartifactId=$name \
                                 -Dversion=$version \
                                 -Dpackaging=jar \
                                 -DgeneratePom=true

        echo "Installation of $groupId:$name:$version done ."

      fi

      if [ $? != 0 ]; then
        exit 1
      fi


    done
}


echo "" > $pomfile

upload_from_dir ${openxava}/lib
upload_from_dir ${openxava}/web/WEB-INF/lib

exit 0;

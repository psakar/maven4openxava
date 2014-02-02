
src=openxava-4.9/workspace/OpenXava
dest_openxava=artifacts/openxava-official
dest_testing=artifacts/testing


# openxava : mkdir
rm -Rf $dest_openxava/src
mkdir -p $dest_openxava/src/main/java
mkdir -p $dest_openxava/src/main/resources

# openxava : resources
cp -r $src/xava/* $dest_openxava/src/main/resources
cp -r $src/properties/* $dest_openxava/src/main/resources
cp -r $src/bin/* $dest_openxava/src/main/resources
cp -r $src/web $dest_openxava/src/main/resources/xava
rm $dest_openxava/src/main/resources/xava/*.jsp

# openxava : src/main
cp -r $src/src/* $dest_openxava/src/main/java
rm -Rf $dest_openxava/src/main/java/org/openxava/tests

# testing : src/main
rm -Rf $dest_testing/src
mkdir -p $dest_testing/src/main/java/org/openxava
cp -r $src/src/org/openxava/tests $dest_testing/src/main/java/org/openxava





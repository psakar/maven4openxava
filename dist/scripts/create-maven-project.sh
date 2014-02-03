
src=openxava-4.9/workspace/OpenXava
src_addons=artifacts/openxava-addons
dest_openxava=artifacts/openxava
src_testing_addons=artifacts/openxava-testing-addons
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
#rm $dest_openxava/src/main/resources/xava/*.jsp

# openxava : src/main
cp -r $src/src/* $dest_openxava/src/main/java
rm -Rf $dest_openxava/src/main/java/org/openxava/tests

# testing : src/main
rm -Rf $dest_testing/src
mkdir -p $dest_testing/src/main/java/org/openxava
cp -r $src/src/org/openxava/tests $dest_testing/src/main/java/org/openxava

# Copy addons to openxava
cp -r $src_addons/src $dest_openxava/

# Copy addons to openxava testing
cp -r $src_testing_addons/src $dest_testing/

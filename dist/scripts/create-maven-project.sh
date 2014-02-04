
src=openxava/workspace/OpenXava
src_addons=artifacts/openxava-addons
dest_openxava=artifacts/openxava
src_testing_addons=artifacts/testing-addons
dest_testing=artifacts/testing
dest_archetype=artifacts/archetype


# openxava : mkdir
rm -Rf $dest_openxava/src
mkdir -p $dest_openxava/src/main/java
mkdir -p $dest_openxava/src/main/resources

# openxava : resources
cp -r $src/xava/* $dest_openxava/src/main/resources
cp -r $src/properties/* $dest_openxava/src/main/resources
cp -r $src/bin/* $dest_openxava/src/main/resources
cp -r $src/web $dest_openxava/src/main/resources/xava
rm -Rf $dest_archetype/src/main/resources/archetype-resources/webapp/xava
cp -r $src/web $dest_archetype/src/main/resources/archetype-resources/webapp
mv $dest_archetype/src/main/resources/archetype-resources/webapp/web $dest_archetype/src/main/resources/archetype-resources/webapp/xava


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

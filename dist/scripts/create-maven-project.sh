
src=openxava-4.9/workspace/OpenXava
dest_openxava=artifacts/openxava
dest_testing=artifacts/testing


# resources
rm -Rf $dest_openxava/src/main/resources/*
cp -r $src/xava $dest_openxava/src/main/resources
cp -r $src/properties/* $dest_openxava/src/main/resources

# src/main
rm -Rf $dest_openxava/src/main/java/*
cp -r $src/src/* $dest_openxava/src/main/java
rm -Rf $dest_openxava/src/main/java/org/openxava/tests


# src/test
rm -Rf $dest_testing/src/main/java/*
mkdir -p $dest_testing/src/test/java/org/openxava
cp -r $src/src/org/openxava/tests $dest_testing/src/test/java/org/openxava


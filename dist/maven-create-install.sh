
# Build OpenXava
(cd openxava/workspace/MySchool && ant)

# Install OpenXava deps
scripts/install-openxava-deps.sh

# Generate and install OpenXava artifact
scripts/create-maven-project.sh

(cd artifacts && mvn clean install)

echo "Done!"

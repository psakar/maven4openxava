
# Make sure prerequisite environment variables are set
if [ -z "$JAVA_HOME" -a -z "$JRE_HOME" ]; then
  echo "Neither the JAVA_HOME nor the JRE_HOME environment variable is defined"
  echo "At least one of these environment variable is needed to run this program"
  exit 2
fi
if [ -z "$JRE_HOME" ]; then
  JRE_HOME="$JAVA_HOME"
fi

PRG="$0"
PRGDIR=`dirname "$PRG"`
TOMCAT_HOME=../$PRGDIR

$JRE_HOME/bin/java -classpath $TOMCAT_HOME/lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

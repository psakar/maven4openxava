package org.openxava.tomcat;

import org.apache.catalina.Host;
import org.apache.catalina.valves.AccessLogValve;
import tomcat.TomcatRunner;

import java.io.IOException;

public class OxAppTomcatRunner extends TomcatRunner {

	public static void main(String[] args) throws Exception {

		final String webappDir = "webapp";

		final String contextPath = "/OxApp";
		final String contextXml = "webapp/META-INF/context.xml";

		final OxAppTomcatRunner runner = new OxAppTomcatRunner(".", contextPath, 8083, webappDir, contextXml, null);
		runner.start();

		// Looooooooooooooooooong wait time........
		Thread.sleep(100000000000000L);
	}

    public OxAppTomcatRunner(String currentDir, String contextPath, Integer port, String webappDir, String contextXmlPath, String userDatabasePath) throws IOException {
        super(currentDir, contextPath, port, webappDir, contextXmlPath, userDatabasePath);
    }

    @Override
    protected void addCustomContexts(Host host) throws IOException {
        super.addCustomContexts(host);
        // Create access.log
        AccessLogValve accessLogValve = new AccessLogValve();
        accessLogValve.setPrefix("localhost_access_log.");
        accessLogValve.setSuffix(".txt");
        accessLogValve.setPattern("common");
        host.getPipeline().addValve(accessLogValve);
    }
}

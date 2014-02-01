package org.openxava.tomcat;

import tomcat.TomcatRunner;

import java.io.IOException;

public class OxAppTomcatRunner extends TomcatRunner {

	public static void main(String[] args) throws Exception {

		final String webappDir = "webapp";

		final String contextPath = "/oxapp";

		final OxAppTomcatRunner runner = new OxAppTomcatRunner(".", contextPath, 8083, webappDir, null, null);
		runner.start();
	}

    public OxAppTomcatRunner(String currentDir, String contextPath, Integer port, String webappDir, String contextXmlPath, String userDatabasePath) throws IOException {
        super(currentDir, contextPath, port, webappDir, contextXmlPath, userDatabasePath);
    }

//    @Override
//    protected void addCustomContexts(Host host) throws IOException {
//        super.addCustomContexts(host);
//        // Create access.log
//        AccessLogValve accessLogValve = new AccessLogValve();
//        accessLogValve.setPrefix("localhost_access_log.");
//        accessLogValve.setSuffix(".txt");
//        accessLogValve.setPattern("common");
//        host.getPipeline().addValve(accessLogValve);
//    }
}

package org.openxava.tomcat;

import java.io.IOException;

public class OpenXavaTomcatRunner extends TomcatRunner {

	public static void main(String[] args) throws Exception {

		final String webappDir = "webapp";

		final String contextPath = "/ox-app";

		final OpenXavaTomcatRunner runner = new OpenXavaTomcatRunner(".", contextPath, 8083, webappDir, null, null);
		runner.start();
	}

    public OpenXavaTomcatRunner(String currentDir, String contextPath, Integer port, String webappDir, String contextXmlPath, String userDatabasePath) throws IOException {
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

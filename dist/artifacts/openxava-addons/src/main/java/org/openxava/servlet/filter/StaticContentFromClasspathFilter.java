package org.openxava.servlet.filter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/*
 * See http://www.kuligowski.pl/java/rest-style-urls-and-url-mapping-for-static-content-apache-tomcat,5
 */
/**
 * To add headers, add this to web.xml, in your <filter> tag: <init-param> <param-name>Cache-Control</param-name> <param-value>max-age=3600,
 * public</param-value> </init-param>
 *
 */
public class StaticContentFromClasspathFilter extends AbstractContentFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(StaticContentFromClasspathFilter.class);

	private Boolean forwardRequest = false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);

		final String str = getConfig().getInitParameter("forwardRequest");
		assert str != null; // "Le paramètre forwardRequest est mandatory (true|false)";
		forwardRequest = str != null && str.equals("true");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {
		boolean forward = true;

		if (req instanceof HttpServletRequest) {
			final HttpServletRequest request = (HttpServletRequest)req;
			final HttpServletResponse response = (HttpServletResponse)resp;

			final String path = getPathFromRequest(request);

			// Folder
			if (path.endsWith("/")) {
				try {
					serveFolder(response.getOutputStream(), path);
				}
				catch (UnsupportedOperationException e) {
					sendTryServingParent(response.getOutputStream(), path);
				}
				catch (URISyntaxException e) {
					e.printStackTrace();
				}
				return;
			}

			InputStream is = getClass().getResourceAsStream(path);
			if (is != null) {
				try {
					// set the provided HTTP response parameters
					for (Enumeration<String> e = getConfig().getInitParameterNames(); e.hasMoreElements();) {
						String headerName = e.nextElement();
						response.addHeader(headerName, getConfig().getInitParameter(headerName));
					}

					addContentType(response, path);

					IOUtils.copy(is, response.getOutputStream());
					forward = false;
				}
				finally {
					IOUtils.closeQuietly(is);
				}
			}
		}

		if (forward && forwardRequest) {
			// Pas trouvé ...
			getDefaultDispatcher().forward(req, resp);
		}
	}

	void sendTryServingParent(OutputStream os, String path) {
		final PrintWriter out = new PrintWriter(os);
		try {
			out.write("<html><head><title>Index of ");
			out.write(path);
			out.write("</title></head><body>");
			out.write("Error: cannot browse");
			out.write(path);
			out.write("<br/>");
			out.write("Try <a href='..'>..</a>");
			out.write("<br/><br/><br/><br/>");
			out.write("<br/><br/><address>Shared-Web/DefaultFilter</address>");
			out.write("</body>");
			out.write("</html>");
			out.flush();
		}
		catch (Exception e) {
		}
		finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * @param os
	 * @param path
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	void serveFolder(OutputStream os, String path) throws URISyntaxException, IOException {
		final PrintWriter out = new PrintWriter(os);
		try {
			out.write("<html><head><title>Index of");
			out.write(path);
			out.write("</title></head><body>");
			out.write("<h1>Index of ");
			out.write(path);
			out.write("</h1>");

			out.write("<ul>");
			out.write("<li><a href=\"..\">Parent Directory</a></li>");
			String[] files = getResourceListing(path);
			Arrays.sort(files);
			for (String file : files) {
				out.write("<li>");
				out.write("<a href=\"");
				out.write(file);
				String[] spl = file.split("/");
				if (!spl[spl.length-1].contains(".")) {
					out.write('/');
				}
				out.write("\">");
				out.write(file);
				out.write("</a>");
				out.write("</li>");
			}
			out.write("</ul>");
			out.write("<br/><br/><address>Shared-Web/DefaultFilter</address>");
			out.write("</body>");
			out.write("</html>");
			out.flush();
		}
		catch (Exception e) {
		}
		finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * List directory contents for a resource folder. Not recursive. This is basically a brute-force implementation. Works for regular files
	 * and also JARs.
	 *
	 * @author Greg Briggs
	 * @param path
	 *            Should end with "/", but not start with one.
	 * @return Just the name of each member item, not the full paths.
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	String[] getResourceListing(String path) throws URISyntaxException, IOException {
		URL dirURL = getClass().getResource(path);
		if (dirURL != null && dirURL.getProtocol().equals("file")) {
			/* A file path: easy enough */
			return new File(dirURL.toURI()).list();
		}

		if (dirURL == null) {
			/*
			 * In case of a jar file, we can't actually find a directory. Have to assume the same jar as clazz.
			 */
			String me = getClass().getName().replace(".", "/") + ".class";
			dirURL = getClass().getClassLoader().getResource(me);
		}

		if (dirURL.getProtocol().equals("jar")) {
			/* A JAR path */
			String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); // strip out only the JAR file
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Enumeration<JarEntry> entries = jar.entries(); // gives ALL entries in jar
			Set<String> result = new HashSet<String>(); // avoid duplicates in case it is a subdirectory
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.startsWith(path)) { // filter according to the path
					String entry = name.substring(path.length());
					int checkSubdir = entry.indexOf("/");
					if (checkSubdir >= 0) {
						// if it is a subdirectory, we just return the directory name
						entry = entry.substring(0, checkSubdir);
					}
					result.add(entry);
				}
			}
			return result.toArray(new String[result.size()]);
		}

		throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
	}

}

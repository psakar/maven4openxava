package org.openxava.servlet.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;

public abstract class AbstractContentFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(AbstractContentFilter.class);

	public static final String DISPATCHER_REQUEST_PATH_ATTR = "org.apache.catalina.core.DISPATCHER_REQUEST_PATH";

	private RequestDispatcher defaultRequestDispatcher;
	private FilterConfig fc;
	private ConfigurableMimeFileTypeMap mimeConfig;

	protected FilterConfig getConfig() {
		return fc;
	}
	// Pour les UT
	public void setConfig(FilterConfig fc) {
		this.fc = fc;
	}

	protected RequestDispatcher getDefaultDispatcher() {
		return defaultRequestDispatcher;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.defaultRequestDispatcher = filterConfig.getServletContext().getNamedDispatcher("default");
		this.fc = filterConfig;

		this.mimeConfig = new ConfigurableMimeFileTypeMap();
		mimeConfig.afterPropertiesSet();
	}

	@Override
	public void destroy() {
	}

	protected String getMimeContentType(String path) {
		final String contentType;
		if (mimeConfig != null) {
			contentType = mimeConfig.getContentType(path);
		}
		else {
			contentType = null;
		}
		return contentType;
	}

	/**
	 * On va regarder si on trouve le fichier dans les resources.
	 * Mais seulement si c'est dans la liste des filtres
	 *
	 * @param request
	 * @return
	 */
	protected String getPathFromRequest(HttpServletRequest request) {
		String path = (String) request.getAttribute(DISPATCHER_REQUEST_PATH_ATTR);

		//Si on n'est pas sur Tomcat
		if (path == null) {

			String url = request.getRequestURL().toString();
			String context = request.getContextPath();
			path = url.substring(url.indexOf(context)+context.length());
		}
		return path;
	}

	protected void addContentType(HttpServletResponse response, String path) {
		final String contentType = getMimeContentType(path);
		if (contentType != null) {
			response.addHeader("Content-Type", contentType);
		}
	}

}

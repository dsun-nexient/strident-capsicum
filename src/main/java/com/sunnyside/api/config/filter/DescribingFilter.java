package com.sunnyside.api.config.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/TestFilter")
public class DescribingFilter implements Filter{

	/**
	 * Default constructor.
	 */
	public DescribingFilter() {
		System.out.println("The filter is alive!");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Why you kill me brah?");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request != null) {
			System.out.println("Request: " + request.getLocale().getCountry());
		} else {
			System.out.println("Request is null");
		}
		if (response != null) {
			System.out.println("Response: " + response.getLocale().getLanguage());
		} else {
			System.out.println("Repsonse is null");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

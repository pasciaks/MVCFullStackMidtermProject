package com.skilldistillery.repeat.controllers;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false); // false means don't create a session if it doesn't exist

		if (session != null) {
			// Access session attributes
			Object userId = session.getAttribute("loggedInUser");
			// Do something with the session information

			System.out.println("Logged In user from session: " + userId);

			System.out.println("Protocol: " + request.getProtocol());
			// Get request URI
			String requestURI = httpRequest.getRequestURI();
			System.out.println("Request URI: " + requestURI);

			// Get request method
			String requestMethod = httpRequest.getMethod();
			System.out.println("Request Method: " + requestMethod);

			// Get query string
			String queryString = httpRequest.getQueryString();
			System.out.println("Query String: " + queryString);
		} else {
			// If no session, forward the request to a route of choosing
			// RequestDispatcher dispatcher = request.getRequestDispatcher("login.do");
			// dispatcher.forward(request, response);
			// return;
		}

		// Continue the filter chain
		chain.doFilter(request, response);
	}

	// Implement other methods like init and destroy as needed
}

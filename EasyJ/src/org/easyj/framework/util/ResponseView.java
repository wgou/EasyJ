package org.easyj.framework.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseView {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	public ResponseView(final HttpServletRequest request,final HttpServletResponse response){
		this.request=request;
		this.response=response;
	}
	public static void responseView(String view) throws ServletException, IOException{
		request.getRequestDispatcher(view).forward(request, response);
	}
	
}

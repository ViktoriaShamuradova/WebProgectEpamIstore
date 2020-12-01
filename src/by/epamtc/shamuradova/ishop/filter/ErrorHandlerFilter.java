package by.epamtc.shamuradova.ishop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.service.exception.AbstractApplicationException;
import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

/**
 * Класс-фильтр для обработки всех исключений
 * 
 * Filter class to handle all exceptions
 *
 * @author Victoria Shamuradova 2020
 */

public class ErrorHandlerFilter extends AbstractFilter {

	private static final String STATUS_CODE = "statusCode";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/page/error.jsp";

	public ErrorHandlerFilter() {
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {

			chain.doFilter(request, response);
		} catch (Throwable th) {

			String reqUri = request.getRequestURI();
			System.out.println(reqUri);
			logger.info("Request" + reqUri + "failed. " + th.getMessage(), th);
			handleException(th, request, response);
		}
	}

	private void handleException(Throwable th, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int statusCode = getStatusCode(th);
		req.setAttribute(STATUS_CODE, statusCode);

		th.printStackTrace();
		req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
	}

	private int getStatusCode(Throwable th) {
		if (th instanceof AbstractApplicationException) {
			return (((AbstractApplicationException) th).getCode());
		}
		if (th instanceof ServiceException) {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		} else {
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}
}

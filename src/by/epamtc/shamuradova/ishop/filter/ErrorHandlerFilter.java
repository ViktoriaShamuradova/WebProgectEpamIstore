package by.epamtc.shamuradova.ishop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.service.exception.ServiceException;

/**
 * Фильтр, который перехватывает все исключения и обрабатывает
 * 
 * @author Шамурадова Виктория
 * 
 */

public class ErrorHandlerFilter implements Filter {

	public ErrorHandlerFilter() {
	}

	@Override
	public void doFilter(ServletRequest reqSer, ServletResponse respSer, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(reqSer, respSer);
		} catch (Throwable th) {
			HttpServletRequest req = (HttpServletRequest) reqSer;
			HttpServletResponse resp = (HttpServletResponse) respSer;

			String reqUri = req.getRequestURI();
			// лоируем ошибки .error("Request"+reqUrl + "failed" + th.getMessage(), th);
			handleException(reqUri, th, req, resp);
			
		}
	}

	private void handleException(String reqUri, Throwable th, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int statusCode = getStatusCode(th);
		//resp.setStatus(statusCode);
		req.setAttribute("statusCode", statusCode);
		
		System.out.println("here!!!");
		
		th.printStackTrace();
		req.getRequestDispatcher("/WEB-INF/jsp/page/error.jsp").forward(req, resp);
	}

	private int getStatusCode(Throwable th) {
		if (th instanceof ServiceException) {
			return (((ServiceException) th).getCode());
		} else {
			return (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

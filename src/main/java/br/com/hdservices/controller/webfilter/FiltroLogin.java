package br.com.hdservices.controller.webfilter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.hdservices.controller.LoginBean;

@WebFilter(urlPatterns = "/hdservices2/*")
public class FiltroLogin implements Filter {

	@Inject
	private LoginBean loginBean;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		if (!url.contains("login")
				&& !url.contains("erro")
				&& !url.contains("home")
				&& (loginBean.getUser() == null || loginBean.getUser()
						.getMatricula() == null)) {
			resp.sendRedirect("Login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

package changemng_filters;

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

import changemng_bean.LoginBean;


/**
 * Servlet Filter implementation class MySecureFolderFilter
 */
@WebFilter("/secure/*")
public class MySecureFolderFilter implements Filter {

	@Inject
	private LoginBean loginBean;
	
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Caution! Somebody tries to reach the secure pages under secure folder!");
	
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;		
		if(loginBean == null || ! loginBean.isLoggedIn())
		{
			String page = req.getRequestURI().replace(req.getContextPath(), "");
			page = page.replace("\\.xhtml", "");
			loginBean.setAccessPage(page);
			System.out.println(page);
			res.sendRedirect(req.getContextPath()+"/Login.xhtml");
			return;
		}	
		chain.doFilter(request, response);
	}
	
	
	
	
	
}

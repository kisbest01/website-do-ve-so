package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("LoginFilter destroy!");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String loginURI = req.getContextPath() + "/LoginServlet";
		String quenpassURI = req.getContextPath() + "/QuenPassServlet";
		String newaccountURI = req.getContextPath() + "/NewAccountServlet";
		
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("loginID") != null;
	    boolean loginRequest = req.getRequestURI().equals(loginURI);
	    boolean quenRequest = req.getRequestURI().equals(quenpassURI);
	    boolean newaccountRequest = req.getRequestURI().equals(newaccountURI);

	    if (loginRequest || loggedIn || quenRequest || newaccountRequest) {
            chain.doFilter(request, response);
		} else {
			// pass the request along the filter chain
			RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("LoginFilter init!");
	}

}

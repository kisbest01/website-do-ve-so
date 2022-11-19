package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.Connection;

import context.DBContext;

/**
 * Servlet Filter implementation class JDBCFilter
 */
public class JDBCFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public JDBCFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("JDBCFilter destroy!");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;

		// 
		String servletPath = req.getServletPath();
		// Only open Connection for special request.
		// (For example: servlet, jsp, ..)
		// 
		// Avoid open Connection for the common request
		// (For example image, css, javascript,... )
		// 
		if(servletPath.contains("/QuanLyServlet") || servletPath.contains("/CapNhatServlet") || servletPath.contains("/UserServlet") || 
				servletPath.contains("/CapNhatUser") || servletPath.contains("/HomeServlet") || servletPath.contains("/DoVeSoServlet") || 
				servletPath.contains("/LichSuDoServlet")|| servletPath.contains("/DoiPassServlet") || servletPath.contains("/HoSoServlet") || 
				servletPath.contains("/LoginServlet") || servletPath.contains("/LogoutServlet") || servletPath.contains("/ThemTinhServlet") || 
				servletPath.contains("/QuenPassServlet") || servletPath.contains("/NewAccountServlet")) {
			Connection conn = null;
			try {
				// Create a Connection.
				conn = DBContext.getConnection();
				// Set auto commit = false
				conn.setAutoCommit(false);
	
				// Store connection in attribute of request.
				MyUtils.storeConnection(request, conn);
	
				// Go to next element (filter or target) in chain
				chain.doFilter(request, response);
	
				// Call commit() to commit transaction.
				conn.commit();
			} catch (Exception e) {
				DBContext.rollbackQuietly(conn);
				System.out.println(e.getMessage());
				throw new ServletException();
			} finally {
				DBContext.closeQuietly(conn);
			}
		} 
		// For common request.
		else {
			// Go to next element (filter or target) in chain.
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("JDBCFilter init!");
	}

}

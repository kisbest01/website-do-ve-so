package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8;");
		request.setCharacterEncoding("utf-8");
		
		request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8;");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);
		UserDAO ud = new UserDAO(request);
		String un = request.getParameter("username");
		String password = request.getParameter("password");
		String username = null, email = null;
		if(un.contains("@")) {
			email = un;
		} else {
			username = un;
		}
		User user = new User(0, username, null, null, email, password, null, 0, 0, null);
		if(ud.validateUser(user)) {
			User userlogin = ud.searchEmail(un);
			if(userlogin == null) {
				request.setAttribute("messenger", un + " chưa đăng kí!");
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				switch (userlogin.getSTATUS()) {
				case 0:
					request.setAttribute("messenger", "Tài khoản bị khóa vui lòng liên hệ với chúng tôi qua hoanghonhutech@gmail.com để được hỗ trợ.");
					request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
					break;

				case -1:
					request.setAttribute("messenger", "Tài khoản bị xóa vui lòng liên hệ với chúng tôi qua hoanghonhutech@gmail.com để được hỗ trợ.");
					request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
					break;
					
				default:
					try {
						if(ud.login(un, password)) {
							switch (userlogin.getROLE()) {
							case 1:
								session.setAttribute("loginID", userlogin.getId());
								response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
								break;

							case 0:
								session.setAttribute("loginID", userlogin.getId());
								response.sendRedirect("HomeServlet?page=trangchu&table=0");
								break;
								
							default:
								request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
								break;
							}
						} else {
							request.setAttribute("messenger", "Mật khẩu chưa đúng!");
							request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
						}
					} catch (NoSuchAlgorithmException | IOException | ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		} else {
			request.setAttribute("messenger", ud.messenger);
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}

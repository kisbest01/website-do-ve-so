package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tinh;
import model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import dao.TinhDAO;
import dao.UserDAO;

/**
 * Servlet implementation class DoiPassServlet
 */
public class DoiPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoiPassServlet() {
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

		HttpSession session = request.getSession();
		User userlogin = new UserDAO(request).searchUser((int) session.getAttribute("loginID"));
		if(userlogin != null) {
			switch (userlogin.getSTATUS()) {
			case 0:
				request.setAttribute("messenger", "Tài khoản bị khóa vui lòng liên hệ với chúng tôi qua hoanghonhutech@gmail.com để được hỗ trợ.");
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				break;
	
			case -1:
				request.setAttribute("messenger", "Tài khoản bị xóa vui lòng liên hệ với chúng tôi qua hoanghonhutech@gmail.com để được hỗ trợ.");
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				break;
				
			case 2:
				request.setAttribute("userlogin", userlogin);
				request.setAttribute("title", "Thay Đổi Mật Khẩu");
				String page = request.getParameter("page");
				if(page != null) {
					switch (page) {
					case "doipass":
						switch (userlogin.getROLE()) {
						case 1:
							request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
							break;
						default:
							List<Tinh> listTinh = new TinhDAO(request).getTinh();
							request.setAttribute("tinh", listTinh);
							request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
							break;
						}
						break;
		
					default:
						response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
						break;
					}	
				} else {
					response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
				}
				break;
				
			default:
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			}
		} else {
			request.setAttribute("messenger", "Tài khoản Chưa đăng kí!");
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8;");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		User userlogin = new UserDAO(request).searchUser((int) session.getAttribute("loginID"));
		if(userlogin != null) {
			switch (userlogin.getSTATUS()) {
			case 0:
				request.setAttribute("messenger", "Tài khoản bị khóa vui lòng liên hệ với chúng tôi qua hoanghonhutech@gmail.com để được hỗ trợ.");
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				break;
	
			case -1:
				request.setAttribute("messenger", "Tài khoản bị xóa vui lòng liên hệ với chúng tôi qua hoanghonhutech@gmail.com để được hỗ trợ.");
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				break;
				
			case 2:
				request.setAttribute("userlogin", userlogin);
				request.setAttribute("title", "Thay Đổi Mật Khẩu");
				UserDAO ud = new UserDAO(request);
				String cupass = request.getParameter("cupass");
				String page = request.getParameter("page");
				if(page != null) {
					switch (page) {
					case "doipass":
						User cuUser = new User(userlogin.getId(), userlogin.getUsername(), userlogin.getTen(), userlogin.getPhone(), 
								userlogin.getEmail(), cupass, userlogin.getAddress(), userlogin.getROLE(), userlogin.getSTATUS(), userlogin.getSrc());
						if(ud.validateUser(cuUser)) {
							try {
								if(!ud.isPassword(cuUser)) {
									request.setAttribute("messenger", "Mật khẩu cũ không đúng!");
									request.setAttribute("alert_succes", false);
									switch (userlogin.getROLE()) {
									case 1:
										request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
										break;
									default:
										List<Tinh> listTinh = new TinhDAO(request).getTinh();
										request.setAttribute("tinh", listTinh);
										request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
										break;
									}
								} else {
									String repass = request.getParameter("repass");
									User newUser = new User(userlogin.getId(), userlogin.getUsername(), userlogin.getTen(), userlogin.getPhone(), 
											userlogin.getEmail(), repass, userlogin.getAddress(), userlogin.getROLE(), userlogin.getSTATUS(), userlogin.getSrc());			
									if(ud.validateUser(newUser) && ud.updatePass(newUser)) {
										request.setAttribute("messenger",  "Đổi mật khẩu thành công. Vui lòng đăng nhập lại!");
										request.getServletContext().getRequestDispatcher("/LogoutServlet").forward(request, response);
									} else {
										request.setAttribute("messenger", ud.messenger);
										request.setAttribute("alert_succes", false);
										switch (userlogin.getROLE()) {
										case 1:
											request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
											break;
										default:
											List<Tinh> listTinh = new TinhDAO(request).getTinh();
											request.setAttribute("tinh", listTinh);
											request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
											break;
										}
									}
								}
							} catch (NoSuchAlgorithmException | ServletException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							request.setAttribute("messenger", ud.messenger);
							request.setAttribute("alert_succes", false);
							switch (userlogin.getROLE()) {
							case 1:
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
							default:
								List<Tinh> listTinh = new TinhDAO(request).getTinh();
								request.setAttribute("tinh", listTinh);
								request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
								break;
							}
						}
						break;
						
					default:
						response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
						break;
					}	
				} else {
					response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
				}
				break;
				
			default:
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			}
		} else {
			request.setAttribute("messenger", "Tài khoản Chưa đăng kí!");
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}



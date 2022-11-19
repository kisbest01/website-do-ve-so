package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;

import dao.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	 // reads SMTP server setting from web.xml file
    	ServletContext context = getServletContext();
    	host = context.getInitParameter("host");
    	port = context.getInitParameter("port");
    	email = context.getInitParameter("email");
    	name = context.getInitParameter("name");
    	pass = context.getInitParameter("pass");
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
				switch (userlogin.getROLE()) {
				case 1:
					request.setAttribute("title", "Quản lý người dùng");
					String table = request.getParameter("table");
					String action = request.getParameter("action");
					String page = request.getParameter("page");
					UserDAO ud = new UserDAO(request);
					List<User> listUser = ud.getUser(0, 0);
					request.setAttribute("size", listUser.size() % 10 != 0 ?  (listUser.size() / 10) + 1 : (listUser.size() / 10));
					if(page != null && page.equals("quanlyuser") && table != null && !table.isEmpty() && action != null && table.matches("^[0-9]*$")) {
						int tab = Integer.parseInt(table);
						int size = (int) request.getAttribute("size");
						if(tab >= 0 && tab < size) {
							request.setAttribute("list", ud.getUser(tab * 10, 10));
							switch (action) {
							case "update":
								request.setAttribute("messenger", "Cập nhật " + request.getParameter("username") + " thành công!");	
								request.setAttribute("alert_succes", true);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							case "insert":
								request.setAttribute("messenger", "Thêm " + request.getParameter("username") + " thành công!");	
								request.setAttribute("alert_succes", true);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							case "xem": 
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
							default:
								response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
								break;
							}
						} else {
							response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
						}
					} else {
						response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
					}
					break;
		
				default:
					request.setAttribute("messenger", "Vui lòng đăng nhập bằng Admin!");
					request.getServletContext().getRequestDispatcher("/LogoutServlet").forward(request, response);
					break;
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
				switch (userlogin.getROLE()) {
				case 1:
					String action = request.getParameter("action");
					String table = request.getParameter("table");
					String page = request.getParameter("page");
					String[] check = request.getParameterValues("check");	//danh sách email
					request.setAttribute("title", "Quản lý người dùng");
					Boolean alert_succes = true;
					String messenger = "";
					UserDAO ud = new UserDAO(request);
					List<User> listUser = ud.getUser(0, 0);
					request.setAttribute("size", listUser.size() % 10 != 0 ?  (listUser.size() / 10) + 1 : (listUser.size() / 10));
					if(page != null && page.equals("quanlyuser") && table != null && !table.isEmpty() && action != null && table.matches("^[0-9]*$")) {
						int tab = Integer.parseInt(table);
						int size = (int) request.getAttribute("size");
						if(tab >= 0 && tab < size) {
							switch (action) {
							case "search":
								String search = request.getParameter("search");
								request.setAttribute("list", ud.searchUser(search, tab * 10, 10));
								List<User> listSearch = ud.searchUser(search, 0, 0);
								if(listSearch.size() == 0) {
									request.setAttribute("messenger", "Tìm không thấy người dùng!");
									request.setAttribute("list", ud.getUser(tab * 10, 10));
									alert_succes = false;
								} else {
									request.setAttribute("messenger", "Successful");
									request.setAttribute("size", listSearch.size() % 10 != 0 ?  (listSearch.size() / 10) + 1 : (listSearch.size() / 10));
								}
								request.setAttribute("alert_succes", alert_succes);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
		
							case "filter":
								String filter = request.getParameter("filter");
								request.setAttribute("list", ud.filterUser(filter, tab * 10, 10));
								List<User> listFilter = ud.filterUser(filter, 0, 0);
								if(listFilter.size() == 0) {
									request.setAttribute("messenger", "Không có người dùng " + filter);
									request.setAttribute("list", ud.getUser(tab * 10, 10));
									alert_succes = false;
								} else {
									request.setAttribute("messenger", "Successful");
									request.setAttribute("size", listFilter.size() % 10 != 0 ?  (listFilter.size() / 10) + 1 : (listFilter.size() / 10));
								}
								request.setAttribute("alert_succes", alert_succes);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							case "xoa":
								if(check != null) {
									for (String email : check) {
										Boolean delete = ud.deleteUser(email);
										if(delete) {} else {
											alert_succes = false;
										}
										request.setAttribute("messenger", ud.messenger);
									}
									
								} else {
									request.setAttribute("messenger", "Vui lòng chọn người dùng muốn xóa!");
									alert_succes = false;
								}
								request.setAttribute("alert_succes", alert_succes);
								request.setAttribute("list", ud.getUser(tab * 10, 10));
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							case "reset":
								if(check != null) {
									for (String recipient : check) {
										String passWord = ud.randomPassword();							
						        		try {
											if(ud.resetPassword(recipient, passWord)) {
												String subject = "Mật khẩu của bạn đã được reset";
												
												String content = "<p>Xin chào ,Đây là mật khẩu mới của bạn:  <b>" + passWord + "</b></p>";
												content += "<p>Chú ý: Vì lý do an toàn, Bạn nên thay đổi mật khẩu sau khi <a href=\"http://localhost:8080/Project_DoVeSo/\">đăng nhập</a></p>";
												//String meString = "";
												EmailUtility emailUtility = new EmailUtility();
												try {
													emailUtility.sendMail(host, port, email, name, pass, recipient, subject, content);
												} catch (UnsupportedEncodingException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
													messenger += "Đây là một lỗi: " + e.getMessage() + ";";
													alert_succes = false;
												} catch (MessagingException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
													messenger += "Đây là một lỗi: " + e.getMessage() + ";";
													alert_succes = false;
												}
		
											} else {
												messenger += "Reset password của " + recipient + " thất bại!;";
												alert_succes = false;
											}
										} catch (NoSuchAlgorithmException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											messenger += "Đây là một lỗi: " + e.getMessage() + ";";
										} 
						        		messenger += ud.messenger;
									}
								} else {
									messenger += "Vui lòng chọn người dùng muốn reset password!;";
									alert_succes = false;
								}
								request.setAttribute("list", ud.getUser(tab * 10, 10));
								request.setAttribute("messenger", messenger);
								request.setAttribute("alert_succes", alert_succes);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							default:
								response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
								break;
							}
						} else {
							response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
						}
					} else {
						response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
					}
					break;
		
				default:
					request.setAttribute("messenger", "Vui lòng đăng nhập bằng Admin!");
					request.getServletContext().getRequestDispatcher("/LogoutServlet").forward(request, response);
					break;
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

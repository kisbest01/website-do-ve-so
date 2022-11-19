package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tinh;
import model.User;

import java.io.IOException;
import java.util.List;

import dao.TinhDAO;
import dao.UserDAO;

/**
 * Servlet implementation class ThemTinhServlet
 */
public class ThemTinhServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemTinhServlet() {
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
				switch (userlogin.getROLE()) {
				case 1:
					request.setAttribute("title", "Thêm Thành Phố");
					TinhDAO td = new TinhDAO(request);
					List<Tinh> listTinh = td.getTinh();
					request.setAttribute("tinh", listTinh);
					String action = request.getParameter("action");
					String page = request.getParameter("page");
					if(page != null && action != null) {
						switch (page) {
						case "themtinh":
							switch (action) {
							case "them":
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							case "capnhat":
								String tinh = request.getParameter("tinh");
								Tinh t = td.search(tinh);
								if(t != null) {
									request.setAttribute("title", "Cập nhật Thành Phố");
									request.setAttribute("t", t);
									request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								} else {
									response.sendRedirect("ThemTinhServlet?page=themtinh&action=them");
								}
								break;
		
							default:
								response.sendRedirect("ThemTinhServlet?page=themtinh&action=them");
								break;
							}						
							break;
							
						default:
							response.sendRedirect("ThemTinhServlet?page=themtinh&action=them");
							break;
						}
					} else {
						response.sendRedirect("ThemTinhServlet?page=themtinh&action=them");
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
					request.setAttribute("title", "Thêm Thành Phố");
					TinhDAO td = new TinhDAO(request);
					List<Tinh> listTinh = td.getTinh();
					request.setAttribute("tinh", listTinh);
					String mien = request.getParameter("mien");
					String tinh = request.getParameter("tinh");
					String action = request.getParameter("action");
					String page = request.getParameter("page");
					if(mien != null && !mien.isEmpty() && tinh != null && !tinh.isEmpty()) {
						if(page != null && page.equals("themtinh") && action != null) {
							switch (action) {
							case "them":
								if(td.search(tinh) != null) {
									request.setAttribute("messenger", "Tỉnh/Thành Phố đã tồn tại!");
									request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								} else {
									Tinh t = new Tinh(0, tinh, mien);
									td.insertTinh(t);
									request.setAttribute("messenger", td.messenger);
									listTinh = td.getTinh();
									request.setAttribute("tinh", listTinh);
									request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								}
								break;
			
							case "capnhat":
								request.setAttribute("title", "Cập nhật Thành Phố");
								int id = Integer.parseInt(request.getParameter("id"));
								Tinh t = new Tinh(id, tinh, mien);
								td.updateTinh(t);
								request.setAttribute("t", t);
								request.setAttribute("messenger", td.messenger);
								listTinh = td.getTinh();
								request.setAttribute("tinh", listTinh);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							default:
								response.sendRedirect("ThemTinhServlet?page=themtinh&action=them");
								break;
							}
						} else {
							response.sendRedirect("ThemTinhServlet?page=themtinh&action=them");
						}
					} else {
						request.setAttribute("messenger", "Vui lòng nhập đầy đủ thông tin!");
						request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
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

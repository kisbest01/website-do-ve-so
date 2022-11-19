package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Tinh;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;


import dao.TinhDAO;
import dao.UserDAO;


/**
 * Servlet implementation class HoSoServlet
 */
@WebServlet("/HoSoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 50, // 50MB
    maxRequestSize = 1024 * 1024 * 50) // 50MB

public class HoSoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoSoServlet() {
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
				request.setAttribute("title", "Hồ Sơ");
				String page = request.getParameter("page");
				if(page != null) {
					switch (page) {
					case "hoso":
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
				request.setAttribute("title", "Hồ Sơ");
				UserDAO ud = new UserDAO(request);
				request.setAttribute("alert_succes", true);
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String ten = request.getParameter("ten");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String fileName = "";
				String messenger = "";
				String page = request.getParameter("page");
				if(page != null) {
					switch (page) {
					case "hoso":
						User userNew = new User(-1, username, ten, phone, email, null, address, 0, 0, fileName);
						if(ud.validateUser(userNew)) {
							User user1 = ud.searchEmail(username);
							Boolean insert = true;
							if(user1 != null && user1.getId() != userlogin.getId()) {
								messenger += username + " đã tồn tại!;";
								request.setAttribute("alert_succes", false);
								insert = false;
							}
							User user2 = ud.searchEmail(email);
							if(user2 != null && user1.getId() != userlogin.getId()) {
								messenger += email + " đã tồn tại!;";
								request.setAttribute("alert_succes", false);
								insert = false;
							}
							if(insert) {
								Boolean bl = false;
								for (Part part : request.getParts()) {
									fileName = extractFileName(part);
							      if(fileName != "") {
							    	// refines the fileName in case it is an absolute path
							    	  fileName = new File(fileName).getName();
							    	  part.write(getServletContext().getRealPath("/data") + "\\" + fileName); 
							    	  bl = true;
							    	  User user = new User(userlogin.getId(), username, ten, phone, email, null, address, 0, 0, "/data/" + fileName);
							    	  ud.updateUser2(user);
							      }
							    }
								if(!bl) {
									User user = new User(userlogin.getId(), username, ten, phone, email, null, address, 0, 0, null);
									ud.updateUser2(user);
								}
								messenger += ud.messenger;
							}
						} else {
							request.setAttribute("alert_succes", false);
							messenger += ud.messenger;
						}
						request.setAttribute("messenger", messenger);
						request.setAttribute("userlogin", ud.searchUser(userlogin.getId()));
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
	   * Extracts file name from HTTP header content-disposition
	   */
	  private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	      if (s.trim().startsWith("filename")) {
	        return s.substring(s.indexOf("=") + 2, s.length() - 1);
	      }
	    }
	    return "";
	  }
}

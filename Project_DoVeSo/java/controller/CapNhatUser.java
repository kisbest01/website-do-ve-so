package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Avatar;
import model.LichSuDo;
import model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import dao.AvatarDAO;
import dao.LichSuDoDAO;
import dao.UserDAO;

/**
 * Servlet implementation class CapNhatUser
 */
public class CapNhatUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatUser() {
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
					String page = request.getParameter("page");
					String username = request.getParameter("username");
					UserDAO ud = new UserDAO(request);
					if(page != null) {
						switch (page) {
						case "capnhatuser":
							request.setAttribute("title", "Cập Nhật Người Dùng");
							String userID = request.getParameter("userid");
							if(userID != null && !userID.isEmpty()) {
								int id = Integer.parseInt(userID);
								List<User> lisUsers = ud.searchUser(username, 0, 0);
								for (User user : lisUsers) {
									if(user.getId() == id) {
										request.setAttribute("user", user);
										break;
									}
								}
								List<LichSuDo> listNew = new ArrayList<LichSuDo>();
								List<LichSuDo> listLSD = new LichSuDoDAO(request).getLichSuDo(0, 0);
								for (LichSuDo lichSuDo : listLSD) {
									if(lichSuDo.getMaND() == id) {
										listNew.add(lichSuDo);
									}
								}
								request.setAttribute("listLSD", listNew);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
							} else {
								response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
							}
							break;
		
						case "themuser":
							request.setAttribute("title", "Thêm Người Dùng");
							request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
							break;
							
						default:
							response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
							break;
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
					String ten = request.getParameter("ten");
					String phone = request.getParameter("phone");
					String email = request.getParameter("email");
					String username = request.getParameter("username");
					String address = request.getParameter("address");
					String role = request.getParameter("role");
					if(role != null && !role.isEmpty()) {
						int rol = Integer.parseInt(role);
						String page = request.getParameter("page");
						UserDAO ud = new UserDAO(request);
						if(page != null) {
							switch (page) {
							case "capnhatuser":
								String userID = request.getParameter("userid");
								String status = request.getParameter("status");
								if(userID != null && !userID.isEmpty() && status != null && !status.isEmpty()) {
									int id = Integer.parseInt(userID);
									int sta = Integer.parseInt(status);
									User user = new User(id, username, ten, phone, email, null, address, rol, sta, null);
									List<LichSuDo> listNew = new ArrayList<LichSuDo>();
									List<LichSuDo> listLSD = new LichSuDoDAO(request).getLichSuDo(0, 0);
									for (LichSuDo lichSuDo : listLSD) {
										if(lichSuDo.getMaND() == id) {
											listNew.add(lichSuDo);
										}
									}
									request.setAttribute("title", "Cập Nhật Người Dùng");
									request.setAttribute("user", user);
									if(ud.validateUser(user)) {
										User user1 = ud.searchEmail(email);
										if(user1 != null && user1.getId() != id) {
											request.setAttribute("messenger", email + " đã tồn tại!;");
											request.setAttribute("alert_succes", false);
											request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
										} else {
											if(ud.updateUser(user)) {
												response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=update&username=" + username);
											} else {
												request.setAttribute("listLSD", listNew);
												request.setAttribute("messenger", ud.messenger);
												request.setAttribute("alert_succes", false);
												request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
											}	
										}
									} else {
										request.setAttribute("listLSD", listNew);
										request.setAttribute("messenger", ud.messenger);
										request.setAttribute("alert_succes", false);
										request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
									}
								} else {
									response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=xem");
								}
								break;
			
							case "themuser":
								String pass = request.getParameter("pass");
								String messenger = "";
								request.setAttribute("title", "Thêm Người Dùng");
								User user = new User(0, username, ten, phone, email, pass, address, rol, 1, null);
								request.setAttribute("user", user);
								if(ud.validateUser(user)) {
									User user1 = ud.searchEmail(username);
									Boolean insert = true;
									if(user1 != null) {
										messenger += username + " đã tồn tại!;";
										request.setAttribute("alert_succes", false);
										insert = false;
									}
									User user2 = ud.searchEmail(email);
									if(user2 != null) {
										messenger += email + " đã tồn tại!;";
										request.setAttribute("alert_succes", false);
										insert = false;
									}
									if(insert) {
										try {
											insert = ud.insertUser(user);
										} catch (NoSuchAlgorithmException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											insert = false;
										}
										if(insert) {
											List<User> us = ud.searchUser(username, 0, 0);
											Avatar avatar = new Avatar(0, us.get(0).getId(), "/data/avatar.jpg");
											if(new AvatarDAO(request).insertAvatar(avatar)) {
												response.sendRedirect("UserServlet?page=quanlyuser&table=0&action=insert&username=" + username);
											} else {
												insert = false;
											}
										} 
										if(!insert){
											request.setAttribute("messenger", ud.messenger);
											request.setAttribute("alert_succes", false);
											request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
										}
									} else {
										request.setAttribute("messenger", messenger);
										request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
									}
								} else {
									request.setAttribute("messenger", ud.messenger);
									request.setAttribute("alert_succes", false);
									request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								}
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

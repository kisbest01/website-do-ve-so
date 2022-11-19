package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tinh;
import model.User;
import model.VeDo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import dao.TinhDAO;
import dao.UserDAO;
import dao.VeDoDAO;

/**
 * Servlet implementation class QuanLyServlet
 */
public class QuanLyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyServlet() {
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
					VeDoDAO  vd = new VeDoDAO(request);
					request.setAttribute("title", "Quản Lý Vé Dò");
					List<VeDo> list = vd.getVeDo(0, 0);
					request.setAttribute("size", list.size() % 10 != 0 ?  (list.size() / 10) + 1 : (list.size() / 10));
					String page = request.getParameter("page");
					String table = request.getParameter("table");
					String action = request.getParameter("action");
					if(page != null && page.equals("quanlyvedo") && table != null && !table.isEmpty() && action != null && table.matches("^[0-9]*$")) {
						int tab = Integer.parseInt(request.getParameter("table"));
						int size = (int) request.getAttribute("size");
						if(tab >= 0 && tab < size) {
							List<Tinh> listTinh = new TinhDAO(request).getTinh();
							request.setAttribute("tinh", listTinh);
							List<VeDo> listVeDo = vd.getVeDo(tab * 10, 10);
							request.setAttribute("list", listVeDo);
							switch (action) {
							case "capnhat":
								request.setAttribute("messenger", "Cập nhật vé dò thành công!");
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
		
							case "them":
								request.setAttribute("messenger", "Thêm vé dò thành công!");
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
		
							case "xem":
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
		
							default:
								response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
								break;
							}
		
						} else {
							response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
						}
					} else {
						response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
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
					VeDoDAO  vd = new VeDoDAO(request);
					List<Tinh> listTinh = new TinhDAO(request).getTinh();
					request.setAttribute("tinh", listTinh);
					List<VeDo> listVeDo = vd.getVeDo(0, 0);
					request.setAttribute("size", listVeDo.size() % 10 != 0 ?  (listVeDo.size() / 10) + 1 : (listVeDo.size() / 10));
					List<VeDo> list = new ArrayList<VeDo>();
					request.setAttribute("title", "Quản Lý Vé Dò");
					String page = request.getParameter("page");
					String table = request.getParameter("table");
					String action = request.getParameter("action");
					if(page != null && page.equals("quanlyvedo") && table != null && !table.isEmpty() && action != null && table.matches("^[0-9]*$")) {
						int tab = Integer.parseInt(request.getParameter("table"));
						int size = (int) request.getAttribute("size");
						if(tab >= 0 && tab < size) {
							switch (action) {
							case "xoanhieu":
								String[] listXoa = request.getParameterValues("checkxoa");
								if(listXoa != null) {
									for(String text : listXoa) {
										if(text.matches("^[0-9]*$")) {
											int id = Integer.parseInt(text);
											for (VeDo vedo : listVeDo) {
												if(vedo.getId() == id) {
													list.add(vedo);
												}
											}
										}
									}
									vd.deleteListVeDo(list);
								}
								list = vd.getVeDo(tab * 10, 10);
								request.setAttribute("list", list);
								request.setAttribute("messenger",  vd.messenger);
								List<VeDo> listNew1 = vd.getVeDo(0, 0);
								request.setAttribute("size", listNew1.size() % 10 != 0 ?  (listNew1.size() / 10) + 1 : (listNew1.size() / 10));
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							case "xoa":
							case "search":
								String ngay = request.getParameter("ngay");
								String tinh = request.getParameter("tinh");
								try {
									list = vd.searchVeDo(ngay, tinh);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if(list.size() == 0) {
									request.setAttribute("list", vd.getVeDo(tab * 10, 10));
									if(action.equals("search")) {
										request.setAttribute("messenger",  "Không tìm thấy vé dò!");
									}
								} else {
									if(ngay.isEmpty() && tinh.isEmpty()) {
										request.setAttribute("messenger",  "Thông tin dò trống!");
									} else {
										request.setAttribute("messenger",  "Tìm thấy " + list.size() + " vé dò!");
									}
									if(action.equals("xoa")) {
										vd.deleteListVeDo(list);
										list = vd.getVeDo(tab * 10, 10);
										request.setAttribute("messenger",  vd.messenger);
									}
									request.setAttribute("list", list);
									List<VeDo> listNew2 = vd.getVeDo(0, 0);
									request.setAttribute("size", listNew2.size() % 10 != 0 ?  (listNew2.size() / 10) + 1 : (listNew2.size() / 10));
									
								}
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								break;
								
							default:
								response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
								break;
							}
						} else {
							response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
						}
					} else {
						response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
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

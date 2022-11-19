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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dao.TinhDAO;
import dao.UserDAO;
import dao.VeDoDAO;

/**
 * Servlet implementation class CapNhatServlet
 */
public class CapNhatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatServlet() {
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
					VeDoDAO  vd = new VeDoDAO(request);
					TinhDAO td = new TinhDAO(request);
					request.setAttribute("listTinh", td.getTinh());
					if(page != null) {
						switch (page) {
						case "capnhatvedo":
							String ngay = request.getParameter("ngay");
							String tinh = request.getParameter("tinh");
							request.setAttribute("title", "Cập Nhật Vé Dò");
							List<VeDo> list = new ArrayList<>();
							try {
								list = vd.searchVeDo(ngay, tinh);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
							if(list.size() == 0) {
								response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=xem");
							} else {
								request.setAttribute("vd", list.get(0));
								session.setAttribute("magiai", list.get(0).getId());
								request.setAttribute("tinh", td.search(tinh));
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
							}
							break;
			
						case "themvedo":
							request.setAttribute("title", "Thêm Vé Dò");
							request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
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
					String page = request.getParameter("page");
					String mien = request.getParameter("mien");
					String giaiNgay = request.getParameter("giaingay");
					String db = request.getParameter("dacbiet");
					String nhat = request.getParameter("giainhat");
					String nhi = Arrays.toString(request.getParameterValues("giainhi")).replace("[", "").replace("]", "");
					String ba = Arrays.toString(request.getParameterValues("giaiba")).replace("[", "").replace("]", "");
					String bon = Arrays.toString(request.getParameterValues("giaibon")).replace("[", "").replace("]", "");
					String nam = Arrays.toString(request.getParameterValues("giainam")).replace("[", "").replace("]", "");
					String sau = Arrays.toString(request.getParameterValues("giaisau")).replace("[", "").replace("]", "");
					String bay = Arrays.toString(request.getParameterValues("giaibay")).replace("[", "").replace("]", "");
					String tam = request.getParameter("giaitam");
					String tinh = request.getParameter("tinh"); 
					TinhDAO td = new TinhDAO(request);
					List<Tinh> listTinh = td.getTinh();
					request.setAttribute("listTinh", listTinh);
					request.setAttribute("tinh", td.search(tinh));
					Tinh t = td.search(tinh);
					int idTinh = 0;
					if(t != null) {
						idTinh = t.getId();
					}
					int id = 0;
					if(session.getAttribute("magiai") != null) {
						id = (int) session.getAttribute("magiai");
					}
					Date ngay = new Date();
					if(!giaiNgay.isEmpty()) {
						try {
							ngay = new SimpleDateFormat("yyyy-MM-dd").parse(giaiNgay);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					String thu = new SimpleDateFormat("EEEE").format(ngay);
					VeDoDAO  vd = new VeDoDAO(request);
					VeDo veDo = new VeDo(id, ngay, idTinh, db, nhat, nhi, ba, bon, nam, sau, bay, tam, thu);
					request.setAttribute("vd", veDo);
					if(page != null) {
						switch (page) {
						case "capnhatvedo":
							request.setAttribute("title", "Cập Nhật Vé Dò");
							if(vd.validateVeDo(veDo, mien)) {
								if(vd.updateVeDo(veDo)) {
									session.removeAttribute("magiai");
									response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=capnhat");
								} else {
									request.setAttribute("messenger", vd.messenger);
									request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								}
							} else {
								request.setAttribute("messenger", vd.messenger);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
							}	
							break;
						
						case "themvedo":
							request.setAttribute("title", "Thêm Vé Dò");
							if(vd.validateVeDo(veDo, mien)) {
								VeDo vedo = vd.searchVeDo(ngay, idTinh);
								if(vedo != null) {
									request.setAttribute("vd", vedo);
									request.setAttribute("messenger", "Vé dò đã tồn tại");
									request.setAttribute("title", "Cập Nhật Vé Dò");
									session.setAttribute("magiai", vedo.getId());
									request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
								} else {
									if(vd.insertVeDo(veDo)) {
										response.sendRedirect("QuanLyServlet?page=quanlyvedo&table=0&action=them");
									} else {
										request.setAttribute("messenger", vd.messenger);
										request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
									}
								}
							} else {
								request.setAttribute("messenger", vd.messenger);
								request.getServletContext().getRequestDispatcher("/admin/quanly.jsp").forward(request, response);
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

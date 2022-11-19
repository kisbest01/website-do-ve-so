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
import java.util.Date;
import java.util.List;

import dao.TinhDAO;
import dao.UserDAO;
import dao.VeDoDAO;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
				String table = request.getParameter("table");
				String page = request.getParameter("page");
				if(page != null && page.equals("trangchu") && table != null && !table.isEmpty() && table.matches("^[0-9]*$")) {
					request.setAttribute("title", "Trang Chủ");
					VeDoDAO  vd = new VeDoDAO(request);
					List<Tinh> listTinh = new TinhDAO(request).getTinh();
					request.setAttribute("tinh", listTinh);
					List<VeDo> list = vd.getVeDo(0, 0);
					request.setAttribute("size", list.size() % 10 != 0 ?  (list.size() / 10) + 1 : (list.size() / 10));
					int tab = Integer.parseInt(table);
					int size = (int) request.getAttribute("size");
					if(tab >= 0 && tab < size) {
						List<VeDo> listVeDo = vd.getVeDo(tab * 10, 10);
						request.setAttribute("list", listVeDo);			
						request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
					} else {
						response.sendRedirect("HomeServlet?page=trangchu&table=0");
					}
				} else {
					response.sendRedirect("HomeServlet?page=trangchu&table=0");
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
				String table = request.getParameter("table");
				String action = request.getParameter("action");
				String page = request.getParameter("page");
				if(page != null && page.equals("trangchu") && action != null && table != null && !table.isEmpty() && table.matches("^[0-9]*$")) {
					request.setAttribute("title", "Trang Chủ");
					VeDoDAO  vd = new VeDoDAO(request);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					List<Tinh> listTinh = new TinhDAO(request).getTinh();
					request.setAttribute("tinh", listTinh);
					List<VeDo> listVeDo = vd.getVeDo(0, 0);
					List<VeDo> list = new ArrayList<VeDo>();
					int tab = Integer.parseInt(table);
					String messenger = null;
					Date date = null;
					switch (action) {
					case "search":
						String ngay = request.getParameter("ngay");
						String tinh = request.getParameter("tinh");
						for(VeDo vedo : listVeDo) {
							if(tinh != null && !tinh.isEmpty()) {
								int idTinh = Integer.MIN_VALUE;
								for(Tinh t :  listTinh) {
									if(t.getTentinh().equalsIgnoreCase(tinh)) {
										idTinh = t.getId();
									}
								}
								if(ngay != null && !ngay.isEmpty()) {
									try {
										date = sdf.parse(ngay);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if(vedo.getNgay().compareTo(date) == 0 && vedo.getMatinh() == idTinh) {
										list.add(vedo);
										break;
									} else {
										messenger = "Không tìm thấy vé dò ngày " + new SimpleDateFormat("dd-MM-yyyy").format(date) + " của " + tinh;
									}
								} else {
									if(vedo.getMatinh() == idTinh) {
										list.add(vedo);
									} else {
										messenger = "Không tìm thấy vé dò của " + tinh;
									}
								}
							} else {
								if(ngay != null && !ngay.isEmpty()) {
									try {
										date = sdf.parse(ngay);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if(vedo.getNgay().compareTo(date) == 0) {
										list.add(vedo);
									} else {
										messenger = "Không tìm thấy vé dò ngày " + new SimpleDateFormat("dd-MM-yyyy").format(date);
									}
								} else {
									messenger = "Thông tin tìm kiếm trống!";
								}
							}
						}
						if(list.size() == 0) {
							request.setAttribute("list", vd.getVeDo(0, 10));
							request.setAttribute("messenger",  messenger);
							request.setAttribute("size", listVeDo.size() % 10 != 0 ?  (listVeDo.size() / 10) + 1 : (listVeDo.size() / 10));
						} else {
							List<VeDo> listSearch = new ArrayList<VeDo>();
							for (int i = tab * 10; i < list.size(); i++) {
								if(i < tab * 10 + 10) {
									listSearch.add(list.get(i));
								}
							}
							request.setAttribute("list", listSearch);
							request.setAttribute("messenger",  "Tìm thấy " + list.size() + " vé dò!");
							request.setAttribute("size", listSearch.size() % 10 != 0 ?  (listSearch.size() / 10) + 1 : (listSearch.size() / 10));
						}
						request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
						break;
		
					default:
						response.sendRedirect("HomeServlet?page=trangchu&table=0");
						break;
					}
				} else {
					response.sendRedirect("HomeServlet?page=trangchu&table=0");
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

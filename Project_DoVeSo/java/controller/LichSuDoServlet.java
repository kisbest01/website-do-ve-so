package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LichSuDo;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.LichSuDoDAO;
import dao.TinhDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LichSuDoServlet
 */
public class LichSuDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichSuDoServlet() {
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
				String page = request.getParameter("page");
				String table = request.getParameter("table");
				if(page != null && page.equals("lichsudo") && table != null && !table.isEmpty() && table.matches("^[0-9]*$")) {
					LichSuDoDAO  ls = new LichSuDoDAO(request);
					request.setAttribute("tinh", new TinhDAO(request).getTinh());
					List<LichSuDo> list = ls.searchLichSuDo(userlogin.getId(), 0, 0);
					request.setAttribute("size", list.size() % 10 != 0 ?  (list.size() / 10) + 1 : (list.size() / 10));
					int tab = Integer.parseInt(table);
					int size = (int) request.getAttribute("size");
					if((tab >= 0 && tab < size) || size == 0) {
						request.setAttribute("title", "Lịch Sử Dò");
						List<LichSuDo> listLSD = ls.searchLichSuDo(userlogin.getId(),tab * 10, 10);
						request.setAttribute("list", listLSD);
						request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
					} else {
						response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
					}
				} else {
					response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
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
				String page = request.getParameter("page");
				String table = request.getParameter("table");
				String action = request.getParameter("action");
				if(page != null && page.equals("lichsudo") && action != null && table != null && !table.isEmpty() && table.matches("^[0-9]*$")) {
					request.setAttribute("title", "Lịch Sử Dò");
					List<LichSuDo> list = new ArrayList<LichSuDo>();
					LichSuDoDAO  ls = new LichSuDoDAO(request);
					request.setAttribute("tinh", new TinhDAO(request).getTinh());
					int tab = Integer.parseInt(table);
					switch (action) {
					case "search":
						List<LichSuDo> listLichSuDo = ls.searchLichSuDo(userlogin.getId(), 0, 0);
						String messenger = "";
						String tinh = request.getParameter("tinh");
						String ketqua = request.getParameter("ketqua");
						for(LichSuDo lichSuDo : listLichSuDo) {
							if(ketqua != null && !ketqua.isEmpty()) {
								if(tinh != null && !tinh.isEmpty()) {
									if(lichSuDo.getTinh().equals(tinh) && lichSuDo.getKetQua().contains(ketqua.toLowerCase())) {
										list.add(lichSuDo);
										break;
									} else {
										messenger = "Không tìm thấy lịch sử dò của Tỉnh/Thành Phố " + tinh + " với kết quả " + ketqua.toLowerCase();
									}
								} else {
									if(lichSuDo.getKetQua().contains(ketqua.toLowerCase())) {
										list.add(lichSuDo);
									} else {
										messenger = "Không tìm thấy kết quả " + ketqua.toLowerCase();
									}
								}					
							} else {
								if(tinh != null && !tinh.isEmpty()) {
									if(lichSuDo.getTinh().equals(tinh)) {
										list.add(lichSuDo);
									} else {
										messenger = "Không tìm thấy lịch sử dò của Tỉnh/Thành Phố " + tinh;
									}
								} else {
									messenger = "Thông tin tìm kiếm trống!";
								}
							}
						}
						if(list.size() == 0) {
							request.setAttribute("messenger",  messenger);
							request.setAttribute("list", ls.searchLichSuDo(userlogin.getId(),tab * 10, 10));
							List<LichSuDo> listSearch = ls.searchLichSuDo(userlogin.getId(), 0, 0);
							request.setAttribute("size", listSearch.size() % 10 != 0 ?  (listSearch.size() / 10) + 1 : (listSearch.size() / 10));
						} else {
							List<LichSuDo> listSearch = new ArrayList<LichSuDo>();
							for (int i = tab * 10; i < list.size(); i++) {
								if(i < tab * 10 + 10) {
									listSearch.add(list.get(i));
								}
							}
							request.setAttribute("list", listSearch);
							request.setAttribute("messenger",  "Tìm thấy " + list.size() + " lịch sử dò!");
							request.setAttribute("size", listSearch.size() % 10 != 0 ?  (listSearch.size() / 10) + 1 : (listSearch.size() / 10));
						}
						request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
						break;
		
					case "xoa":
						String id = request.getParameter("id");
						if(id != null && !id.isEmpty() && id.matches("^[0-9]*$")) {
							LichSuDo lsd = ls.searchLichSuDo(Integer.parseInt(request.getParameter("id")));
							if(lsd != null) {
								ls.deleteLichSuDo(lsd);
								request.setAttribute("messenger",  ls.messenger);
								request.setAttribute("list", ls.searchLichSuDo(userlogin.getId(),tab * 10, 10));
								List<LichSuDo> listSearch = ls.searchLichSuDo(userlogin.getId(), 0, 0);
								request.setAttribute("size", listSearch.size() % 10 != 0 ?  (listSearch.size() / 10) + 1 : (listSearch.size() / 10));
								request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
							} else {
								response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
							}
						} else {
							response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
						}
						break;
						
					case "xoanhieu":
						String[] check = request.getParameterValues("check");		
						if(check != null) {
							for (String idLS : check) {
								if(idLS != null && !idLS.isEmpty() && idLS.matches("^[0-9]*$")) {
									LichSuDo lichSuDo = ls.searchLichSuDo(Integer.parseInt(idLS));
									if(lichSuDo != null) {
										list.add(lichSuDo);
									}
								} else {
									continue;
								}
							}
							if(list.size() != 0) {
								ls.deleteLichSuDo(list);
								request.setAttribute("messenger",  ls.messenger);
								request.setAttribute("list", ls.searchLichSuDo(userlogin.getId(),tab * 10, 10));
								List<LichSuDo> listSearch = ls.searchLichSuDo(userlogin.getId(), 0, 0);
								request.setAttribute("size", listSearch.size() % 10 != 0 ?  (listSearch.size() / 10) + 1 : (listSearch.size() / 10));
							} else {
								response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
							}
						} else {
							response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
						}
						break;
					default:
						response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
						break;
					}
				} else {
					response.sendRedirect("LichSuDoServlet?page=lichsudo&table=0");
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

package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LichSuDo;
import model.Tinh;
import model.User;
import model.VeDo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.LichSuDoDAO;
import dao.TinhDAO;
import dao.UserDAO;
import dao.VeDoDAO;

/**
 * Servlet implementation class DoVeSoServlet
 */
public class DoVeSoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoVeSoServlet() {
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
				if(page != null && page.equals("doveso")) {
					request.setAttribute("title", "Dò Vé Số");
					TinhDAO t = new TinhDAO(request);
					request.setAttribute("tinh", t.getTinh());
					request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
				} else {
					response.sendRedirect("DoVeSoServlet?page=doveso");
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
				String action = request.getParameter("action");
				String page = request.getParameter("page");
				if(page != null && page.equals("doveso") && action != null && action.equals("dove")) {
					request.setAttribute("title", "Dò Vé Số");
					VeDoDAO  vd = new VeDoDAO(request);
					TinhDAO t = new TinhDAO(request);
					LichSuDoDAO l = new LichSuDoDAO(request);
					String messenger = "";
					String ngay = request.getParameter("giaingay");
					String mave = request.getParameter("mave");
					Tinh tinh = t.search(request.getParameter("tinh"));
					if(ngay != null && !ngay.isEmpty() && tinh != null && mave != null && !mave.isEmpty()) {
						if (!mave.matches("[0-9]{6}")) {
							messenger += "Mã vé dò không hợp lệ!;";
						} else {
							Date date = null;
							try {
								date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							VeDo veDo = vd.searchVeDo(date, tinh.getId());
							if(veDo == null) {
								messenger += "Rất tiếc vé dò chưa được nhập. Vui lòng quay lại sau!;";
							} else {
								Boolean bl = false;
								int ketqua = -1;
								if(mave.equals(veDo.getGiaiDB())) {
									messenger += "Chúc mừng bạn đã trúng giải đặc biệt!;";
									bl = true;
									ketqua = 0;
								} 
								if(mave.endsWith(veDo.getGiaiNhat())) {
									messenger += "Chúc mừng bạn đã trúng giải nhất!;";
									bl = true;
									ketqua = 1;
								} 
								if(vd.isDoVeSo(mave, veDo.getGiaiNhi().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải nhì!;";
									bl = true;
									ketqua = 2;
								}
								if(vd.isDoVeSo(mave, veDo.getGiaiBa().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải ba!;";
									bl = true;
									ketqua = 3;
								}
								if(vd.isDoVeSo(mave, veDo.getGiaiBon().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải bốn!;";
									bl = true;
									ketqua = 4;
								}
								if(vd.isDoVeSo(mave, veDo.getGiaiNam().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải năm!;";
									bl = true;
									ketqua = 5;
								}
								if(vd.isDoVeSo(mave, veDo.getGiaiSau().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải sáu!;";
									bl = true;
									ketqua = 6;
								}
								if(vd.isDoVeSo(mave, veDo.getGiaiBay().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải bảy!;";
									bl = true;
									ketqua = 7;
								}
								if(vd.isDoVeSo(mave, veDo.getGiaiTam().split(", "))) {
									messenger += "Chúc mừng bạn đã trúng giải tám!;";
									bl = true;
									ketqua = 8;
								} 
								
								if(!bl) {
									messenger += "Rất tiếc bạn chưa trúng giải nào!;";
								}
								LichSuDo lsd = new LichSuDo(0, userlogin.getId(), new Date(), mave, veDo.getId(), ketqua);
								if(l.isLichSuDo(lsd)) {} else {
									l.insertLichSuDo(lsd);
								}
							}
						}
					} else {
						messenger += "Vui lòng nhập đầy đủ thông tin dò!;";
					}
					request.setAttribute("tinh", t.getTinh());
					request.setAttribute("messenger", messenger);
					request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
				} else {
					response.sendRedirect("DoVeSoServlet?page=doveso");
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

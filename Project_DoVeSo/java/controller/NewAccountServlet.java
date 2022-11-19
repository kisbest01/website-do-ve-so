package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import dao.UserDAO;

/**
 * Servlet implementation class NewAccountServlet
 */
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;         
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccountServlet() {
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
		request.getServletContext().getRequestDispatcher("/newaccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8;");
		request.setCharacterEncoding("utf-8");
		
		String messenger = "";
		UserDAO ud = new UserDAO(request);
		String ten = request.getParameter("ten");
		String phone = request.getParameter("phone");
		String recipient = request.getParameter("email");
		String username = request.getParameter("username");
		String password = ud.randomPassword();
		String address = request.getParameter("address");
		User user = new User(0, username, ten, phone, recipient, password, address, 0, 1, null);
		Boolean insert = true;
		if(ud.validateUser(user)) { 
			if(ud.searchEmail(username) != null) {
				messenger += "Username đã tồn tại!;";
				insert = false;
			}	
			if(ud.searchEmail(recipient) != null) {
				messenger += "Email đã đăng ký!;";
				insert = false;
			}	
			if(insert) {
				try {
					if(ud.insertUser(user)) {
						String subject = "Đăng ký tài khoản thành công";
	        			
	        			String content = "<p>Xin chào, Đây là mật khẩu của bạn:  <b>" + password + "</b></p>";
	        			content += "<p>Chú ý: Vì lý do an toàn, Bạn nên thay đổi mật khẩu sau khi <a href=\"http://localhost:8080/Project_DoVeSo/\">đăng nhập</a></p>";
	        			EmailUtility emailUtility = new EmailUtility();
	        			try {
							emailUtility.sendMail(host, port, email, name, pass, recipient, subject, content);
							messenger = "Đăng ký tài khoản thành công. Vui lòng kiểm tra e-mail của bạn.";
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							messenger = "Đây là một lỗi: " + e.getMessage();
							insert = false;
							ud.delete(user);
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							messenger = "Đây là một lỗi: " + e.getMessage();
							insert = false;
							ud.delete(user);
						}
	
					} else {
						messenger = ud.messenger;
						insert = false;
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					messenger = "Đây là một lỗi: " + e.getMessage() + ";";
					insert = false;
				}
			}
		} else {
			messenger = ud.messenger;
			insert = false;
		}
		request.setAttribute("messenger", messenger);
		if(insert) {
			request.setAttribute("alert_succes", insert);
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			request.setAttribute("alert_succes", insert);
			request.getServletContext().getRequestDispatcher("/newaccount.jsp").forward(request, response);
		}
	}

}

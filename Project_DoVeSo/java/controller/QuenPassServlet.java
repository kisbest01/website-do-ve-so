package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;
import dao.UserDAO;

/**
 * Servlet implementation class QuenPassServlet
 */
public class QuenPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuenPassServlet() {
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
		request.getServletContext().getRequestDispatcher("/quenpass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8;");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);
		String action = request.getParameter("action");
		String messenger = "";
		String recipient = "";
		Boolean bl = false;
		UserDAO ud = new UserDAO(request);
		if(action == null) {
			request.getServletContext().getRequestDispatcher("/quenpass.jsp").forward(request, response);
			return;
		}
		switch (action) {
		case "layma":
			recipient = request.getParameter("email");
			if(recipient != null && !recipient.isEmpty()) {
				if(ud.searchEmail(recipient) != null) {
					String key = ud.randomPassword();
	    			String subject = "M?? x??c th???c c???a b???n";
	    			
	    			String content = "<p>Xin ch??o , ????y l?? m?? x??c th???c c???a b???n:  <b>" + key + "</b></p>";
	    			EmailUtility emailUtility = new EmailUtility();
	    			try {
						emailUtility.sendMail(host, port, email, name, pass, recipient, subject, content);
						messenger = "Vui l??ng ki???m tra Email c???a b???n!";
						bl = true;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						messenger = "????y l?? m???t l???i: " + e.getMessage() + ";";
						bl = false;
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						messenger = "????y l?? m???t l???i: " + e.getMessage() + ";";
						bl = false;
					}
	        		request.setAttribute("messenger", messenger);
	        		session.setAttribute("key", key);
				} else {
					request.setAttribute("messenger", recipient + " ch??a ????ng k??!");
				}
			} else {
				request.setAttribute("messenger", "Vui l??ng ??i???n ?????a ch??? email!");
			}
			request.setAttribute("alert_succes", bl);
			request.getServletContext().getRequestDispatcher("/quenpass.jsp").forward(request, response);
			break;

		case "quenpass":
			recipient = request.getParameter("email");
			String maxacthuc = request.getParameter("maxacthuc");
			String key = (String) session.getAttribute("key");	
			if(!recipient.isEmpty() && maxacthuc != null && !maxacthuc.isEmpty()) {
				if(key == null) {
					request.setAttribute("messenger", "Vui l??ng l???y m?? x??c th???c m???i!");
					request.getServletContext().getRequestDispatcher("/quenpass.jsp").forward(request, response);
					return;
				}
				if(ud.searchEmail(recipient) != null) {
					if(maxacthuc.equals(key)) {
						String passWord = ud.randomPassword();
		        		try {
							Boolean reset = ud.resetPassword(recipient, passWord);
							if(reset) {
			        			String subject = "M???t kh???u c???a b???n ???? ???????c reset";
			        			
			        			String content = "<p>Xin ch??o , ????y l?? m???t kh???u m???i c???a b???n:  <b>" + passWord + "</b></p>";
			        			content += "<p>Ch?? ??: V?? l?? do an to??n, B???n n??n thay ?????i m???t kh???u sau khi <a href=\"http://localhost:8080/Project_DoVeSo/\">????ng nh???p</a></p>";
			        			EmailUtility emailUtility = new EmailUtility();
			        			try {
									emailUtility.sendMail(host, port, email, name, pass, recipient, subject, content);
									messenger = "???? reset m???t kh???u th??nh c??ng. Vui l??ng ki???m tra Email c???a b???n!";
									bl = true;
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									messenger = "????y l?? m???t l???i: " + e.getMessage() + ";";
									bl = false;
								} catch (MessagingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									messenger = "????y l?? m???t l???i: " + e.getMessage() + ";";
									bl = false;
								}
			        		}
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							messenger = "????y l?? m???t l???i: " + e.getMessage() + ";";
							bl = false;
						}
		        		request.setAttribute("messenger", messenger);
					} else {
						request.setAttribute("messenger", "M?? x??c th???c sai!");
						bl = false;
					}
				} else {
					request.setAttribute("messenger", recipient + " ch??a ????ng k??!");
					bl = false;
				}	
			} else {
				request.setAttribute("messenger", "Vui l??ng nh???p ?????y ????? th??ng tin!");
				bl= false;
			}
			if(bl) {
				request.setAttribute("alert_succes", bl);
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				request.setAttribute("alert_succes", bl);
				request.getServletContext().getRequestDispatcher("/quenpass.jsp").forward(request, response);
			}
		}
	}

}

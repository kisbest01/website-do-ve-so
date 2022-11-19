/**
 * 
 */
package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;

import filter.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import model.User;

/**
 * @author KISS
 *
 */
public class UserDAO {
	public String messenger;
	HttpServletRequest request;
	
	public UserDAO(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		messenger = "";
	}
	/**
	 * Lấy danh sách User
	 * @param start
	 * @param total
	 * @return
	 */
	public List<User> getUser(int start, int total) {
		String fetch = "";
		if(total > 0) {
			fetch = "FETCH NEXT " + total + " ROWS ONLY";
		}
		String sql = "select N.MAND, N.USERNAME, N.TENND, N.SODIENTHOAI, N.EMAIL, N.MATKHAU, N.DIACHI, N.ROLE, N.TRANGTHAI, A.SRC "
				+ "from NGUOIDUNG N "
				+ "LEFT JOIN AVATAR A "
				+ "ON N.MAND = A.MAND "
				+ "ORDER BY TRANGTHAI DESC, ROLE DESC OFFSET " + start + " ROWS " + fetch + ";";
		List<User> list = new ArrayList<User>();
		try {
			 Connection conn = MyUtils.getStoredConnection(request);
			// crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	    	// show data
	        while (rs.next()) {
	             User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
	            list.add(us);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return list;
	}
	
	/**
	 * Search User theo username, Phone
	 * @param text
	 * @param start
	 * @param total
	 * @return
	 */
	public List<User> searchUser(String text, int start, int total) {
		String fetch = "";
		if(total > 0) {
			fetch = "FETCH NEXT " + total + " ROWS ONLY";
		}
		String sql = "select N.MAND, N.USERNAME, N.TENND, N.SODIENTHOAI, N.EMAIL, N.MATKHAU, N.DIACHI, N.ROLE, N.TRANGTHAI, A.SRC "
				+ "from NGUOIDUNG N "
				+ "LEFT JOIN AVATAR A "
				+ "ON N.MAND = A.MAND "
				+ "WHERE USERNAME LIKE '%" + text + "%' OR SODIENTHOAI LIKE '%" + text + "%' ORDER BY TRANGTHAI DESC, ROLE DESC OFFSET " + start + " ROWS " + fetch + ";";
		List<User> list = new ArrayList<User>();
		try {
			 Connection conn = MyUtils.getStoredConnection(request);
			// crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	    	// show data
	        while (rs.next()) {
	        	User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
	            list.add(us);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return list;
	}
	
	/**
	 * Search User theo email, username
	 * @param text
	 * @return
	 */
	public User searchEmail(String text) {
		String sql = "select N.MAND, N.USERNAME, N.TENND, N.SODIENTHOAI, N.EMAIL, N.MATKHAU, N.DIACHI, N.ROLE, N.TRANGTHAI, A.SRC "
				+ "from NGUOIDUNG N "
				+ "LEFT JOIN AVATAR A "
				+ "ON N.MAND = A.MAND "
				+ "WHERE EMAIL = '" + text + "' OR USERNAME = '" + text + "';";
		User user = null;
		try {
			 Connection conn = MyUtils.getStoredConnection(request);
			// crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	    	// show data
	        while (rs.next()) {
	        	user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return user;
	}
	
	/**
	 * Search User theo ID
	 * @param id
	 * @return
	 */
	public User searchUser(int id) {
		String sql = "select N.MAND, N.USERNAME, N.TENND, N.SODIENTHOAI, N.EMAIL, N.MATKHAU, N.DIACHI, N.ROLE, N.TRANGTHAI, A.SRC "
				+ "from NGUOIDUNG N "
				+ "LEFT JOIN AVATAR A "
				+ "ON N.MAND = A.MAND "
				+ "WHERE N.MAND = " + id + ";";
		User user = null;
		try {
			 Connection conn = MyUtils.getStoredConnection(request);
			// crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	    	// show data
	        while (rs.next()) {
	        	user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return user;
	}
	
	/**
	 * Kiểm tra password có đúng không
	 * @param userlogin
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public boolean isPassword(User userlogin) throws NoSuchAlgorithmException {
		String sql = "select N.MAND, N.USERNAME, N.TENND, N.SODIENTHOAI, N.EMAIL, N.MATKHAU, N.DIACHI, N.ROLE, N.TRANGTHAI, A.SRC "
				+ "from NGUOIDUNG N "
				+ "LEFT JOIN AVATAR A "
				+ "ON N.MAND = A.MAND "
				+ "WHERE N.MAND = " + userlogin.getId() + " "
					+ "AND N.MATKHAU = '" + hashPassword(userlogin.getPassword()) + "';";
		Boolean bl = false;
		try {
			 Connection conn = MyUtils.getStoredConnection(request);
			// crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	    	// show data
	        while (rs.next()) {
	        	bl = true;
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return bl;
	}
	
	/**
	 * Filter User theo tiêu chí
	 * @param text
	 * @param start
	 * @param total
	 * @return
	 */
	public List<User> filterUser(String text, int start, int total) {
		String fetch = "";
		if(total > 0) {
			fetch = "FETCH NEXT " + total + " ROWS ONLY";
		}
		String sql = "select N.MAND, N.USERNAME, N.TENND, N.SODIENTHOAI, N.EMAIL, N.MATKHAU, N.DIACHI, N.ROLE, N.TRANGTHAI, A.SRC "
				+ "from NGUOIDUNG N "
				+ "LEFT JOIN AVATAR A "
				+ "ON N.MAND = A.MAND ";
		
			switch (text) {
			case "Admin":
				sql += " WHERE ROLE = 1";
				break;
	
			case "User":
				sql += " WHERE ROLE = 0";
				break;
	
			case "Trực tuyến":
				sql += " WHERE TRANGTHAI = 2";
				break;
	
			case "Đã khóa":
				sql += " WHERE TRANGTHAI = 0";
				break;
	
			case "Đã xóa":
				sql += " WHERE TRANGTHAI = -1";
				break;
	
			default:
				break;
			}
		
		sql += " ORDER BY TRANGTHAI DESC, ROLE DESC OFFSET " + start + " ROWS " + fetch + ";";
		List<User> list = new ArrayList<User>();
		try {
			Connection conn = MyUtils.getStoredConnection(request);
			// crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	    	// show data
	        while (rs.next()) {
	        	User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
	            list.add(us);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return list;
	}
	
	/**
	 * Xóa 1 User theo email
	 * @param email
	 * @return
	 */
	public Boolean deleteUser(String email) {
		User user = searchEmail(email);	
		if(user.getRole().equals("Admin")) {
			messenger += email + " là admin không thể xóa!;";
			return false;
		}
		if(user.getStatus().equals("Đã xóa")) {
			messenger += email + " đã được xóa!;";
			return false;
		}
		String sql = "";
		switch (user.getSTATUS()) {
		case 2:
			messenger += email + " đang hoạt động!;";
			sql = "UPDATE NGUOIDUNG SET TRANGTHAI = 0 where EMAIL = '" + email + "'; ";
			break;

		default:
			sql = "UPDATE NGUOIDUNG SET TRANGTHAI = -1 where EMAIL = '" + email + "'; ";
			break;
		}
		Boolean del = false;
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Xóa/khóa " + email + " thất bại!;";
	        } else {
	        	messenger += "Xóa/khóa " + email + " thành công!;";
				del = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return del;
	}	
	
	/**
	 * Reset password for User
	 * @param email
	 * @param passWord
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Boolean resetPassword(String email, String passWord) throws NoSuchAlgorithmException {
		Boolean reset = false;
		String sql = "UPDATE NGUOIDUNG SET MATKHAU = '" + hashPassword(passWord) + "' where EMAIL = '" + email + "'; ";
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Reset password của " + email + " thất bại!;";
	        } else {
	        	messenger += "Reset password của " + email + " thành công!;";
				reset = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return reset;
	}
	
	/**
	 * Cập nhật user
	 * @param user
	 * @return
	 */
	public Boolean updateUser(User user) {
		String sql = "";
		List<User> listUser = searchUser(user.getUsername(), 0, 0);
		for (User us : listUser) {
			if(us.getUsername().equals(user.getUsername()) && us.getId() == user.getId()) {
				if(us.getROLE() == 1 && user.getROLE() == 0) {
					messenger += "Không thể cập nhật Admin là User;";
					return false;
				}
				sql += "UPDATE NGUOIDUNG SET "
						+ "TENND = N'" + user.getTen() + "',"
						+ "SODIENTHOAI = '" + user.getPhone() + "',"
						+ "EMAIL = '" + user.getEmail() + "',"
						+ "DIACHI = N'" + user.getAddress() + "',"
						+ "ROLE = " + user.getROLE() + ","
						+ "TRANGTHAI = " + user.getSTATUS()
						+ " where MAND = " + user.getId() + "; ";
				break;
			}
		}
		
		Boolean update = false;
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Cập nhật " + user.getUsername() + " thất bại!;";
	        } else {
	        	messenger += "Cập nhật " + user.getUsername() + " thành công!;";
				update = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return update;
	}
	
	/**
	 * Cập nhật user
	 * @param user
	 * @return
	 */
	public Boolean updateUser2(User user) {
		String sql = "Begin transaction ";
			sql += "UPDATE NGUOIDUNG SET "
				+ "TENND = N'" + user.getTen() + "',"
				+ "SODIENTHOAI = '" + user.getPhone() + "',"
				+ "EMAIL = '" + user.getEmail() + "',"
				+ "DIACHI = N'" + user.getAddress() + "' "
				+ "where MAND = " + user.getId() + "; ";
		if(user.getSrc() != null) {
			sql += "UPDATE AVATAR SET SRC = '" + user.getSrc() + "' where MAND = " + user.getId() + ";";
		}
		sql += "commit transaction";
		Boolean update = false;
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Cập nhật thất bại!;";
	        } else {
	        	messenger += "Cập nhật thành công!;";
				update = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return update;
	}
	
	/**
	 * Đổi pass
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Boolean updatePass(User user) throws NoSuchAlgorithmException {
		String sql = "UPDATE NGUOIDUNG SET MATKHAU = '" + hashPassword(user.getPassword()) + "' where MAND = " + user.getId() + ";";
		Boolean update = false;
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Cập nhật " + user.getUsername() + " thất bại!;";
	        } else {
	        	messenger += "Cập nhật " + user.getUsername() + " thành công!;";
				update = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return update;
	}
	
	/**
	 * Thêm user
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Boolean insertUser(User user) throws NoSuchAlgorithmException {
		String sql = "INSERT INTO NGUOIDUNG(USERNAME, TENND, SODIENTHOAI, EMAIL, MATKHAU, DIACHI, ROLE, TRANGTHAI) VALUES ("
				+ "'" + user.getUsername() + "',"
				+ "N'" + user.getTen() + "',"
				+ "'" + user.getPhone() + "',"
				+ "'" + user.getEmail() + "',"
				+ "'" + hashPassword(user.getPassword()) + "',"
				+ "N'" + user.getAddress() + "',"
				+ user.getROLE() + "," + 1 + ");";
		Boolean insert = false;
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Thêm " + user.getUsername() + " thất bại!;";
	        } else {
	        	messenger += "Thêm " + user.getUsername() + " thành công!;";
				insert = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return insert;
	}
	
	/**
	 * Xóa User
	 * @param user
	 * @return
	 */
	public Boolean delete(User user) {
		Boolean dl = false;
		String sql = "DELETE FROM NGUOIDUNG where EMAIL = '" + user.getEmail() + "' AND USERNAME = '" + user.getUsername() + "';";
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Delete " + user.getEmail() + " thất bại!;";
	        } else {
	        	messenger += "Delete " + user.getEmail() + " thành công!;";
				dl = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return dl;
	}
	
	/**
	 * Login
	 * @param text
	 * @param passWord
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Boolean login(String text, String passWord) throws NoSuchAlgorithmException {
		Boolean bl = false;
		String sql = "UPDATE NGUOIDUNG SET TRANGTHAI = 2 where (EMAIL = '" + text + "' OR USERNAME = '" + text + "') AND MATKHAU = '" + hashPassword(passWord) + "';";
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Login " + text + " thất bại!;";
	        } else {
	        	messenger += "Login " + text + " thành công!;";
				bl = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return bl;
	}
	
	/**
	 * Logout
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Boolean logout(User user) throws NoSuchAlgorithmException {
		Boolean bl = false;
		String sql = "UPDATE NGUOIDUNG SET TRANGTHAI = 1 where MAND = " + user.getId() + ";";
		try {
			
			// connect to database
			 Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Logout " + user.getEmail() + " thất bại!;";
	        } else {
	        	messenger += "Logout " + user.getEmail() + " thành công!;";
				bl = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
	
		return bl;
	}
	
	public String randomPassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!#$%&";
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&])(?=\\S+$).{8,}$";
		String passWord = RandomStringUtils.random(8, characters);
		if(passWord.matches(regex)) {
			return passWord;
		} 
		
		return randomPassword();
	}
	
	public Boolean validateUser(User user) {
		Boolean bl = true;
		if(user.getEmail() != null) {
			String regex_email = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			if(user.getEmail().isEmpty()) {
				messenger += "Chưa nhập email!;";
				bl = false;
			} else if (!user.getEmail().matches(regex_email)) {
				messenger += "Email không hợp lệ!;";
				bl = false;
			}
		}
		
		if(user.getTen() != null) {
			if(user.getTen().isEmpty()) {
				messenger += "Chưa nhập tên!;";
				bl = false;
			} else if (user.getTen().length() > 30) {
				messenger += "Tên chứa quá 30 kí tự!;";
				bl = false;
			}
		}
		
		if(user.getId() == 0 && user.getUsername() != null || user.getId() == -1) {
			if(user.getUsername().isEmpty()) {
				messenger += "Chưa nhập username!;";
				bl = false;
			} else if (user.getUsername().length() > 30) {
				messenger += "Username chứa quá 30 kí tự!;";
				bl = false;
			} else if (!user.getUsername().matches("^[a-zA-Z0-9]{3,30}$")) {
				messenger += "Username không hợp lệ!;";
				bl = false;
			}
		}
		
		if(user.getId() == 0 || user.getPassword() != null) {
			String regex_pass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&])(?=\\S+$).{8,100}$";
			if(user.getPassword().isEmpty()) {
				messenger += "Chưa nhập password!;";
				bl = false;
			} else if (!user.getPassword().matches(regex_pass)) {
				messenger += "Password không hợp lệ!;";
				bl = false;
			}
		}
		if(user.getAddress() != null || user.getId() == -1) {
			if(user.getAddress().isEmpty()) {
				messenger += "Chưa nhập địa chỉ!;";
				bl = false;
			} else if (user.getAddress().length() > 300) {
				messenger += "Địa chỉ chứa quá 300 kí tự!;";
				bl = false;
			}
		}
		
		if(user.getPhone() != null) {
			String regex_phone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
			if(user.getPhone().isEmpty()) {
				messenger += "Chưa nhập số điện thoại!;";
				bl = false;
			} else if(!user.getPhone().matches(regex_phone)) {
				messenger += "Số điện thoại không hợp lệ!;";
				bl = false;
			}
		}

		return bl;
	}
	
	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();

		return myChecksum;
	}
}

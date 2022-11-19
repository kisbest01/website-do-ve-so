/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.Statement;

import filter.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import model.Avatar;

/**
 * @author KISS
 *
 */
public class AvatarDAO {
	public String messenger;
	HttpServletRequest request;
	
	public AvatarDAO(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		messenger = "";
	}
	
	/**
	 * Thêm avatar
	 * @param avatar
	 * @return
	 */
	public Boolean insertAvatar(Avatar avatar) {
		
		String sql = "INSERT INTO Avatar(MAND, SRC) VALUES ("
				+ avatar.getMaND() + ","
				+ "'" + avatar.getSrc() + "');";
		Boolean insert = false;
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Thêm avatar thất bại!;";
	        } else {
	        	messenger += "Thêm avatar thành công!;";
				insert = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		return insert;
	}
}

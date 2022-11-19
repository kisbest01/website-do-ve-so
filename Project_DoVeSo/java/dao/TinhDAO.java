/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import filter.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import model.Tinh;

/**
 * @author KISS
 *
 */
public class TinhDAO {
	
	public String messenger;
	HttpServletRequest request;
	
	public TinhDAO(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		messenger = "";
	}
	
	/**
	 * Lấy danh sách tỉnh
	 * @return
	 */
	public List<Tinh> getTinh() { 
		List<Tinh> list = new ArrayList<Tinh>();
		String sql = "select * from TINH;";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            Tinh  t = new Tinh(rs.getInt(1), rs.getString(2), rs.getString(3));
	            list.add(t);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return list;
	}
	
	/**
	 * Tìm tỉnh theo ngày
	 * @param ngay
	 * @return
	 * @throws ParseException
	 */
	public List<Tinh> searchTinh(String ngay) throws ParseException { 
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
		List<Tinh> list = new ArrayList<Tinh>();
		String sql = "select T.MATINH, T.TENTINH, T.MIEN "
				+ "from TINH T"
				+ "join CHITIET_GIAI C "
				+ "ON T.MATINH = C.MATINH "
				+ "WHERE GIAINGAY = '" + new SimpleDateFormat("yyyyMMdd").format(date) + "';";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            Tinh  t = new Tinh(rs.getInt(1), rs.getString(2), rs.getString(3));
	            list.add(t);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}

		return list;
	}
	
	/**
	 * Tìm tỉnh theo tên tỉnh
	 * @param tinh
	 * @return
	 */
	public Tinh search(String tinh) { 
		Tinh t = null;
		String sql = "select * from TINH "
				+ "WHERE TENTINH = N'" + tinh + "';";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            t = new Tinh(rs.getInt(1), rs.getString(2), rs.getString(3));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return t;
	}
	
	/**
	 * Thêm tỉnh
	 * @param tinh
	 * @return
	 */
	public Boolean insertTinh(Tinh tinh) {
		String sql = "INSERT INTO TINH(TENTINH, MIEN) VALUES ("
				+ "N'" + tinh.getTentinh() + "',"
				+ "N'" + tinh.getMien() + "');";
		Boolean insert = false;
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Thêm Tỉnh/Thành Phố " + tinh.getTentinh() + " thất bại!;";
	        } else {
	        	messenger += "Thêm Tỉnh/Thành Phố " + tinh.getTentinh() + " thành công!;";
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
	 * /Cập nhật tỉnh
	 * @param tinh
	 * @return
	 */
	public Boolean updateTinh(Tinh tinh) {		
		String sql = "UPDATE TINH SET "
				+ "TENTINH = N'" + tinh.getTentinh() + "',"
				+ "MIEN = N'" + tinh.getMien() + "' "
				+ "WHERE MATINH = " + tinh.getId() + ";";
		Boolean insert = false;
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Cập nhật Tỉnh/Thành Phố " + tinh.getTentinh() + " thất bại!;";
	        } else {
	        	messenger += "Cập nhật Tỉnh/Thành Phố " + tinh.getTentinh() + " thành công!;";
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

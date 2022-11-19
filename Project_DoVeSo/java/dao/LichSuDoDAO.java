/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import filter.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import model.LichSuDo;

/**
 * @author KISS
 *
 */
public class LichSuDoDAO {
	
	public String messenger;
	HttpServletRequest request;
	
	public LichSuDoDAO(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		messenger = "";
	}

	/**
	 * Lấy danh sách Lịch Sử Dò
	 * @param start
	 * @param total
	 * @return
	 */
	public List<LichSuDo> getLichSuDo(int start, int total) { 
		List<LichSuDo> list = new ArrayList<LichSuDo>();
		String fetch = "";
		if(total > 0) {
			fetch = "FETCH NEXT " + total + " ROWS ONLY";
		}
		String sql = "select * from LICHSU_DO order by NGAYDO DESC, ID DESC OFFSET " + start + " ROWS " + fetch + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            LichSuDo  t = new LichSuDo(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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
	 * Tìm Lịch Sử Dò theo ID người dùng
	 * @param id
	 * @param start
	 * @param total
	 * @return
	 */
	public List<LichSuDo> searchLichSuDo(int id, int start, int total) { 
		List<LichSuDo> list = new ArrayList<LichSuDo>();
		String fetch = "";
		if(total > 0) {
			fetch = "FETCH NEXT " + total + " ROWS ONLY";
		}
		String sql = "select L.ID, L.MAND, L.NGAYDO, L.MAVE, L.MAGIAI, L.KETQUA, C.GIAINGAY, T.TENTINH "
				+ "from LICHSU_DO L "
				+ "JOIN CHITIET_GIAI C "
				+ "ON L.MAGIAI = C.MAGIAI "
				+ "JOIN TINH T "
				+ "ON T.MATINH = C.MATINH "
				+ "WHERE L.MAND = " + id + " "
				+ "order by L.NGAYDO DESC, L.ID DESC OFFSET " + start + " ROWS " + fetch + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            LichSuDo  t = new LichSuDo(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getString(8));
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
	 * Tìm Lịch Sử Dò theo ID
	 * @param id
	 * @return
	 */
	public LichSuDo searchLichSuDo(int id) { 
		LichSuDo lsd = null;
		String sql = "select * from LICHSU_DO where ID = " + id + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            lsd = new LichSuDo(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return lsd;
	}
	
	/**
	 * Tìm Lịch Sử Dò theo ID vé dò
	 * @param id
	 * @return
	 */
	public List<LichSuDo> search(int magiai) { 
		List<LichSuDo> list = new ArrayList<LichSuDo>();
		String sql = "select * from LICHSU_DO WHERE MAGIAI = " + magiai + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	            LichSuDo  t = new LichSuDo(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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
	 * Xóa 1 lịch sử dò
	 * @param lsd
	 * @return
	 */
	public boolean deleteLichSuDo(LichSuDo lsd) {
		Boolean del = false;
		String sql = "delete from LICHSU_DO where ID = " + lsd.getId() + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Xóa ngày " + new SimpleDateFormat("dd-MM-yyyy").format(lsd.getNgayDo()) + " với mã vé " + lsd.getMaVe() + " không thành công!;";
	        } else {
	        	messenger += "Xóa ngày " + new SimpleDateFormat("dd-MM-yyyy").format(lsd.getNgayDo()) + " với mã vé " + lsd.getMaVe() + " thành công!;";
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
	 * Xóa nhiều lịch sử dò
	 * @param list
	 * @return
	 */
	public boolean deleteLichSuDo(List<LichSuDo> list) {
		Boolean del = false;
		String sql = "Begin transaction ";
		for (LichSuDo lichSuDo : list) {
			sql += "delete from LICHSU_DO where ID = " + lichSuDo.getId() + "; ";
		}
		sql += "commit transaction";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	for (LichSuDo lichSuDo : list) {
	        		messenger += "Xóa ngày " + new SimpleDateFormat("dd-MM-yyyy").format(lichSuDo.getNgayDo()) + " với mã vé " + lichSuDo.getMaVe() + " không thành công!;";
				}
	        } else {
	        	for (LichSuDo lichSuDo : list) {
	        		messenger += "Xóa ngày " + new SimpleDateFormat("dd-MM-yyyy").format(lichSuDo.getNgayDo()) + " với mã vé " + lichSuDo.getMaVe() + " thành công!;";
				}
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
	 * Thêm lich sử dò
	 * @param lsd
	 * @return
	 */
	public Boolean insertLichSuDo(LichSuDo lsd) {
		Boolean insert = false;
		String ngay = new SimpleDateFormat("yyyyMMdd").format(lsd.getNgayDo());
		String sql = "insert into LICHSU_DO(MAND, NGAYDO, MAVE, MAGIAI, KETQUA) "
				+ "values (" + lsd.getMaND() + ", '" + ngay + "', '" + lsd.getMaVe() + "', " + lsd.getMaGiai() + ", " + lsd.getKETQUA() + ");";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {	     
	        } else {
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
	 * Kiểm tra tồn tại lịch sử dò
	 * @param lsd
	 * @return
	 */
	public boolean isLichSuDo(LichSuDo lsd) {
		Boolean bl = false;
		String ngay = new SimpleDateFormat("yyyyMMdd").format(lsd.getNgayDo());
		String sql = "select * from LICHSU_DO where MAND = " + lsd.getMaND() + " AND MAVE = '" + lsd.getMaVe() + "' AND MAGIAI = " + lsd.getMaGiai() + " "
				+ "AND NGAYDO = '" + ngay + "';";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(request);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()) {
	        	bl = true;
	        } 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return bl;
	}
}

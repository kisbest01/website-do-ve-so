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
import model.VeDo;

/**
 * @author kisbest01
 *
 */
public class VeDoDAO {
	public String messenger;
	HttpServletRequest requet;
	
	public VeDoDAO(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.requet = request;
		messenger = "";
	}
	
	/**
	 * Lấy danh sách từ bảng chi tiết giải
	 * @param start
	 * @param total
	 * @return
	 */
	public List<VeDo> getVeDo( int start, int total) {
		String fetch = "";
		if(total > 0) {
			fetch = "FETCH NEXT " + total + " ROWS ONLY";
		}
		List<VeDo> list = new ArrayList<VeDo>();
		String sql = "select * from CHITIET_GIAI "
				+ "order by GIAINGAY desc "
				+ "OFFSET " + start + " ROWS " + fetch + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	        	String thu = new SimpleDateFormat("EEEE").format(rs.getDate(2));
	            VeDo  vd = new VeDo(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
	            		rs.getString(11), rs.getString(12), thu);
	            list.add(vd);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return list;
	}
	
	/**
	 * Tìm vé dò theo ID
	 * @param magiai
	 * @return
	 */
	public VeDo getVeDo( int magiai) {
		VeDo  vd = null;
		String sql = "select * from CHITIET_GIAI "
				+ "where MAGIAI = " + magiai + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	        	String thu = new SimpleDateFormat("EEEE").format(rs.getDate(2));
	            vd = new VeDo(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
	            		rs.getString(11), rs.getString(12), thu);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return vd;
	}
	
	/**
	 * Tìm vé dò theo ngày, tỉnh
	 * @param magiai
	 * @return
	 * @throws ParseException 
	 */
	public List<VeDo> searchVeDo(String ngay, String tinh) throws ParseException {
		List<VeDo> list = new ArrayList<>();
		String sql = "select C.MAGIAI, C.GIAINGAY, C.MATINH, C.DACBIET, C.GIAINHAT, C.GIAINHI, C.GIAIBA, C.GIAIBON, C.GIAINAM, C.GIAISAU, C.GIAIBAY, C.GIAITAM "
				+ "from CHITIET_GIAI C "
				+ "JOIN TINH T "
				+ "ON C.MATINH = T.MATINH "
				+ "where T.TENTINH LIKE N'%" + tinh + "%'";
		if(ngay != null && !ngay.isEmpty()) {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
			sql += "AND C.GIAINGAY = '" + new SimpleDateFormat("yyyyMMdd").format(date) + "';";
			
		}
						
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	        	String thu = new SimpleDateFormat("EEEE").format(rs.getDate(2));
	            VeDo vd = new VeDo(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
	            		rs.getString(11), rs.getString(12), thu);
	            list.add(vd);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return list;
	}
	
	
	/**
	 * Update vé dò
	 * @param vd
	 * @return
	 */
	public Boolean updateVeDo(VeDo vd) {
		Boolean update = false;
		String ngay = new SimpleDateFormat("yyyyMMdd").format(vd.getNgay());
		String sql = "UPDATE CHITIET_GIAI " 
        		+ "SET GIAINGAY = '" + ngay + "', "
				+ "MATINH = " + vd.getMatinh() + ", "
				+ "DACBIET = '" + vd.getGiaiDB() + "', "
				+ "GIAINHAT = '" + vd.getGiaiNhat() + "', "
				+ "GIAINHI = '" + vd.getGiaiNhi() + "', " 
				+ "GIAIBA = '" + vd.getGiaiBa() + "', "
				+ "GIAIBON = '" + vd.getGiaiBon() + "', "
				+ "GIAINAM = '" + vd.getGiaiNam() + "', "
				+ "GIAISAU = '" + vd.getGiaiSau() + "', "
				+ "GIAIBAY = '" + vd.getGiaiBay();
		if(vd.getGiaiTam() != null) {
			sql += "', GIAITAM = '" + vd.getGiaiTam();
		}
			sql	+= "' WHERE MAGIAI = " + vd.getId() + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Cập nhật vé dò thất bại!;";
	        } else {
	        	messenger += "Cập nhật vé dò thành công!;";
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
	 * Thêm vé dò
	 * @param vd
	 * @return
	 */
	public Boolean insertVeDo(VeDo vd) {
		Boolean insert = false;
		String ngay = new SimpleDateFormat("yyyyMMdd").format(vd.getNgay());
		String sql = "insert into CHITIET_GIAI values ('" + ngay + "', " + vd.getMatinh() + ", '" + vd.getGiaiDB() + "', '" + vd.getGiaiNhat() +
				"', '" + vd.getGiaiNhi() + "', '" + vd.getGiaiBa() + "', '" + vd.getGiaiBon() + "', '" + vd.getGiaiNam() + "', '" + vd.getGiaiSau() +
				"', '" + vd.getGiaiBay() + "', '" + vd.getGiaiTam() + "');";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Thêm vé dò thất bại!;";
	        	insert = false;
	        } else {
	        	messenger += "Thêm vé dò thành công!;";
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
	 * Xóa nhiều vé dò
	 * @param list
	 * @return
	 */
	public boolean deleteListVeDo(List<VeDo> list) {
		LichSuDoDAO lsd = new LichSuDoDAO(requet);
		String sql = "Begin transaction ";
		Boolean bl = false;
		for (VeDo vd : list) {
			if(lsd.search(vd.getId()).size() > 0) {
				sql += "delete from LICHSU_DO where MAGIAI = " + vd.getId() + "; ";
			}
			sql += "delete from CHITIET_GIAI where MAGIAI = " + vd.getId() + "; ";
		}
		sql += "commit transaction";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        int numberRowsAffected = stmt.executeUpdate(sql);
	        if(numberRowsAffected == 0) {
	        	messenger += "Xóa vé dò thất bại!;";
	        } else {
	        	messenger += "Xóa vé dò thành công!;";
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
	 * search vé dò theo ngày, ID tỉnh
	 * @param ngay
	 * @param matinh
	 * @return
	 */
	public VeDo searchVeDo(Date ngay, int matinh) {
		VeDo  vd = null;
		String sql = "select * from CHITIET_GIAI "
				+ "where GIAINGAY = '" + new SimpleDateFormat("yyyyMMdd").format(ngay) + "' "
						+ "AND MATINH = " + matinh + ";";
		try {
			// connect to database
	        Connection conn = MyUtils.getStoredConnection(requet);
	        // crate statement
	        Statement stmt = conn.createStatement();
	        // get data from table
	        ResultSet rs = stmt.executeQuery(sql);
        	// show data
	        while (rs.next()) {
	        	String thu = new SimpleDateFormat("EEEE").format(rs.getDate(2));
	            vd = new VeDo(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
	            		rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
	            		rs.getString(11), rs.getString(12), thu);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messenger += "Đây là một lỗi: " + e.getMessage() + ";";
		}
		
		return vd;
	}
	
	/**
	 * So sánh các giải 
	 * @param mave
	 * @param giai
	 * @return
	 */
	public boolean isDoVeSo(String mave, String[] giai) {
		for (String string : giai) {
			if(mave.endsWith(string)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean validateVeDo(VeDo vedo, String mien) {
		Boolean insert = true;
		if(mien.isEmpty()) {
			messenger += "Chưa nhập vùng miền.;";
			insert = false;
		} 
		if(vedo.getMatinh() == 0) {
			messenger += "Chưa nhập Tỉnh/Thành phố.;";
			insert = false;
		}
		switch (mien) {
		case "Miền Bắc":
			if(vedo.getGiaiDB().isEmpty()) {
				messenger += "Chưa nhập mã số giải đặc biệt.;";
				insert = false;
			} else if(!vedo.getGiaiDB().matches("[0-9]{5}")) {
				messenger += "Mã số giải đặc biệt chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiNhat().isEmpty()) {
				messenger += "Chưa nhập mã số giải nhất.;";
				insert = false;
			} else if(!vedo.getGiaiNhat().matches("[0-9]{5}")) {
				messenger += "Mã số giải nhất chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiNhi().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải nhì.;";
				insert = false;
			} else if(!vedo.getGiaiNhi().matches("[0-9]{5},\\s[0-9]{5}")) {
				messenger += "Mã số giải nhì chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiBa().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải ba.;";
				insert = false;
			} else if(!vedo.getGiaiBa().matches("[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5}")) {
				messenger += "Mã số giải ba chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiBon().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải bốn.;";
				insert = false;
			} else if(!vedo.getGiaiBon().matches("[0-9]{4},\\s[0-9]{4},\\s[0-9]{4},\\s[0-9]{4}")) {
				messenger += "Mã số giải bốn chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiNam().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải năm.;";
				insert = false;
			} else if(!vedo.getGiaiNam().matches("[0-9]{4},\\s[0-9]{4},\\s[0-9]{4},\\s[0-9]{4},\\s[0-9]{4},\\s[0-9]{4}")) {
				messenger += "Mã số giải năm chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiSau().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải sáu.;";
				insert = false;
			} else if(!vedo.getGiaiSau().matches("[0-9]{3},\\s[0-9]{3},\\s[0-9]{3}")) {
				messenger += "Mã số giải sáu chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiBay().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải bảy.;";
				insert = false;
			} else if(!vedo.getGiaiBay().matches("[0-9]{2},\\s[0-9]{2},\\s[0-9]{2},\\s[0-9]{2}")) {
				messenger += "Mã số giải bảy chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			break;

		default:
			if(vedo.getGiaiDB().isEmpty()) {
				messenger += "Chưa nhập mã số giải đặc biệt.;";
				insert = false;
			} else if(!vedo.getGiaiDB().matches("[0-9]{6}")) {
				messenger += "Mã số giải đặc biệt chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiNhat().isEmpty()) {
				messenger += "Chưa nhập mã số giải nhất.;";
				insert = false;
			} else if(!vedo.getGiaiNhat().matches("[0-9]{5}")) {
				messenger += "Mã số giải nhất chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiNhi().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải nhì.;";
				insert = false;
			} else if(!vedo.getGiaiNhi().matches("[0-9]{5}")) {
				messenger += "Mã số giải nhì chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiBa().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải ba.;";
				insert = false;
			} else if(!vedo.getGiaiBa().matches("[0-9]{5},\\s[0-9]{5}")) {
				messenger += "Mã số giải ba chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiBon().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải bốn.;";
				insert = false;
			} else if(!vedo.getGiaiBon().matches("[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5},\\s[0-9]{5}")) {
				messenger += "Mã số giải bốn chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiNam().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải năm.;";
				insert = false;
			} else if(!vedo.getGiaiNam().matches("[0-9]{4}")) {
				messenger += "Mã số giải năm chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiSau().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải sáu.;";
				insert = false;
			} else if(!vedo.getGiaiSau().matches("[0-9]{4},\\s[0-9]{4},\\s[0-9]{4}")) {
				messenger += "Mã số giải sáu chưa hợp lệ hoặc chưa nhập đủ mã của giải.;";
				insert = false;
			}
			
			if(vedo.getGiaiBay().replace(",", "").isEmpty()) {
				messenger += "Chưa nhập mã số giải bảy.;";
				insert = false;
			} else if(!vedo.getGiaiBay().matches("[0-9]{3}")) {
				messenger += "Mã số giải bảy chưa hợp lệ.;";
				insert = false;
			}
			
			if(vedo.getGiaiTam().isEmpty()) {
				messenger += "Chưa nhập mã số giải tám.;";
				insert = false;
			} else if(!vedo.getGiaiTam().matches("[0-9]{2}")) {
				messenger += "Mã số giải tám chưa hợp lệ.;";
				insert = false;
			}
			break;
		}
		return insert;
	}
}

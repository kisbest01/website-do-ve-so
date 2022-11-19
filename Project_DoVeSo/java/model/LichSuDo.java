/**
 * 
 */
package model;

import java.util.Date;

/**
 * @author KISS
 *
 */
public class LichSuDo {
	private int id;
	private int maND;
	private Date ngayDo;
	private String maVe;
	private int maGiai;
	private int ketQua;
	private Date giaingay;
	/**
	 * @return the giaingay
	 */
	public Date getGiaingay() {
		return giaingay;
	}

	/**
	 * @param giaingay the giaingay to set
	 */
	public void setGiaingay(Date giaingay) {
		this.giaingay = giaingay;
	}

	/**
	 * @return the tinh
	 */
	public String getTinh() {
		return tinh;
	}

	/**
	 * @param tinh the tinh to set
	 */
	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	private String tinh;
	
	public LichSuDo(int id, int maND, Date ngayDo, String maVe, int maGiai, int ketQua) {
		this.id = id;
		this.maND = maND;
		this.ngayDo = ngayDo;
		this.maVe = maVe;
		this.maGiai = maGiai;
		this.ketQua = ketQua;
	}
	
	public LichSuDo(int id, int maND, Date ngayDo, String maVe, int maGiai, int ketQua, Date giaingay, String tinh) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.maND = maND;
		this.ngayDo = ngayDo;
		this.maVe = maVe;
		this.maGiai = maGiai;
		this.ketQua = ketQua;
		this.giaingay = giaingay;
		this.tinh = tinh;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the maND
	 */
	public int getMaND() {
		return maND;
	}

	/**
	 * @param maND the maND to set
	 */
	public void setMaND(int maND) {
		this.maND = maND;
	}

	/**
	 * @return the ngayDO
	 */
	public Date getNgayDo() {
		return ngayDo;
	}

	/**
	 * @param ngayDO the ngayDO to set
	 */
	public void setNgayDo(Date ngayDo) {
		this.ngayDo = ngayDo;
	}

	/**
	 * @return the maVe
	 */
	public String getMaVe() {
		return maVe;
	}

	/**
	 * @param maVe the maVe to set
	 */
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	/**
	 * @return the maGiai
	 */
	public int getMaGiai() {
		return maGiai;
	}

	/**
	 * @param maGiai the maGiai to set
	 */
	public void setMaGiai(int maGiai) {
		this.maGiai = maGiai;
	}

	/**
	 * @return the ketQua
	 */
	public String getKetQua() {
		String text = "";
		
		switch (ketQua) {
		case -1:
			text = "không trúng giải nào";
			break;

		case 0:
			text = "trúng giải đặc biệt";
			break;

		case 1:
			text = "trúng giải nhất";
			break;

		case 2:
			text = "trúng giải nhì";
			break;

		case 3:
			text = "trúng giải ba";
			break;

		case 4:
			text = "trúng giải bốn";
			break;

		case 5:
			text = "trúng giải năm";
			break;

		case 6:
			text = "trúng giải sáu";
			break;

		case 7:
			text = "trúng giải bảy";
			break;

		case 8:
			text = "trúng giải tám";
			break;

		default:
			text = "không trúng giải nào";
			break;
		}
		
		return text;
	}
	
	/**
	 * @return the ketQua
	 */
	public int getKETQUA() {
		return ketQua;
	}

	/**
	 * @param ketQua the ketQua to set
	 */
	public void setKetQua(int ketQua) {
		this.ketQua = ketQua;
	}
}

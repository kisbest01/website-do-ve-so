package model;

import java.util.Date;

public class VeDo {

	private int id;
	private Date ngay;
	private String giaiDB;
	private String giaiNhat;
	private String giaiNhi;
	private String giaiBa;
	private String giaiBon;
	private String giaiNam;
	private String giaiSau;
	private String giaiBay;
	private String giaiTam;
	private String thu;
	private int matinh;
	
	public VeDo() {
		// TODO Auto-generated constructor stub
	}
	
	public VeDo (int id, Date ngay, int matinh, String giaiDB, String giaiNhat, String giaiNhi, String giaiBa, 
			String giaiBon, String giaiNam, String giaiSau, String giaiBay, String giaiTam , String thu) {
		this.id = id;
		this.ngay = ngay;
		this.giaiDB = giaiDB;
		this.giaiNhat = giaiNhat;
		this.giaiNhi = giaiNhi;
		this.giaiBa = giaiBa;
		this.giaiBon = giaiBon;
		this.giaiNam = giaiNam;
		this.giaiSau = giaiSau;
		this.giaiBay = giaiBay;
		this.giaiTam = giaiTam;
		this.thu = thu;
		this.matinh = matinh;
	}
	
	public VeDo (Date ngay, int matinh, String giaiDB, String giaiNhat, String giaiNhi, String giaiBa, 
			String giaiBon, String giaiNam, String giaiSau, String giaiBay, String giaiTam, String thu) {
		this.ngay = ngay;
		this.giaiDB = giaiDB;
		this.giaiNhat = giaiNhat;
		this.giaiNhi = giaiNhi;
		this.giaiBa = giaiBa;
		this.giaiBon = giaiBon;
		this.giaiNam = giaiNam;
		this.giaiSau = giaiSau;
		this.giaiBay = giaiBay;
		this.giaiTam = giaiTam;
		this.matinh = matinh;
		this.thu = thu;
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
	 * @return the ngay
	 */
	public Date getNgay() {
		return ngay;
	}

	/**
	 * @param ngay the ngay to set
	 */
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	/**
	 * @return the giaiDB
	 */
	public String getGiaiDB() {
		return giaiDB;
	}

	/**
	 * @param giaiDB the giaiDB to set
	 */
	public void setGiaiDB(String giaiDB) {
		this.giaiDB = giaiDB;
	}

	/**
	 * @return the giaiNhat
	 */
	public String getGiaiNhat() {
		return giaiNhat;
	}

	/**
	 * @param giaiNhat the giaiNhat to set
	 */
	public void setGiaiNhat(String giaiNhat) {
		this.giaiNhat = giaiNhat;
	}

	/**
	 * @return the giaiNhi
	 */
	public String getGiaiNhi() {
		return giaiNhi;
	}

	/**
	 * @param giaiNhi the giaiNhi to set
	 */
	public void setGiaiNhi(String giaiNhi) {
		this.giaiNhi = giaiNhi;
	}

	/**
	 * @return the giaiBa
	 */
	public String getGiaiBa() {
		return giaiBa;
	}

	/**
	 * @param giaiBa the giaiBa to set
	 */
	public void setGiaiBa(String giaiBa) {
		this.giaiBa = giaiBa;
	}

	/**
	 * @return the giaiBon
	 */
	public String getGiaiBon() {
		return giaiBon;
	}

	/**
	 * @param giaiBon the giaiBon to set
	 */
	public void setGiaiBon(String giaiBon) {
		this.giaiBon = giaiBon;
	}

	/**
	 * @return the giaiNam
	 */
	public String getGiaiNam() {
		return giaiNam;
	}

	/**
	 * @param giaiNam the giaiNam to set
	 */
	public void setGiaiNam(String giaiNam) {
		this.giaiNam = giaiNam;
	}

	/**
	 * @return the giaiSau
	 */
	public String getGiaiSau() {
		return giaiSau;
	}

	/**
	 * @param giaiSau the giaiSau to set
	 */
	public void setGiaiSau(String giaiSau) {
		this.giaiSau = giaiSau;
	}

	/**
	 * @return the giaiBay
	 */
	public String getGiaiBay() {
		return giaiBay;
	}

	/**
	 * @param giaiBay the giaiBay to set
	 */
	public void setGiaiBay(String giaiBay) {
		this.giaiBay = giaiBay;
	}

	/**
	 * @return the giaiTam
	 */
	public String getGiaiTam() {
		return giaiTam;
	}

	/**
	 * @param giaiTam the giaiTam to set
	 */
	public void setGiaiTam(String giaiTam) {
		this.giaiTam = giaiTam;
	}

	/**
	 * @return the thu
	 */
	public String getThu() {
		String text = "";
		switch (thu) {
		case "Monday":
			text = "Thứ Hai";
			break;
			
		case "Tuesday":
			text = "Thứ Ba";
			break;
			
		case "Wednesday":
			text = "Thứ Tư";
			break;
			
		case "Thursday":
			text = "Thứ Năm";
			break;
			
		case "Friday":
			text = "Thứ Sáu";
			break;
			
		case "Saturday":
			text = "Thứ Bảy";
			break;
			
		case "Sunday":
			text = "Chủ Nhật";
			break;
			
		default: 
			text = thu;
			break;
		}
		return text;
	}

	/**
	 * @param thu the thu to set
	 */
	public void setThu(String thu) {
		this.thu = thu;
	}

	/**
	 * @return the matinh
	 */
	public int getMatinh() {
		return matinh;
	}

	/**
	 * @param matinh the matinh to set
	 */
	public void setMatinh(int matinh) {
		this.matinh = matinh;
	}
}

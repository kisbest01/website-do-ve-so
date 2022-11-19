package model;

public class Tinh {
	private int id;
	private String tentinh;
	private String mien;
	
	public Tinh(int id, String tentinh, String mien) {
		this.id = id;
		this.tentinh = tentinh;
		this.mien = mien;
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
	 * @return the tentinh
	 */
	public String getTentinh() {
		return tentinh;
	}

	/**
	 * @param tentinh the tentinh to set
	 */
	public void setTentinh(String tentinh) {
		this.tentinh = tentinh;
	}

	/**
	 * @return the mien
	 */
	public String getMien() {
		return mien;
	}

	/**
	 * @param mien the mien to set
	 */
	public void setMien(String mien) {
		this.mien = mien;
	}
}

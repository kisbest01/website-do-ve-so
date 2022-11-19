/**
 * 
 */
package model;

/**
 * @author KISS
 *
 */
public class Avatar {
	private int id;
	private int maND;
	private String src;
	
	public Avatar(int id, int maND, String src) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.maND = maND;
		this.src = src;
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
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}
	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	
}

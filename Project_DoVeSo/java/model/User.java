/**
 * 
 */
package model;

/**
 * @author KISS
 *
 */
public class User {
	private int id;
	private String username;
	private String ten;
	private String phone;
	private String email;
	private String password;
	private String address;
	private int role;
	private int status;
	private String src;
	public String messenger;
	
	public User(int id, String username, String ten, String phone, String email, String password, String address, int role, int status, String src) {
		this.id = id;
		this.ten = ten;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.status = status;
		this.setUsername(username);
		this.setSrc(src);
	}

	public User() {
		// TODO Auto-generated constructor stub
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
	 * @return the ten
	 */
	public String getTen() {
		return ten;
	}

	/**
	 * @param ten the ten to set
	 */
	public void setTen(String ten) {
		this.ten = ten;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		String text = "";
		
		switch (role) {
		case 1:
			text = "Admin";
			break;
		
		case 0:
			text = "User";
			break;	
			
		default:
			text = "User";
			break;
		}
		return text;
	}
	public int getROLE() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		String text = "";
		switch (status) {
		case 2:
			text = "Trực tuyến";
			break;
			
		case 1:
			text = "Ngoại tuyến";
			break;
		
		case 0:
			text = "Đã khóa";
			break;
		
		case -1:
			text = "Đã xóa";
			break;

		default:
			text = "Đã khóa";
			break;
		}
		return text;
	}
	public int getSTATUS() {
		return status;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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

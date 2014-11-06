package dto;

public class FriendInfomation{
	private int userId;
	private String surl;
	private String lurl;
	private String fname;
	private String sign;

	public FriendInfomation() {
	}
	
	public FriendInfomation(String surl, String fname) {
		super();
		this.surl = surl;
		this.fname = fname;
	}

	public FriendInfomation(int userId, String surl, String lurl, String fname,
			String sign) {
		super();
		this.userId = userId;
		this.surl = surl;
		this.lurl = lurl;
		this.fname = fname;
		this.sign = sign;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLurl() {
		return lurl;
	}

	public void setLurl(String lurl) {
		this.lurl = lurl;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}

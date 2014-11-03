package dto;

public class FriendInfomation{
	private String surl;
	private String fname;

	public FriendInfomation() {
	}
	
	public FriendInfomation(String surl, String fname) {
		super();
		this.surl = surl;
		this.fname = fname;
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

}

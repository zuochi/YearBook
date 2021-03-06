package dto;

public class FriendInfomation{
	private int userId;
	private String surl;
	private String lurl;
	private String fname;
	private String sign;
	private boolean isFriend;
	//获取当前user的好友列表,判断与该用户的关系,0:未关注,1:已关注,2:朋友,3:被关注
	private int relationShip;

	public FriendInfomation() {
	}
	
	public FriendInfomation(String surl, String fname) {
		super();
		this.surl = surl;
		this.fname = fname;
	}

	public FriendInfomation(int userId, String lurl, String fname,
			String sign) {
		super();
		this.userId = userId;
		this.lurl = lurl;
		this.fname = fname;
		this.sign = sign;
	}

	public FriendInfomation(int userId, String lurl, String fname,
			String sign, boolean isFriend) {
		super();
		this.userId = userId;
		this.lurl = lurl;
		this.fname = fname;
		this.sign = sign;
		this.isFriend = isFriend;
	}
	
	public FriendInfomation(int userId, String lurl, String fname,
			String sign, boolean isFriend, int relationShip) {
		super();
		this.userId = userId;
		this.lurl = lurl;
		this.fname = fname;
		this.sign = sign;
		this.isFriend = isFriend;
		this.relationShip = relationShip;
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

	public boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	
	public int getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(int relationShip) {
		this.relationShip = relationShip;
	}

	//配合SQL使用的get set方法
	public String getUrl_s() {
		return surl;
	}

	public void setUrl_s(String surl) {
		this.surl = surl;
	}

	public String getName() {
		return fname;
	}

	public void setName(String fname) {
		this.fname = fname;
	}
}

package bean;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private Profession profession;
	private SchoolYear schoolYear;
	private HeadPhoto headPhoto;
	private String userName;
	private String password;
	private String name;
	private Integer sex;
	private String sign;
	private Timestamp signupDate;
	private Timestamp lastLogintime;
	private String telePhone;
	private String homeTown;
	private String qq;
	private String weChat;
	private String email;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	/** full constructor */
	public User(Integer id, Profession profession, SchoolYear schoolYear,
			HeadPhoto headPhoto, String userName, String password, String name,
			Integer sex, String sign, Timestamp signupDate,
			Timestamp lastLogintime, String telePhone, String homeTown,
			String qq, String weChat, String email, Integer isDelete) {
		this.id = id;
		this.profession = profession;
		this.schoolYear = schoolYear;
		this.headPhoto = headPhoto;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.sign = sign;
		this.signupDate = signupDate;
		this.lastLogintime = lastLogintime;
		this.telePhone = telePhone;
		this.homeTown = homeTown;
		this.qq = qq;
		this.weChat = weChat;
		this.email = email;
		this.isDelete = isDelete;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profession getProfession() {
		return this.profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public SchoolYear getSchoolYear() {
		return this.schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public HeadPhoto getHeadPhoto() {
		return this.headPhoto;
	}

	public void setHeadPhoto(HeadPhoto headPhoto) {
		this.headPhoto = headPhoto;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Timestamp getSignupDate() {
		return this.signupDate;
	}

	public void setSignupDate(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	public Timestamp getLastLogintime() {
		return this.lastLogintime;
	}

	public void setLastLogintime(Timestamp lastLogintime) {
		this.lastLogintime = lastLogintime;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeChat() {
		return this.weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
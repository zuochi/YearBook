package bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
	private String qq;
	private String weChat;
	private String email;
	private Integer isDelete;
	private Set privateLettersForUserBid = new HashSet(0);
	private Set friendListsForFriendId = new HashSet(0);
	private Set advices = new HashSet(0);
	private Set repliesForUserId = new HashSet(0);
	private Set IWantTops = new HashSet(0);
	private Set privateLettersForUserId = new HashSet(0);
	private Set photoAlbums = new HashSet(0);
	private Set repliesForUserBid = new HashSet(0);
	private Set bbses = new HashSet(0);
	private Set friendListsForUserId = new HashSet(0);
	private Set photos = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	/** full constructor */
	public User(Profession profession, SchoolYear schoolYear,
			HeadPhoto headPhoto, String userName, String password, String name,
			Integer sex, String sign, Timestamp signupDate,
			Timestamp lastLogintime, String qq, String weChat, String email,Integer isDelete,
			Set privateLettersForUserBid, Set friendListsForFriendId,
			Set advices, Set repliesForUserId, Set IWantTops,
			Set privateLettersForUserId, Set photoAlbums,
			Set repliesForUserBid, Set bbses, Set friendListsForUserId,
			Set photos) {
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
		this.qq = qq;
		this.weChat = weChat;
		this.email = email;
		this.isDelete = isDelete;
		this.privateLettersForUserBid = privateLettersForUserBid;
		this.friendListsForFriendId = friendListsForFriendId;
		this.advices = advices;
		this.repliesForUserId = repliesForUserId;
		this.IWantTops = IWantTops;
		this.privateLettersForUserId = privateLettersForUserId;
		this.photoAlbums = photoAlbums;
		this.repliesForUserBid = repliesForUserBid;
		this.bbses = bbses;
		this.friendListsForUserId = friendListsForUserId;
		this.photos = photos;
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

	public Set getPrivateLettersForUserBid() {
		return this.privateLettersForUserBid;
	}

	public void setPrivateLettersForUserBid(Set privateLettersForUserBid) {
		this.privateLettersForUserBid = privateLettersForUserBid;
	}

	public Set getFriendListsForFriendId() {
		return this.friendListsForFriendId;
	}

	public void setFriendListsForFriendId(Set friendListsForFriendId) {
		this.friendListsForFriendId = friendListsForFriendId;
	}

	public Set getAdvices() {
		return this.advices;
	}

	public void setAdvices(Set advices) {
		this.advices = advices;
	}

	public Set getRepliesForUserId() {
		return this.repliesForUserId;
	}

	public void setRepliesForUserId(Set repliesForUserId) {
		this.repliesForUserId = repliesForUserId;
	}

	public Set getIWantTops() {
		return this.IWantTops;
	}

	public void setIWantTops(Set IWantTops) {
		this.IWantTops = IWantTops;
	}

	public Set getPrivateLettersForUserId() {
		return this.privateLettersForUserId;
	}

	public void setPrivateLettersForUserId(Set privateLettersForUserId) {
		this.privateLettersForUserId = privateLettersForUserId;
	}

	public Set getPhotoAlbums() {
		return this.photoAlbums;
	}

	public void setPhotoAlbums(Set photoAlbums) {
		this.photoAlbums = photoAlbums;
	}

	public Set getRepliesForUserBid() {
		return this.repliesForUserBid;
	}

	public void setRepliesForUserBid(Set repliesForUserBid) {
		this.repliesForUserBid = repliesForUserBid;
	}

	public Set getBbses() {
		return this.bbses;
	}

	public void setBbses(Set bbses) {
		this.bbses = bbses;
	}

	public Set getFriendListsForUserId() {
		return this.friendListsForUserId;
	}

	public void setFriendListsForUserId(Set friendListsForUserId) {
		this.friendListsForUserId = friendListsForUserId;
	}

	public Set getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set photos) {
		this.photos = photos;
	}

}
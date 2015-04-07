package bean;

import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private String id;
	private User userByUserId;
	private Bbs bbs;
	private PrivateLetter privateLetter;
	private User userByUserBid;
	private Photo photo;
	private String context;
	private Timestamp signupDate;
	private Integer status;
	private Integer isAccusation;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(User userByUserId, User userByUserBid, String context,
			Timestamp signupDate, Integer status) {
		this.userByUserId = userByUserId;
		this.userByUserBid = userByUserBid;
		this.context = context;
		this.signupDate = signupDate;
		this.status = status;
	}

	/** full constructor */
	public Reply(String id, User userByUserId, Bbs bbs,
			PrivateLetter privateLetter, User userByUserBid, Photo photo,
			String context, Timestamp signupDate, Integer status,
			Integer isAccusation, Integer isDelete) {
		this.id = id;
		this.userByUserId = userByUserId;
		this.bbs = bbs;
		this.privateLetter = privateLetter;
		this.userByUserBid = userByUserBid;
		this.photo = photo;
		this.context = context;
		this.signupDate = signupDate;
		this.status = status;
		this.isAccusation = isAccusation;
		this.isDelete = isDelete;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	public Bbs getBbs() {
		return this.bbs;
	}

	public void setBbs(Bbs bbs) {
		this.bbs = bbs;
	}

	public PrivateLetter getPrivateLetter() {
		return this.privateLetter;
	}

	public void setPrivateLetter(PrivateLetter privateLetter) {
		this.privateLetter = privateLetter;
	}

	public User getUserByUserBid() {
		return this.userByUserBid;
	}

	public void setUserByUserBid(User userByUserBid) {
		this.userByUserBid = userByUserBid;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Timestamp getSignupDate() {
		return this.signupDate;
	}

	public void setSignupDate(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsAccusation() {
		return isAccusation;
	}

	public void setIsAccusation(Integer isAccusation) {
		this.isAccusation = isAccusation;
	}

}
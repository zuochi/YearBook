package bean;

import java.util.Date;

/**
 * PrivateLetter entity. @author MyEclipse Persistence Tools
 */

public class PrivateLetter implements java.io.Serializable {

	// Fields

	private Integer id;
	private User userByUserId;
	private User userByUserBid;
	private String context;
	private Date signupDate;
	private Integer status;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public PrivateLetter() {
	}

	/** minimal constructor */
	public PrivateLetter(User userByUserId, User userByUserBid, String context,
			Date signupDate, Integer status) {
		this.userByUserId = userByUserId;
		this.userByUserBid = userByUserBid;
		this.context = context;
		this.signupDate = signupDate;
		this.status = status;
	}

	/** full constructor */
	public PrivateLetter(User userByUserId, User userByUserBid, String context,
			Date signupDate, Integer status, Integer isDelete) {
		this.userByUserId = userByUserId;
		this.userByUserBid = userByUserBid;
		this.context = context;
		this.signupDate = signupDate;
		this.status = status;
		this.isDelete = isDelete;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

	public User getUserByUserBid() {
		return this.userByUserBid;
	}

	public void setUserByUserBid(User userByUserBid) {
		this.userByUserBid = userByUserBid;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getSignupDate() {
		return this.signupDate;
	}

	public void setSignupDate(Date signupDate) {
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
}
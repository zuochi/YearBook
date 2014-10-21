package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private Set replies = new HashSet(0);

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
			Date signupDate, Integer status, Integer isDelete, Set replies) {
		this.userByUserId = userByUserId;
		this.userByUserBid = userByUserBid;
		this.context = context;
		this.signupDate = signupDate;
		this.status = status;
		this.isDelete = isDelete;
		this.replies = replies;
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

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}
package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bbs entity. @author MyEclipse Persistence Tools
 */

public class Bbs implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String context;
	private Date signupDate;
	private Integer lol;
	private Integer isDelete;
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Bbs() {
	}

	/** minimal constructor */
	public Bbs(User user, String context, Date signupDate) {
		this.user = user;
		this.context = context;
		this.signupDate = signupDate;
	}

	/** full constructor */
	public Bbs(User user, String context, Date signupDate, Integer lol,
			Integer isDelete, Set replies) {
		this.user = user;
		this.context = context;
		this.signupDate = signupDate;
		this.lol = lol;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Integer getLol() {
		return this.lol;
	}

	public void setLol(Integer lol) {
		this.lol = lol;
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
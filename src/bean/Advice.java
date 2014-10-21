package bean;

import java.util.Date;

/**
 * Advice entity. @author MyEclipse Persistence Tools
 */

public class Advice implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String context;
	private Date signupDate;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Advice() {
	}

	/** minimal constructor */
	public Advice(User user, String context, Date signupDate) {
		this.user = user;
		this.context = context;
		this.signupDate = signupDate;
	}

	/** full constructor */
	public Advice(User user, String context, Date signupDate, Integer isDelete) {
		this.user = user;
		this.context = context;
		this.signupDate = signupDate;
		this.isDelete = isDelete;
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

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
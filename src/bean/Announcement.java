package bean;

import java.util.Date;

/**
 * Advice entity. @author MyEclipse Persistence Tools
 */

public class Announcement implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String title;
	private String context;
	private Integer top;
	private Integer topId;
	private Date signupDate;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Announcement() {}

	/** full constructor */
	public Announcement(Integer id, User user, String title, String context,
			Integer top, Integer topId, Date signupDate, Integer isDelete) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.context = context;
		this.top = top;
		this.topId = topId;
		this.signupDate = signupDate;
		this.isDelete = isDelete;
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	public Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
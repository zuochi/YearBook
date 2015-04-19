package bean;

import java.sql.Timestamp;

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
	private Timestamp signupDate;
	private Timestamp topDate;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Announcement() {}

	/** full constructor */
	public Announcement(Integer id, User user, String title, String context,
			Integer top, Integer topId, Timestamp signupDate,Timestamp topDate, Integer isDelete) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.context = context;
		this.top = top;
		this.topId = topId;
		this.signupDate = signupDate;
		this.topDate = topDate;
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

	public Timestamp getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	public Timestamp getTopDate() {
		return topDate;
	}

	public void setTopDate(Timestamp topDate) {
		this.topDate = topDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
package dto;

import java.util.Date;

/**
 * Advice entity. @author MyEclipse Persistence Tools
 */

public class Announcement implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String userName;
	private String title;
	private String context;
	private Integer top;
	private Integer topId;
	private Date signupDate;
	private Integer isDelete;

	// Constructors
	public Announcement(Integer id, Integer userId, String userName,
			String title, String context, Integer top, Integer topId,
			Date signupDate, Integer isDelete) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
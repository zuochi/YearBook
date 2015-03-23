package bean;

import java.sql.Timestamp;

/**
 * IWantTop entity. @author MyEclipse Persistence Tools
 */

public class IWantTop implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Photo photo;
	private Timestamp signupDate;
	private Integer status;
	private Integer isDelete;
	private Timestamp reviewDate;
	
	// Constructors

	/** default constructor */
	public IWantTop() {
	}

	/** minimal constructor */
	public IWantTop(User user, Photo photo, Timestamp signupDate, Integer status) {
		this.user = user;
		this.photo = photo;
		this.signupDate = signupDate;
		this.status = status;
	}

	/** full constructor */
	public IWantTop(User user, Photo photo, Timestamp signupDate,
			Integer status, Integer isDelete, Timestamp reviewDate) {
		this.user = user;
		this.photo = photo;
		this.signupDate = signupDate;
		this.status = status;
		this.isDelete = isDelete;
		this.reviewDate = reviewDate;
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

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
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

	public Timestamp getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}
}
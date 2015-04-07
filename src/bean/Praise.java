package bean;

import java.util.Date;

/**
 * Advice entity. @author MyEclipse Persistence Tools
 */

public class Praise implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Photo photo;
	private Date signupDate;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Praise() {}

	/** full constructor */
	public Praise(Integer id, User user, Photo photo, Date signupDate,
			Integer isDelete) {
		this.id = id;
		this.user = user;
		this.photo = photo;
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

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
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
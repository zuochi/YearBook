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
	private Integer statusCover;
	private Integer isDelete;
	private Timestamp reviewDate;
	private Timestamp praiseDate;
	private Timestamp topDate;
	
	// Constructors

	/** default constructor */
	public IWantTop() {}
	
	public IWantTop(User user, Photo photo, Timestamp signupDate,Integer status,Integer statusCover,Integer isDelete) {
		this.user = user;
		this.photo = photo;
		this.signupDate = signupDate;
		this.statusCover = status;
		this.status = status;
		this.isDelete = isDelete;
	}
	
	/** full constructor */
	public IWantTop(Integer id, User user, Photo photo, Timestamp signupDate,
			Integer status,Integer statusCover, Integer isDelete, Timestamp reviewDate,
			Timestamp praiseDate,Timestamp topDate) {
		this.id = id;
		this.user = user;
		this.photo = photo;
		this.signupDate = signupDate;
		this.status = status;
		this.statusCover = statusCover;
		this.isDelete = isDelete;
		this.reviewDate = reviewDate;
		this.praiseDate = praiseDate;
		this.topDate = topDate;
	}

	// Property accessors

	public Timestamp getPraiseDate() {
		return praiseDate;
	}

	public Timestamp getTopDate() {
		return topDate;
	}

	public void setTopDate(Timestamp topDate) {
		this.topDate = topDate;
	}

	public void setPraiseDate(Timestamp praiseDate) {
		this.praiseDate = praiseDate;
	}

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

	public Integer getStatusCover() {
		return statusCover;
	}

	public void setStatusCover(Integer statusCover) {
		this.statusCover = statusCover;
	}
}
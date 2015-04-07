package bean;

import java.sql.Timestamp;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class FeedBack implements java.io.Serializable {

	// Fields
	private Integer id;
	private User user;
	private User userDeal;
	private String context;
	private Integer status;
	private Timestamp dealDate;
	private Timestamp signupDate;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public FeedBack() {}

	/** full constructor */
	public FeedBack(Integer id, User user, User userDeal, String context,
			Integer status, Timestamp dealDate, Timestamp signupDate,
			Integer isDelete) {
		this.id = id;
		this.user = user;
		this.userDeal = userDeal;
		this.context = context;
		this.status = status;
		this.dealDate = dealDate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Timestamp getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public User getUserDeal() {
		return userDeal;
	}

	public void setUserDeal(User userDeal) {
		this.userDeal = userDeal;
	}

	public Timestamp getDealDate() {
		return dealDate;
	}

	public void setDealDate(Timestamp dealDate) {
		this.dealDate = dealDate;
	}
}
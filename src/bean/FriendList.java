package bean;

import java.sql.Timestamp;

/**
 * FriendList entity. @author MyEclipse Persistence Tools
 */

public class FriendList implements java.io.Serializable {

	// Fields

	private Integer id;
	private User userByUserId;
	private User userByFriendId;
	private Integer isDelete;
	private Timestamp updateDate;

	// Constructors

	/** default constructor */
	public FriendList() {
	}

	/** minimal constructor */
	public FriendList(User userByUserId, User userByFriendId,Integer isDelete, Timestamp updateDate) {
		this.userByUserId = userByUserId;
		this.userByFriendId = userByFriendId;
		this.isDelete = isDelete;
		this.updateDate = updateDate;
	}

	/** full constructor */
	public FriendList(Integer id, User userByUserId, User userByFriendId,
			Integer isDelete, Timestamp updateDate) {
		super();
		this.id = id;
		this.userByUserId = userByUserId;
		this.userByFriendId = userByFriendId;
		this.isDelete = isDelete;
		this.updateDate = updateDate;
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

	public User getUserByFriendId() {
		return this.userByFriendId;
	}

	public void setUserByFriendId(User userByFriendId) {
		this.userByFriendId = userByFriendId;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
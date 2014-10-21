package bean;

/**
 * FriendList entity. @author MyEclipse Persistence Tools
 */

public class FriendList implements java.io.Serializable {

	// Fields

	private Integer id;
	private User userByUserId;
	private User userByFriendId;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public FriendList() {
	}

	/** minimal constructor */
	public FriendList(User userByUserId, User userByFriendId) {
		this.userByUserId = userByUserId;
		this.userByFriendId = userByFriendId;
	}

	/** full constructor */
	public FriendList(User userByUserId, User userByFriendId, Integer isDelete) {
		this.userByUserId = userByUserId;
		this.userByFriendId = userByFriendId;
		this.isDelete = isDelete;
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

}
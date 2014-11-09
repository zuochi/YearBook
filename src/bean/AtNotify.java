package bean;

import java.sql.Timestamp;

/**
 * AtNotify entity. @author MyEclipse Persistence Tools
 */

public class AtNotify implements java.io.Serializable {

	// Fields

	private Integer id;
	private User userByUserId;
	private Bbs bbs;
	private Reply reply;
	private User userByUserBid;
	private Photo photoByPhotoBid;
	private Photo photoByPhotoDescBid;
	private Timestamp atDate;
	private Integer status;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public AtNotify() {
	}

	/** minimal constructor */
	public AtNotify(User userByUserId, User userByUserBid, Timestamp atDate) {
		this.userByUserId = userByUserId;
		this.userByUserBid = userByUserBid;
		this.atDate = atDate;
	}

	/** full constructor */
	public AtNotify(Integer id, User userByUserId, Bbs bbs, Reply reply,
			User userByUserBid, Photo photoByPhotoBid,
			Photo photoByPhotoDescBid, Timestamp atDate, Integer status,
			Integer isDelete) {
		super();
		this.id = id;
		this.userByUserId = userByUserId;
		this.bbs = bbs;
		this.reply = reply;
		this.userByUserBid = userByUserBid;
		this.photoByPhotoBid = photoByPhotoBid;
		this.photoByPhotoDescBid = photoByPhotoDescBid;
		this.atDate = atDate;
		this.status = status;
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

	public Bbs getBbs() {
		return this.bbs;
	}

	public void setBbs(Bbs bbs) {
		this.bbs = bbs;
	}

	public Reply getReply() {
		return this.reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public User getUserByUserBid() {
		return this.userByUserBid;
	}

	public void setUserByUserBid(User userByUserBid) {
		this.userByUserBid = userByUserBid;
	}

	public Photo getPhotoByPhotoBid() {
		return this.photoByPhotoBid;
	}

	public void setPhotoByPhotoBid(Photo photoByPhotoBid) {
		this.photoByPhotoBid = photoByPhotoBid;
	}

	public Photo getPhotoByPhotoDescBid() {
		return this.photoByPhotoDescBid;
	}

	public void setPhotoByPhotoDescBid(Photo photoByPhotoDescBid) {
		this.photoByPhotoDescBid = photoByPhotoDescBid;
	}

	public Timestamp getAtDate() {
		return this.atDate;
	}

	public void setAtDate(Timestamp atDate) {
		this.atDate = atDate;
	}
	
	public Integer getStatus() {
		return status;
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

}
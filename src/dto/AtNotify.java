package dto;

import java.sql.Timestamp;

/**
 * AtNotify entity. @author MyEclipse Persistence Tools
 */

public class AtNotify implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer user_id;
	private Integer user_bid;
	private Integer bbs_bid;
	private Integer reply_bid;
	private Integer photo_bid;
	private Integer photoDesc_bid;
	private Timestamp at_date;
	private Integer status;
	private Integer is_delete;

	// Constructors

	/** default constructor */
	public AtNotify() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getUser_bid() {
		return user_bid;
	}

	public void setUser_bid(Integer user_bid) {
		this.user_bid = user_bid;
	}

	public Integer getBbs_bid() {
		return bbs_bid;
	}

	public void setBbs_bid(Integer bbs_bid) {
		this.bbs_bid = bbs_bid;
	}

	public Integer getReply_bid() {
		return reply_bid;
	}

	public void setReply_bid(Integer reply_bid) {
		this.reply_bid = reply_bid;
	}

	public Integer getPhoto_bid() {
		return photo_bid;
	}

	public void setPhoto_bid(Integer photo_bid) {
		this.photo_bid = photo_bid;
	}

	public Integer getPhotoDesc_bid() {
		return photoDesc_bid;
	}

	public void setPhotoDesc_bid(Integer photoDesc_bid) {
		this.photoDesc_bid = photoDesc_bid;
	}

	public Timestamp getAt_date() {
		return at_date;
	}

	public void setAt_date(Timestamp at_date) {
		this.at_date = at_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

}
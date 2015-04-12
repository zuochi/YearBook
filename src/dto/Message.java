package dto;

import java.sql.Timestamp;

public class Message {
	private Object id;
	private Integer user_id;
	private Integer photoOwnerId;
	private String name;
	private String url_m;
	private Integer user_bid;
	private Integer photo_bid;
	private Integer status;
	private String context;
	private Integer is_accusation;
	private Timestamp signup_date;

	public Integer getPhotoOwnerId() {
		return photoOwnerId;
	}

	public void setPhotoOwnerId(Integer photoOwnerId) {
		this.photoOwnerId = photoOwnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUrl_m() {
		return url_m;
	}
	public void setUrl_m(String url_m) {
		this.url_m = url_m;
	}
	public Integer getUser_bid() {
		return user_bid;
	}
	public void setUser_bid(Integer user_bid) {
		this.user_bid = user_bid;
	}
	public Integer getPhoto_bid() {
		return photo_bid;
	}
	public void setPhoto_bid(Integer photo_bid) {
		this.photo_bid = photo_bid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Timestamp getSignup_date() {
		return signup_date;
	}
	public void setSignup_date(Timestamp signup_date) {
		this.signup_date = signup_date;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIs_accusation() {
		return is_accusation;
	}

	public void setIs_accusation(Integer is_accusation) {
		this.is_accusation = is_accusation;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", user_id=" + user_id + ", photoOwnerId="
				+ photoOwnerId + ", name=" + name + ", url_m=" + url_m
				+ ", user_bid=" + user_bid + ", photo_bid=" + photo_bid
				+ ", status=" + status + ", context=" + context
				+ ", signup_date=" + signup_date + "]";
	}
}

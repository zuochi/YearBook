package dto;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import bean.AtNotify;
import bean.Bbs;
import bean.HeadPhoto;
import bean.Photo;
import bean.PrivateLetter;
import bean.User;

public class Message {
	private Integer id;
	private Integer user_id;
	private Integer photoOwnerId;
	private String name;
	private String url_m;
	private Integer user_bid;
	private Integer photo_bid;
	private Integer status;
	private String context;
	private Timestamp signup_date;
	
	public Message() {
		super();
	}

	public Message(Integer id, Integer user_id, String name, String url_m,
			Integer user_bid, Integer photo_bid, Integer status,
			String context, Timestamp signup_date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.url_m = url_m;
		this.user_bid = user_bid;
		this.photo_bid = photo_bid;
		this.status = status;
		this.context = context;
		this.signup_date = signup_date;
	}

	public Message(Integer id, Integer user_id, Integer photoOwnerId,
			String name, String url_m, Integer user_bid, Integer photo_bid,
			Integer status, String context, Timestamp signup_date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.photoOwnerId = photoOwnerId;
		this.name = name;
		this.url_m = url_m;
		this.user_bid = user_bid;
		this.photo_bid = photo_bid;
		this.status = status;
		this.context = context;
		this.signup_date = signup_date;
	}
	
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

	@Override
	public String toString() {
		return "Reply [id=" + id + ", user_id=" + user_id + ", photoOwnerId="
				+ photoOwnerId + ", name=" + name + ", url_m=" + url_m
				+ ", user_bid=" + user_bid + ", photo_bid=" + photo_bid
				+ ", status=" + status + ", context=" + context
				+ ", signup_date=" + signup_date + "]";
	}
}

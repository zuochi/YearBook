package dto;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class IWantTop implements java.io.Serializable {
	private Integer id;
	private Integer photo_id;
	private String url;
	private String url_thumb;
	private Timestamp signup_date;
	private Integer status;
	private Integer is_delete;
	private Integer user_id;
	private String name;
	private String url_m;
	private BigInteger praise_count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(Integer photo_id) {
		this.photo_id = photo_id;
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
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl_m() {
		return url_m;
	}
	public void setUrl_m(String url_m) {
		this.url_m = url_m;
	}
	public BigInteger getPraise_count() {
		return praise_count;
	}
	public void setPraise_count(BigInteger praise_count) {
		this.praise_count = praise_count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl_thumb() {
		return url_thumb;
	}
	public void setUrl_thumb(String url_thumb) {
		this.url_thumb = url_thumb;
	}
}
package dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import bean.PhotoAlbum;
import bean.User;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class SearchUserInfo implements java.io.Serializable {

	// Fields

	private int id;
	private String name;
	private Integer sex;
	private String sign;
	private String home_town;
	private String year;
	private String professionName;
	private String url_m;
	private String url_l;
	private BigInteger post;
	private BigInteger following;
	private BigInteger follower;
	
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getHome_town() {
		return home_town;
	}
	public void setHome_town(String home_town) {
		this.home_town = home_town;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getUrl_m() {
		return url_m;
	}
	public void setUrl_m(String url_m) {
		this.url_m = url_m;
	}
	public String getUrl_l() {
		return url_l;
	}
	public void setUrl_l(String url_l) {
		this.url_l = url_l;
	}
	public BigInteger getPost() {
		return post;
	}
	public void setPost(BigInteger post) {
		this.post = post;
	}
	public BigInteger getFollowing() {
		return following;
	}
	public void setFollowing(BigInteger following) {
		this.following = following;
	}
	public BigInteger getFollower() {
		return follower;
	}
	public void setFollower(BigInteger follower) {
		this.follower = follower;
	}
	
}
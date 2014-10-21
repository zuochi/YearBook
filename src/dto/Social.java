package dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import bean.PhotoAlbum;
import bean.User;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Social implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String name;
	private String headPhoto;
	private String urlThumb1;
	private String urlThumb2;
	private String urlThumb3;
	private String urlThumb4;
	private String urlThumb5;
	private Timestamp uploadDate;

	// Constructors

	/** default constructor */
	public Social() {
	}

	/** full constructor */
	public Social(Integer id, Integer userId, String name, String headPhoto,
			String urlThumb1, String urlThumb2, String urlThumb3,
			String urlThumb4, String urlThumb5, Timestamp uploadDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.headPhoto = headPhoto;
		this.urlThumb1 = urlThumb1;
		this.urlThumb2 = urlThumb2;
		this.urlThumb3 = urlThumb3;
		this.urlThumb4 = urlThumb4;
		this.urlThumb5 = urlThumb5;
		this.uploadDate = uploadDate;
	}

	// Property accessors
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	public String getUrlThumb1() {
		return urlThumb1;
	}

	public void setUrlThumb1(String urlThumb1) {
		this.urlThumb1 = urlThumb1;
	}

	public String getUrlThumb2() {
		return urlThumb2;
	}

	public void setUrlThumb2(String urlThumb2) {
		this.urlThumb2 = urlThumb2;
	}

	public String getUrlThumb3() {
		return urlThumb3;
	}

	public void setUrlThumb3(String urlThumb3) {
		this.urlThumb3 = urlThumb3;
	}

	public String getUrlThumb4() {
		return urlThumb4;
	}

	public void setUrlThumb4(String urlThumb4) {
		this.urlThumb4 = urlThumb4;
	}

	public String getUrlThumb5() {
		return urlThumb5;
	}

	public void setUrlThumb5(String urlThumb5) {
		this.urlThumb5 = urlThumb5;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}
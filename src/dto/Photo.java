package dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import bean.PhotoAlbum;
import bean.User;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Photo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer photoAlbumId;
	private String name;
	private String url;
	private String urlThumb;
	private Timestamp uploadDate;
	private Integer isAccusation;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Photo() {
	}

	/** minimal constructor */
	public Photo(int userId, String name, String url, String urlThumb,Timestamp uploadDate) {
		this.userId = userId;
		this.name = name;
		this.url = url;
		this.urlThumb = urlThumb;
		this.uploadDate = uploadDate;
	}

	/** full constructor */
	public Photo(Integer id, Integer userId, Integer photoAlbumId, String name,
			String url, String urlThumb, Timestamp uploadDate,
			Integer isAccusation, Integer isDelete) {
		this.id = id;
		this.userId = userId;
		this.photoAlbumId = photoAlbumId;
		this.name = name;
		this.url = url;
		this.urlThumb = urlThumb;
		this.uploadDate = uploadDate;
		this.isAccusation = isAccusation;
		this.isDelete = isDelete;
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

	public Integer getPhotoAlbumId() {
		return photoAlbumId;
	}

	public void setPhotoAlbumId(Integer photoAlbumId) {
		this.photoAlbumId = photoAlbumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlThumb() {
		return urlThumb;
	}

	public void setUrlThumb(String urlThumb) {
		this.urlThumb = urlThumb;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	//配合数据库用的GET SET方法
	public Integer getUser_id() {
		return userId;
	}

	public void setUser_id(Integer userId) {
		this.userId = userId;
	}

	public Integer getAlbum_id() {
		return photoAlbumId;
	}

	public void setAlbum_id(Integer photoAlbumId) {
		this.photoAlbumId = photoAlbumId;
	}

	public String getUrl_thumb() {
		return urlThumb;
	}

	public void setUrl_thumb(String urlThumb) {
		this.urlThumb = urlThumb;
	}

	public Timestamp getUpload_date() {
		return uploadDate;
	}

	public void setUpload_date(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getIs_delete() {
		return isDelete;
	}

	public void setIs_delete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIs_accusation() {
		return isAccusation;
	}

	public void setIs_accusation(Integer isAccusation) {
		this.isAccusation = isAccusation;
	}
	
}
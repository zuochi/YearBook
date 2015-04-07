package bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Photo implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private PhotoAlbum photoAlbum;
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
	public Photo(User user, String name, String url,String urlThumb, Timestamp uploadDate) {
		this.user = user;
		this.name = name;
		this.url = url;
		this.urlThumb = urlThumb;
		this.uploadDate = uploadDate;
	}

	/** full constructor */
	public Photo(Integer id, User user, PhotoAlbum photoAlbum, String name,
			String url, String urlThumb, Timestamp uploadDate,
			Integer isAccusation, Integer isDelete) {
		this.id = id;
		this.user = user;
		this.photoAlbum = photoAlbum;
		this.name = name;
		this.url = url;
		this.urlThumb = urlThumb;
		this.uploadDate = uploadDate;
		this.isAccusation = isAccusation;
		this.isDelete = isDelete;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PhotoAlbum getPhotoAlbum() {
		return this.photoAlbum;
	}

	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
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
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsAccusation() {
		return isAccusation;
	}

	public void setIsAccusation(Integer isAccusation) {
		this.isAccusation = isAccusation;
	}

}
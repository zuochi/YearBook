package dto;

import java.util.HashSet;
import java.util.Set;

/**
 * PhotoAlbum entity. @author MyEclipse Persistence Tools
 */

public class PhotoAlbum implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String name;
	private String photoUrl;
	private Integer permission;
	private String password;

	// Constructors

	/** default constructor */
	public PhotoAlbum() {
	}

	/** minimal constructor */
	public PhotoAlbum(String name) {
		this.name = name;
	}

	/** full constructor */
	public PhotoAlbum(Integer id,Integer userId, String name, String photoUrl,
			Integer permission, String password) {
		this.userId = userId;
		this.name = name;
		this.photoUrl = photoUrl;
		this.permission = permission;
		this.password = password;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getPermission() {
		return this.permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
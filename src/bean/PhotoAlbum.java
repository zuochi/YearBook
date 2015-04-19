package bean;

/**
 * PhotoAlbum entity. @author MyEclipse Persistence Tools
 */

public class PhotoAlbum implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String name;
	private String photoUrl;
	private Integer permission;
	private String password;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public PhotoAlbum() {
	}

	/** minimal constructor */
	public PhotoAlbum(String name) {
		this.name = name;
	}

	/** full constructor */
	public PhotoAlbum(User user, String name, String photoUrl,
			Integer permission, String password, Integer isDelete) {
		this.user = user;
		this.name = name;
		this.photoUrl = photoUrl;
		this.permission = permission;
		this.password = password;
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

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
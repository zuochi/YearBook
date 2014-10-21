package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * HeadPhoto entity. @author MyEclipse Persistence Tools
 */

public class HeadPhoto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String urlS;
	private String urlM;
	private String urlL;
	private String urlO;
	private Integer isDelete;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public HeadPhoto() {
	}

	/** minimal constructor */
	public HeadPhoto(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public HeadPhoto(Integer id, String urlS, String urlM, String urlL,
			String urlO, Integer isDelete, Set users) {
		this.id = id;
		this.urlS = urlS;
		this.urlM = urlM;
		this.urlL = urlL;
		this.urlO = urlO;
		this.isDelete = isDelete;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlS() {
		return this.urlS;
	}

	public void setUrlS(String urlS) {
		this.urlS = urlS;
	}

	public String getUrlM() {
		return this.urlM;
	}

	public void setUrlM(String urlM) {
		this.urlM = urlM;
	}

	public String getUrlL() {
		return this.urlL;
	}

	public void setUrlL(String urlL) {
		this.urlL = urlL;
	}

	public String getUrlO() {
		return this.urlO;
	}

	public void setUrlO(String urlO) {
		this.urlO = urlO;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}
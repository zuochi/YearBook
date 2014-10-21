package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * SchoolYear entity. @author MyEclipse Persistence Tools
 */

public class SchoolYear implements java.io.Serializable {

	// Fields

	private Integer id;
	private String year;
	private Integer isDelete;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public SchoolYear() {
	}

	/** minimal constructor */
	public SchoolYear(String year,Integer isDelete) {
		this.year = year;
		this.isDelete = isDelete;
	}

	/** full constructor */
	public SchoolYear(String year,Integer isDelete,Set users) {
		this.year = year;
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

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
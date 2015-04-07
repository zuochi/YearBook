package bean;

/**
 * SchoolYear entity. @author MyEclipse Persistence Tools
 */

public class SchoolYear implements java.io.Serializable {

	// Fields

	private Integer id;
	private String year;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public SchoolYear() {
	}

	/** full constructor */
	public SchoolYear(String year,Integer isDelete) {
		this.year = year;
		this.isDelete = isDelete;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
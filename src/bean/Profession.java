package bean;

/**
 * Profession entity. @author MyEclipse Persistence Tools
 */

public class Profession implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String context;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Profession() {
	}

	/** minimal constructor */
	public Profession(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Profession(Integer id, String name, String context,
			Integer isDelete) {
		this.id = id;
		this.name = name;
		this.context = context;
		this.isDelete = isDelete;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
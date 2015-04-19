package dto;

public class AnnouncementAndCover {
	private dto.IWantTop i_want_top;
	private String title;
	private String context;
	private Integer user_id;
	private String name;
	
	public dto.IWantTop getI_want_top() {
		return i_want_top;
	}
	public void setI_want_top(dto.IWantTop i_want_top) {
		this.i_want_top = i_want_top;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

package action.admin;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CHW;
import util.PageController;
import bean.Announcement;

@Controller
@Scope("prototype")
public class GetAnnouncements extends AdminAction{
	private Announcement announcement;
	
	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(this.announcement!=null){
			chw.setCondition("id", this.announcement.getId(), CHW.opr_like);
			chw.setCondition("title", this.announcement.getTitle(), CHW.opr_like);
			chw.setCondition("context", this.announcement.getContext(), CHW.opr_like);
			chw.setCondition("user.id", this.announcement.getUser().getId(), CHW.opr_equal);
			chw.setCondition("user.name", this.announcement.getUser().getName(), CHW.opr_like);
			chw.setCondition("top", this.announcement.getUser().getName(), CHW.opr_equal);
			chw.setCondition("topDate", this.announcement.getTopDate(), CHW.opr_equal);
			chw.setCondition("topId", this.announcement.getUser().getName(), CHW.opr_equal);
			chw.setCondition("isDelete", this.announcement.getIsDelete(), CHW.opr_equal);
		}else{
			this.announcement = new Announcement();
			this.announcement.setIsDelete(0);
			chw.setCondition("isDelete", this.announcement.getIsDelete(), CHW.opr_equal);
		}
		
		Integer announcementsCount = (Integer) service.getObjectByHql("select count(id) from Announcement " + chw.getSqlWhere(), "getInteger");
		PageController pc = new PageController(announcementsCount, 1, pageSize);
		pc.setCurrentPage(toPage);
		
		List<Announcement> announcements = service.getObjectsByHql("from Announcement " + chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"signupDate") + " " + (desc!=null?desc:""),pc,"getUser");
		
		request.setAttribute("objs", announcements);
		request.setAttribute("params", this.announcement);
		request.setAttribute("pc", pc);
		request.setAttribute("pageSize", this.pageSize);
		
		return "admin_getAnnouncements";
	}

	@JSON(serialize=false)
	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

}

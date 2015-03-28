package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Announcement;

@Controller
@Scope("prototype")
public class DeleteAnnouncementA extends AdminAction {
	private Announcement announcement;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			announcement = (Announcement) service.getObjectByHql("from Announcement where isDelete=0 and id='" + announcement.getId()+"'");
			if(announcement!=null){
				announcement.setIsDelete(1);
				if (service.updateObject(announcement)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@JSON(serialize=false)
	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

}

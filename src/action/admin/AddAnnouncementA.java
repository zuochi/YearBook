package action.admin;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Announcement;
import bean.User;

@Controller
@Scope("prototype")
public class AddAnnouncementA extends AdminAction {
	private Announcement announcement;
	
	@Override
	public String execute() {
		try {
			User user = (User) session.get("user");
			
			//TODO 过滤器
			if(user==null){
				return "login";
			}
			
			out = response.getWriter();
			
			if(announcement!=null){
				announcement.setUser(user);
				announcement.setSignupDate(new Timestamp(System.currentTimeMillis()));
				if (service.saveObject(announcement)) {
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

package action.admin;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Announcement;
import bean.Profession;
import bean.User;

@Controller
@Scope("prototype")
public class EditAnnouncement extends AdminAction {
	private Announcement announcement;
	
	@Override
	public String execute() {
		try {
			User user = (User) session.get("user");
			
			//TODO 过滤器
			if(user==null){
				return "login";
			}
			
			//判断是否使用旧密码
			out = response.getWriter();
			
			if(announcement!=null){
				announcement.setUser(user);
				announcement.setSignupDate(new Timestamp(System.currentTimeMillis()));
				if (service.updateObject(announcement)) {
					out.print("success");
				} else {
					out.print("fail");
				}
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String overhead() {
		try {
			User user = (User) session.get("user");
			
			//TODO 过滤器
			if(user==null){
				return "login";
			}
			
			//判断是否使用旧密码
			out = response.getWriter();
			
			announcement = (Announcement) service.getObjectByHql("from Announcement where id="+announcement.getId());
			
			if(announcement!=null){
				//重置顶置时间
				announcement.setTopDate(new Timestamp(System.currentTimeMillis()));
				if (service.updateObject(announcement)) {
					out.print("success");
				} else {
					out.print("fail");
				}
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
	
	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

}

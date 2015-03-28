package action.admin;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Announcement;

@Controller
@Scope("prototype")
public class GetAnnouncementDetail extends AdminAction{
	private Announcement announcement;
	
	@Override
	public String execute() {
		announcement = (Announcement) service.getObjectByHql("from Announcement where id="+announcement.getId(),"getUser");

		try {
			out = response.getWriter();
		
			if(announcement!=null){
				dto.Announcement announcementDTO = (dto.Announcement) new dto.Announcement(
																			announcement.getId(),
																			announcement.getUser().getId(),
																			announcement.getUser().getName(),
																			announcement.getTitle(),announcement.getContext(),
																			announcement.getTop(),announcement.getTopId(),
																			announcement.getSignupDate(),
																			announcement.getIsDelete());
				JSONObject json = JSONObject.fromObject(announcementDTO);
				out.print(json);
			}else{
				out.print("error");
			}
			
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		return null;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

}
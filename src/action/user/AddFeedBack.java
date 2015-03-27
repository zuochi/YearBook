package action.user;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import action.admin.AdminAction;
import bean.FeedBack;
import bean.Profession;
import bean.User;

@Controller
@Scope("prototype")
public class AddFeedBack extends AdminAction {
	private FeedBack feedBack;
	
	@Override
	public String execute() {
		try {
			User user = (User) session.get("user");
			
			out = response.getWriter();
			
			if(feedBack!=null && user!=null){
				feedBack.setUser(user);
				feedBack.setStatus(0);
				feedBack.setSignupDate(new Timestamp(System.currentTimeMillis()));
				feedBack.setIsDelete(0);
				
				if (service.saveObject(feedBack)) {
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
	public FeedBack getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(FeedBack feedBack) {
		this.feedBack = feedBack;
	}
}

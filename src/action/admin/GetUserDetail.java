package action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;
import bean.SchoolYear;
import bean.User;

@Controller
@Scope("prototype")
public class GetUserDetail extends AdminAction{
	private User user;
	
	@Override
	public String execute() {
		user = (User) service.getObjectByHql("from User u where u.id="+user.getId(),"getHeadPhoto","getProfession");
		
		Profession professions = (Profession) service.getObjectByHql("from Profession p order by id");
		SchoolYear schoolYears = (SchoolYear) service.getObjectByHql("from SchoolYear p order by year desc");
		request.setAttribute("obj", user);
		request.setAttribute("professions", professions);
		request.setAttribute("schoolYears", schoolYears);
		
		return "report_getUserDetail";
	}

	public void setUser(User user) {
		this.user = user;
	}
}

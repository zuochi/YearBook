package action.admin;

import java.util.List;

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
		
		List<Profession> professions = service.getObjectsByHql("from Profession p order by id",null);
		List<SchoolYear> schoolYears = service.getObjectsByHql("from SchoolYear p order by year desc",null);
		request.setAttribute("obj", user);
		request.setAttribute("professions", professions);
		request.setAttribute("schoolYears", schoolYears);
		
		return "admin_getUserDetail";
	}

	public void setUser(User user) {
		this.user = user;
	}
}

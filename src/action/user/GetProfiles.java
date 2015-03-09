package action.user;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;
import bean.SchoolYear;
import bean.User;

@Controller
@Scope("prototype")
public class GetProfiles extends UserAction{
	@Override
	public String execute() {
		user = (User) request.getSession().getAttribute("user");
		
		if(!isLogin(user)){
			return "login";
		}
		
		List<Profession> professions = service.getObjectsByHql("from Profession",null);
		List<SchoolYear> schoolYears = service.getObjectsByHql("from SchoolYear order by year desc",null);
		request.setAttribute("professions", professions);
		request.setAttribute("schoolYears", schoolYears);
		return "profile";
	}
}

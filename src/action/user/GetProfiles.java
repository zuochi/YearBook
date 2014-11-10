package action.user;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;
import bean.SchoolYear;

@Controller
@Scope("prototype")
public class GetProfiles extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		List<Profession> professions = service.getObjectsByHql("from Profession",null);
		List<SchoolYear> schoolYears = service.getObjectsByHql("from SchoolYear order by year desc",null);
		session.put("professions", professions);
		session.put("schoolYears", schoolYears);
		return "profile";
	}
}

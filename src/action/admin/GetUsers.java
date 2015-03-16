package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;
import util.PageController;

@Controller
@Scope("prototype")
public class GetUsers extends AdminAction{

	@Override
	public String execute() {
		/*if(!isLogin(user)){
			return "login";
		}*/
		Integer replysCount = (Integer) service.getObjectByHql("select count(u.id) from User u", "getInteger");
		PageController pc = new PageController(replysCount, 1,20);
		pc.setCurrentPage(toPage);
		
		List<User> users = service.getObjectsByHql("from User u order by u.signupDate desc", pc);
		request.setAttribute("objs", users);
		request.setAttribute("pc", pc);
		
		return "report_getUsers";
	}
}

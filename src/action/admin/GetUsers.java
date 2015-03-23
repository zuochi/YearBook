package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;
import util.PageController;

@Controller
@Scope("prototype")
public class GetUsers extends AdminAction{
	private User user;

	@Override
	public String execute() {
		
		Integer replysCount = (Integer) service.getObjectByHql("select count(u.id) from User u", "getInteger");
		PageController pc = new PageController(replysCount, 1,20);
		pc.setCurrentPage(toPage);
		
		List<User> users = service.getObjectsByHql("from User u order by u.signupDate desc", pc,"getHeadPhoto","getProfession");
		request.setAttribute("objs", users);
		request.setAttribute("pc", pc);
		
		return "report_getUsers";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private String consturtSqlWhere(){
		return null;
	}
}

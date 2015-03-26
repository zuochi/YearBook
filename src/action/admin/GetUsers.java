package action.admin;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Profession;
import bean.SchoolYear;
import bean.User;
import util.CHW;
import util.PageController;

@Controller
@Scope("prototype")
public class GetUsers extends AdminAction{
	private User user;

	@Override
	public String execute() {
		CHW chw = new CHW();
		
		if(user!=null){
			chw.setCondition("user.id", user.getId(), CHW.opr_equal);
			chw.setCondition("user.userName", user.getUserName(), CHW.opr_like);
			chw.setCondition("user.name", user.getName(), CHW.opr_like);
			chw.setCondition("user.sex", user.getSex(), CHW.opr_equal);
			chw.setCondition("user.isDelete", user.getIsDelete(), CHW.opr_equal);
			chw.setCondition("user.profession.id", user.getProfession().getId(),  CHW.opr_equal);
		}
		
		Integer replysCount = (Integer) service.getObjectByHql("select count(user.id) from User user "+chw.getSqlWhere(), "getInteger");
		PageController pc = new PageController(replysCount, 1,pageSize);
		pc.setCurrentPage(toPage);

		List<User> users = service.getObjectsByHql("from User user "+chw.getSqlWhere() + " order by " + (orderBy!=null?orderBy:"user.signupDate") + " " + (desc!=null?desc:""), pc,"getHeadPhoto","getProfession","getSchoolYear");
		List<Profession> professions = service.getObjectsByHql("from Profession where isDelete=0",null);
		List<SchoolYear> schoolYears = service.getObjectsByHql("from SchoolYear where isDelete=0 order by year desc",null);
		
		request.setAttribute("objs", users);
		request.setAttribute("pc", pc);
		request.setAttribute("params", user);
		request.setAttribute("professions", professions);
		request.setAttribute("schoolYears", schoolYears);
		request.setAttribute("pageSize", pageSize);
		
		return "admin_getUsers";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

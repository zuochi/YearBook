package action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;

@Controller
@Scope("prototype")
public class LoginA extends AdminAction{
	private User user;

	@Override
	public String execute() {
		if(user!=null){
			user = (User) service.getObjectByHql(
					"from User where isDelete=0 and isAdmin=1 and userName='"+user.getUserName()+"' and password='" + user.getPassword() +"'","getHeadPhoto");
			
			if(user!=null){
				session.put("user", user);
				session.put("headPhoto", user.getHeadPhoto());
				return "loginSuccess";
			}
		}
		
		request.setAttribute("result", "username or password incorrect.");
		
		return "login";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

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
			user = (User) service.getObjectByHql("from User where userName='"+user.getUserName()+"' and password='" + user.getPassword() +"'");
			
			if(user!=null){
				session.put("user", user);
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

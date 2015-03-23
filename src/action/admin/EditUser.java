package action.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;

@Controller
@Scope("prototype")
public class EditUser extends AdminAction {
	private User user;
	
	@Override
	public String execute() {
		try {
			//判断是否使用旧密码
			out = response.getWriter();
			if (service.updateUser(user)) {
				out.print("success");
				session.put("user", user);
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}

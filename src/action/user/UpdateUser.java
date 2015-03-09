package action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;

@Controller
@Scope("prototype")
public class UpdateUser extends UserAction {
	@Override
	public String execute() {
		try {
			//判断是否使用旧密码
			if (request.getParameter("user.password") != "") {
				user.setPassword(request.getParameter("user.password"));
			} else {
				user.setPassword(request.getParameter("user.passwordOld"));
			}
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
	
	public String updateSign() throws Exception{
		out = response.getWriter();
		if(service.updateUserSign(user)){
			String sign = user.getSign();
			out.print("success");
			user = (User) request.getSession().getAttribute("user");
			user.setSign(sign);
			session.put("user", user);
		}else{
			out.print("fail");
		}
		out.flush();
		out.close();
		return null;
	}
}

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
			
			User userOld = (User) session.get("user");
			
			user.setIsDelete(0);
			user.setIsAdmin(userOld.getIsAdmin());
			
			if (service.updateUser(user)) {
				out.print("success");
				user = (User) service.getObjectByHql("from User where isDelete=0 and id="+user.getId(), "getHeadPhoto", "getProfession");
				session.put("user", user);
				session.put("headPhoto", user.getHeadPhoto());
				session.put("profession", user.getProfession());
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

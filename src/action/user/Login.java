package action.user;

import javax.servlet.http.Cookie;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CookieUtils;
import bean.User;

@Controller
@Scope("prototype")
public class Login extends UserAction{
	@Override
	public String execute() throws Exception {
		try {
			out = response.getWriter();
			user = (User) service.getObjectByHql("from User where isDelete=0 and userName='"+userName+"'", "getHeadPhoto", "getProfession");
			if (user != null && password.equals(user.getPassword())) {
				if ("on".equals(auto_login)) {
					CookieUtils cookieUtils = new CookieUtils();
					Cookie cookie = (Cookie) cookieUtils.addCookie(user);
					response.addCookie(cookie);
				}
				// 放置数据
				session.put("user", user);
				session.put("headPhoto", user.getHeadPhoto());
				session.put("profession", user.getProfession());
				// 更新登陆时间
				service.updateUserLoginTime(user);
				
				//如果用户名大于10，说明用户还没有修改资料
				if(user.getName().length()>10){
					out.print("profile");
				}else{
					out.print("success");
				}
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
}

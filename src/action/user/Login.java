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
		// TODO Auto-generated method stub
		try {
			out = response.getWriter();
			user = (User) service.getObjectByHql("from User where isDelete=0 and userName='"+userName+"'", "getHeadPhoto");
			if (user != null) {
				if (password.equals(user.getPassword())) {
					if ("on".equals(auto_login)) {
						CookieUtils cookieUtils = new CookieUtils();
						Cookie cookie = (Cookie) cookieUtils.addCookie(user);
						response.addCookie(cookie);
					}
					// 放置数据
					session.put("user", user);
					session.put("headPhoto", user.getHeadPhoto());
					// 更新登陆时间
					service.updateUserLoginTime(user);
					out.print("success");
				} else {
					out.print("fail");
				}
			} else {
				out.print("fail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
}

package action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CookieUtils;

@Controller
@Scope("prototype")
public class AutoLogin extends UserAction{
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		CookieUtils cookieUtils = new CookieUtils();
		if (cookieUtils.getCookie(request, service, session)) {
			return "autoLogin";
		} else
			return "login";
	}
	
}

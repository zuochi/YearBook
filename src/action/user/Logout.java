package action.user;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CookieUtils;

@Controller
@Scope("prototype")
public class Logout extends UserAction {
	@Override
	public String execute() {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			session.removeAttribute("headPhoto");
		}
		this.session.remove("user");
		this.session.remove("headPhoto");
		CookieUtils cookieUtils = new CookieUtils();
		Cookie cookie = cookieUtils.delCookie(request);
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		return "logout";
	}
}

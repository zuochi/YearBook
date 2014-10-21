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
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			session.removeAttribute("professions");
			session.removeAttribute("headPhoto");
			session.removeAttribute("schoolYears");
			session.removeAttribute("photoAlbumscount");
			session.removeAttribute("photoAlbums");
			session.removeAttribute("photoAlbumsAll");
			session.removeAttribute("photos");
			session.removeAttribute("photosPc");
			session.removeAttribute("photoAlbumPc");
		}
		this.session.remove("user");
		this.session.remove("professions");
		this.session.remove("headPhoto");
		this.session.remove("schoolYears");
		this.session.remove("photoAlbumscount");
		this.session.remove("photoAlbums");
		this.session.remove("photoAlbumsAll");
		this.session.remove("photos");
		this.session.remove("photosPc");
		this.session.remove("photoAlbumPc");
		CookieUtils cookieUtils = new CookieUtils();
		Cookie cookie = cookieUtils.delCookie(request);
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		try {
			response.sendRedirect("/YearBook/index.jsp");// 自动转跳到主页
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "logout";
	}
}

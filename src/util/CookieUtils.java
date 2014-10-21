package util;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import service.Service;
import bean.HeadPhoto;
import bean.User;

/**
 * cookie的增加、删除、查询
 */
public class CookieUtils{
	public static final String USER_COOKIE = "user.cookie";
	// 添加一个cookie
	public Cookie addCookie(User user) {
		Cookie cookie = new Cookie(USER_COOKIE, (user.getUserName() + "-" + user.getPassword()));
		//System.out.println("添加cookie");
		cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
		return cookie;
	}

	// 得到cookie
	public boolean getCookie(HttpServletRequest request,Service service,Map<String, Object> session) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split("-");
						String username = "";
						String password = "";
						for(int i=0 ; i<split.length ; i++){
							if(i==0){
								username = split[i];
							}else{
								password = split[i];
							}
						}
						Properties pro = new Properties();
						pro.setProperty("userName", username);
						User user = (User) service.getObjectByProperties(pro, new User());
						if (user != null) {
							if(password.equals(user.getPassword())){
								pro = new Properties();
								pro.setProperty("id", user.getHeadPhoto().getId().toString());
								HeadPhoto headPhoto = (HeadPhoto) service.getObjectByProperties(pro, new HeadPhoto());
								session.put("user", user);
								session.put("headPhoto", headPhoto);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	// 删除cookie
	public Cookie delCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (USER_COOKIE.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
	}

}

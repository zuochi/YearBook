package action.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.CommonUtils;
import bean.HeadPhoto;
import bean.User;

@Controller
@Scope("prototype")
public class Register extends UserAction {
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			// 初始化headPhoto
			String uuid = CommonUtils.createUUID();
			user.setName(uuid);
			user.setLastLogintime(new Timestamp(System.currentTimeMillis()));
			user.setSignupDate(new Timestamp(System.currentTimeMillis()));
			user.setIsAdmin(0);
			user.setIsDelete(0);
			this.userName = user.getUserName();
			this.password = user.getPassword();
			Properties pro = new Properties();
			pro.setProperty("userName", userName);
			if (service.isDuplicationOfProperties(pro, new User()) == false) {// 验证用户名是否有重复
				// 初始化头像
				HeadPhoto headPhoto = new HeadPhoto();
				headPhoto.setId(uuid);
				headPhoto.setIsDelete(0);
				service.saveObject(headPhoto);
				user.setHeadPhoto(headPhoto);
				if (service.saveObject(user)) {
					Login login = new Login();
					login.setUserName(userName);
					login.setPassword(password);
					login.setService(service);
					login.setSession(session);
					try {
						login.execute();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					out.print("fail");
				}
			} else {
				out.print("fail");
			}
		} catch (IOException e) {
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
	
}

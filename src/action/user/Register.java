package action.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.HeadPhoto;
import bean.User;

@Controller
@Scope("prototype")
public class Register extends UserAction {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			out = response.getWriter();
			// 初始化headPhoto
			int random;
			while (true) {
				random = (int) (Math.random() * 90000000 + 10000000);
				Properties pro = new Properties();
				pro.setProperty("name", random + "");
				if (service.isDuplicationOfProperties(pro, new User()) == false) {// 保证不重复
					user.setName(random + "");
					break;
				}
			}
			user.setPassword(request.getParameter("user.password"));
			user.setLastLogintime(new Timestamp(System.currentTimeMillis()));
			user.setSignupDate(new Timestamp(System.currentTimeMillis()));
			user.setIsDelete(0);
			this.userName = user.getUserName();
			this.password = user.getPassword();
			Properties pro = new Properties();
			pro.setProperty("userName", userName);
			if (service.isDuplicationOfProperties(pro, new User()) == false) {// 双重验证用户名是否有重复
				// 初始化头像
				HeadPhoto headPhoto = new HeadPhoto();
				headPhoto.setId(random);
				headPhoto.setIsDelete(0);
				service.saveObject(headPhoto);
				user.setHeadPhoto(headPhoto);
				if (service.saveObject(user)) {
					Login login = new Login();
					login.setUserName(userName);
					login.setPassword(password);
					login.setService(service);
					login.setSession(session);
					login.execute();
				} else {
					out.print("fail");
				}
			} else {
				out.print("fail");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			out.print("fail");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return "ajax";
	}
	
}

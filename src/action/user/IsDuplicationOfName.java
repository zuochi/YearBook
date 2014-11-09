package action.user;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.User;

@Controller
@Scope("prototype")
public class IsDuplicationOfName extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			out = response.getWriter();
			Properties pro = new Properties();
			pro.setProperty("name", request.getParameter("name"));
			if (service.isDuplicationOfProperties(pro, new User())) {
				out.print("used");
			} else {
				out.print("ok");
			}
		} catch (IOException e) {
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

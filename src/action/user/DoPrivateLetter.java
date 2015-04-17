package action.user;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.PrivateLetter;
import bean.User;

@Controller
@Scope("prototype")
public class DoPrivateLetter extends UserAction{
	private PrivateLetter privateLetter;
	
	@Override
	public String execute() {
		//当前用户
		user = (User) request.getSession().getAttribute("user");
		
		try {
			out=response.getWriter();
		
			//给私信设置状态
			privateLetter.setUserByUserId(user);
			privateLetter.setSignupDate(new Timestamp(System.currentTimeMillis()));
			privateLetter.setIsDelete(0);
			privateLetter.setStatus(0);
			
			if(service.saveObject(privateLetter)){
				out.print("success");
			}else{
				out.print("fail");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		
		return null;
	}

	public PrivateLetter getPrivateLetter() {
		return privateLetter;
	}

	public void setPrivateLetter(PrivateLetter privateLetter) {
		this.privateLetter = privateLetter;
	}

}

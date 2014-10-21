package action.user;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import action.user.UserAction;

@Controller
@Scope("prototype")
public class GetReply extends UserAction{
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
}

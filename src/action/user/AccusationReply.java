package action.user;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Reply;

@Controller
@Scope("prototype")
public class AccusationReply extends UserAction{
	private Reply reply;
	
	@Override
	public String execute() {
		reply = (Reply) service.getObjectByHql("from Reply where isDelete=0 and id='" + reply.getId()+"'");
		reply.setIsAccusation(1);
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(service.updateObject(reply)){
			out.print("success");
		}else{
			out.print("fail");
		}
		
		return null;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
}

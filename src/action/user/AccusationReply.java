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
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		reply = (Reply) service.getObjectByHql("from Reply where isDelete=0 and id='" + reply.getId()+"'");
		if(reply.getIsAccusation()!=null && reply.getIsAccusation()==0){
			reply.setIsAccusation(1);

			if(service.updateObject(reply)){
				out.print("success");
			}else{
				out.print("fail");
			}
		}else{
			out.print("repeat");
		}
		
		out.flush();
		out.close();
		return null;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
}

package action.admin;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.Reply;

@Controller
@Scope("prototype")
public class RecoveryReplyA extends AdminAction {
	private Reply reply;
	
	@Override
	public String execute() {
		try {
			out = response.getWriter();
			reply = (Reply) service.getObjectByHql("from Reply where isDelete=1 and id='" + reply.getId()+"'");
			if(reply!=null){
				reply.setIsDelete(0);
				if (service.updateObject(reply)) {
					out.print("success");
				}else {
					out.print("fail");
				}
			}else {
				out.print("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@JSON(serialize=false)
	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
}

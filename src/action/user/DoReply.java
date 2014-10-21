package action.user;

import java.util.Date;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import util.AnalyzeStringUtils;

import bean.Reply;
import bean.User;

@Controller
@Scope("prototype")
public class DoReply extends UserAction{
	private Reply reply;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//当前用户
		User currentUser = (User) request.getSession().getAttribute("user");
		reply.setUserByUserId(currentUser);
		//先解评论好友，并为好友附上链接
		reply.setContext(AnalyzeStringUtils.analyzeFriendUrl(reply.getContext(),service));
		reply.setSignupDate(new Date());
		reply.setStatus(0);
		reply.setIsDelete(0);
		out = response.getWriter();
		if(service.saveObject(reply)){
			out.print("success");
			//返回成功并通知好友
		}else{
			out.print("fail");
		}
		out.flush();
		out.close();
		return "ajax";
	}
	
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}

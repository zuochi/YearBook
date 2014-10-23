package action.user;

import java.util.Date;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.Service;

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
		reply.setContext(analyzeFriendUrl(reply.getContext(),currentUser,service));
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
	
	public String analyzeFriendUrl(String reply,User currentUser,Service service){
		reply = reply.trim();
		String s = reply;
		String[] friendsList = reply.split("\\s+");
		
		//重置reply
		reply = "";
		
		User friend = null;
		
		for(int i=0 ; i<friendsList.length ; i++){
			try {
				String[] friendsListMiddle = friendsList[i].split("@");
				for(int j=0 ; j<friendsListMiddle.length ; j++){
					if(j==0){
						reply += friendsListMiddle[j];
						continue;
					}
					if(j==(friendsListMiddle.length-1) && !"".equals(friendsListMiddle[j])){
						friend = getUserByName(friendsListMiddle[j],service);
						if(friend!=null){
							reply += "<a href='javascript:goSocialIndex("+friend.getId()+")'>"+"@"+friendsListMiddle[j]+"<a> ";
							noifyFriend(currentUser,friend);
						}else{
							reply += "@" + friendsListMiddle[j] + " " ;
						}
					}else{
						reply += ("@" + friendsListMiddle[j]);
					}
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reply;
	}
	
	private boolean noifyFriend(User currentUser,User friend){
		//提醒用户
		return false;
	}
	
	private static User getUserByName(String name,Service service){
		Properties pro = new Properties();
		pro.setProperty("name", name);
		return (User) service.getObjectByProperties(pro, new User());
	}
	
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
}

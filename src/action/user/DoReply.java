package action.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.hibernate.Hibernate;
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
		reply.setSignupDate(new Timestamp(System.currentTimeMillis()));
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
	
	//解析at到的用户
	public String analyzeFriendUrl(String reply,User currentUser,Service service){
		//去除前后空格
		reply = reply.trim();
		//先按空格分割
		String[] friendsList = reply.split("\\s+");
		//使用StringBuilder重组字符串
		StringBuilder replyStringBuilder = new StringBuilder("");
				
		//重置reply
		//reply = "";
				
		User friend = null;
				
		for(int i=0 ; i<friendsList.length ; i++){
			try {
				String[] friendsListMiddle = friendsList[i].split("@");
				for(int j=0 ; j<friendsListMiddle.length ; j++){
					if(j==0){
						replyStringBuilder.append(friendsListMiddle[j]);
						continue;
					}
					if(j==(friendsListMiddle.length-1) && !"".equals(friendsListMiddle[j])){
						friend = getUserByName(friendsListMiddle[j],service);
						if(friend!=null){
							replyStringBuilder.append("<a href='javascript:goSocialIndex("+friend.getId()+")'>"+"@"+friendsListMiddle[j]+"<a> ");
						}else{
							replyStringBuilder.append("@" + friendsListMiddle[j] + " ");
						}
					}else{
						replyStringBuilder.append(("@" + friendsListMiddle[j]));
					}
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return replyStringBuilder.toString();
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

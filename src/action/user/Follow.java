package action.user;

import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bean.FriendList;
import bean.User;

@Controller
@Scope("prototype")
public class Follow extends UserAction{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		user = (User) request.getSession().getAttribute("user");
		
		Properties followUserPro = new Properties();
		followUserPro.setProperty("id", request.getParameter("userId"));
		User followUser = (User) service.getObjectByProperties(followUserPro, new User());
		
		out = response.getWriter();
		
		if("follow".equals(request.getParameter("type"))){
			//查找是否已经有删除过的记录
			Properties friendListPro = new Properties();
			friendListPro.setProperty("userByUserId.id", user.getId().toString());
			friendListPro.setProperty("userByFriendId.id", followUser.getId().toString());
			FriendList friendList = (FriendList) service.getDelObjectByProperties(friendListPro, new FriendList());
			
			//没有的话就更新
			if(friendList==null){
				if(service.saveObject(new FriendList(user,followUser,0))){
					out.print("success");
				}else{
					out.print("fail");
				}
			//有的话就更新isDelete为1
			}else{
				friendList.setIsDelete(0);
				if(service.updateObject(friendList)){
					out.print("success");
				}else{
					out.print("fail");
				}
			}
		}else if("cancelFollow".equals(request.getParameter("type"))){
			//查找是否已经有记录
			Properties friendListPro = new Properties();
			friendListPro.setProperty("userByUserId.id", user.getId().toString());
			friendListPro.setProperty("userByFriendId.id", followUser.getId().toString());
			FriendList friendList = (FriendList) service.getObjectByProperties(friendListPro, new FriendList());
			friendList.setIsDelete(1);
			try {
				if(service.updateObject(friendList)){
					out.print("success");
				}else{
					out.print("fail");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
		return "ajax";
	}
}

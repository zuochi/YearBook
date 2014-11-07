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
	
		User followUser =new User();
		followUser.setId(Integer.parseInt(request.getParameter("userId")));
		
		out = response.getWriter();
		
		if("follow".equals(request.getParameter("type"))){
			//查找是否已经有删除过的记录
			FriendList friendList = getFriendList(user.getId(),followUser.getId(),true);

			//没有的话就更新
			if(friendList==null){
				System.out.println("new");
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
			FriendList friendList = getFriendList(user.getId(),followUser.getId(),false);
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
	
	private FriendList getFriendList(int userId,int friendId,boolean isDelete){
		return (FriendList) service.getObjectByHql("from FriendList where isDelete="+(isDelete?1:0)+" and userByUserId.id="+userId+" and userByFriendId.id="+friendId);
	}
}

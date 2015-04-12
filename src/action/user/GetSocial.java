package action.user;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dto.FriendInfomation;

import util.PageController;

import bean.FriendList;
import bean.User;

@Controller
@Scope("prototype")
public class GetSocial extends UserAction{
	private boolean isMine = true;
	
	@Override
	public String execute() {
		int socialPhotosCount = (Integer) service.getObjectByHql("select count(*) from Photo where isDelete=0 and user.id="+user.getId(), "getInteger");
		
		//获取社交用户
		User socialUser = (User) service.getObjectByHql("from User where isDelete=0 and id="+user.getId(), "getHeadPhoto","getProfession","getSchoolYear");
		
		//获取当前user
		user = (User) request.getSession().getAttribute("user");
		
		if(!isLogin(user)){
			return "login";
		}
		
		//屏蔽相关属性
		socialUser.setUserName("");
		socialUser.setPassword("");
		
		if(socialUser.getProfession()!=null){
			request.setAttribute("socialProfession", socialUser.getProfession());
		}
		
		if(socialUser.getSchoolYear()!=null){
			request.setAttribute("socialSchoolYear", socialUser.getSchoolYear());
		}
		
		//获取following数量
		int socialFollowingCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByUserId.id="+socialUser.getId(), "getInteger");
		
		//获取followers数量
		int socialFollowersCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByFriendId.id="+socialUser.getId(), "getInteger");
		
		request.setAttribute("socialPhotosCount", socialPhotosCount);
		request.setAttribute("socialUser", socialUser);
		request.setAttribute("socialHeadPhoto", socialUser.getHeadPhoto());
		request.setAttribute("socialFollowingCount", socialFollowingCount);
		request.setAttribute("socialFollowersCount", socialFollowersCount);
		request.setAttribute("relationShip", getRelationship(user.getId(),socialUser.getId()));
		return "social";
	}
	
/*	//获取好友列表
	public String getFriendByPerPage(){
		try {
			List<FriendInfomation> friendInfomationList = null;
			out = response.getWriter();
			
			//获取好友数量
			Integer friendAmmount = (Integer) service.getObjectBySql("select count(*) from friend_list f left join user u on f.user_id = u.id where f.user_id  in (select f.friend_id from friend_list f where f.user_id = "+user.getId()+") and f.friend_id = "+user.getId(),new Integer(0));
			
			PageController pc = new PageController(friendAmmount,1,20);
			pc.setCurrentPage(toPage);
			
			//获取当前用户的好友信息
			List<User> userFriendList = service.getObjectsBySql("select u.* from friend_list f left join user u on f.user_id = u.id where f.user_id  in (select f.friend_id from friend_list f where f.user_id = "+user.getId()+") and f.friend_id = "+user.getId()+" order by update_date desc",pc,user,"getHeadPhoto");
			
			if(userFriendList.size()>0){
				friendInfomationList = new ArrayList<FriendInfomation>();
				//transform
				for(User friend : userFriendList){
					friendInfomationList.add(new FriendInfomation(friend.getId(),friend.getHeadPhoto().getUrlL(),friend.getName(),friend.getSign()));
				}
				JSONArray json = JSONArray.fromObject(friendInfomationList);
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}*/
	
	//获取关注列表
	public String getFollowingByPerPage(){
		try {
			List<FriendInfomation> friendInfomationList = null;
			out = response.getWriter();
			
			//获取关注数量
			int followingCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByUserId.id="+user.getId(), "getInteger");
			
			PageController pc = new PageController(followingCount,1,20);
			pc.setCurrentPage(toPage);
			
			//获取当前用户的关注信息
			if(toPage>pc.getTotalPages()){
				out.print("false");
			}else{
				List<User> userFriendList = service.getObjectsBySql("select u.* from user u where u.is_delete=0 and u.id in (select f.friend_id from friend_list f where f.is_delete=0 and f.user_id="+user.getId()+" order by f.update_date desc)",pc,user,"getHeadPhoto");
	
				if(userFriendList.size()>0){
					friendInfomationList = new ArrayList<FriendInfomation>();
					//transform
					//判断是否自己的粉丝列表
					if(isMine){
						for(User friend : userFriendList){
							//判断是否互相关注
							if(isFollowed(friend.getId(), user.getId())){
								friendInfomationList.add(new FriendInfomation(friend.getId(),friend.getHeadPhoto().getUrlL(),friend.getName(),friend.getSign(),true));
							}else{
								friendInfomationList.add(new FriendInfomation(friend.getId(),friend.getHeadPhoto().getUrlL(),friend.getName(),friend.getSign(),false));
							}
						}
					}else{
						//获取主用户与改用户的关系
						user = (User) request.getSession().getAttribute("user");
						for(User friend : userFriendList){
							friendInfomationList.add(new FriendInfomation(friend.getId(),friend.getHeadPhoto().getUrlL(),friend.getName(),friend.getSign(),false,getRelationship(user.getId(),friend.getId())));
						}
					}
					JSONArray json = JSONArray.fromObject(friendInfomationList);
					out.print(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	//获取粉丝列表
	public String getFollowersByPerPage(){
		try {
			List<FriendInfomation> friendInfomationList = null;
			out = response.getWriter();
			
			//获取粉丝数量
			int followersCount = (Integer) service.getObjectByHql("select count(*) from FriendList where isDelete=0 and userByFriendId.id="+user.getId(), "getInteger");
			
			PageController pc = new PageController(followersCount,1,20);
			pc.setCurrentPage(toPage);
			
			//获取当前用户的粉丝信息
			if(toPage>pc.getTotalPages()){
				out.print("false");
			}else{
				List<User> userFriendList = service.getObjectsBySql("select u.* from user u where u.is_delete=0 and u.id in (select f.user_id from friend_list f where f.is_delete=0 and f.friend_id="+user.getId()+" order by f.update_date desc)",pc,user,"getHeadPhoto");
				
				if(userFriendList.size()>0){
					friendInfomationList = new ArrayList<FriendInfomation>();
					//transform
					//判断是否自己的粉丝列表
					if(isMine){
						for(User follow : userFriendList){
							//判断是否互相关注
							if(isFollowed(user.getId(),follow.getId())){
								friendInfomationList.add(new FriendInfomation(follow.getId(),follow.getHeadPhoto().getUrlL(),follow.getName(),follow.getSign(),true));
							}else{
								friendInfomationList.add(new FriendInfomation(follow.getId(),follow.getHeadPhoto().getUrlL(),follow.getName(),follow.getSign(),false));
							}
						}
					}else{
						//获取主用户与改用户的关系
						user = (User) request.getSession().getAttribute("user");
						for(User friend : userFriendList){
							friendInfomationList.add(new FriendInfomation(friend.getId(),friend.getHeadPhoto().getUrlL(),friend.getName(),friend.getSign(),false,getRelationship(user.getId(),friend.getId())));
						}
					}
					JSONArray json = JSONArray.fromObject(friendInfomationList);
					out = response.getWriter();
					out.print(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	//判断是否关注
	private boolean isFollowed(int userByUserId,int userByFriendId){
		FriendList isFriendList = (FriendList) service.getObjectByHql("from FriendList where isDelete=0 and userByUserId.id="+userByUserId+" and userByFriendId.id=" + userByFriendId,null);
		if(isFriendList!=null){
			return true;
		}
		return false;
	}
	
	//获取当前user的好友列表,判断与该用户的关系,0:未关注,1:已关注,2:朋友,3:被关注
	private int getRelationship(int userByUserId,int userByFriendId){
		//判断是否关注
		if(isFollowed(userByUserId,userByFriendId)){
			//判断是否朋友
			if(isFollowed(userByFriendId,userByUserId)){
				return 2;
			}else{
				return 1;
			}
		}else{
			//判断是否被关注
			if(isFollowed(userByFriendId,userByUserId)){
				return 3;
			}else{
				return 0;
			}
		}
	}
	
	@JSON(serialize=false)
	public boolean getIsMine() {
		return isMine;
	}

	public void setIsMine(boolean isMine) {
		this.isMine = isMine;
	}
	
	
}
